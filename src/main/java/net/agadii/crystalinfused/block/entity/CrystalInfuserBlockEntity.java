package net.agadii.crystalinfused.block.entity;

import net.agadii.crystalinfused.block.CrystalInfuserBlock;
import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.particle.ModParticles;
import net.agadii.crystalinfused.recipe.CrystalInfusionRecipe;
import net.agadii.crystalinfused.recipe.recipeInput.CrystalInfusionRecipeInput;
import net.agadii.crystalinfused.screen.CrystalInfusionScreenHandler;
import net.agadii.crystalinfused.tag.ModTags;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CrystalInfuserBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private static int maxProgress = 700;
    private int fuelProgress = 0;
    private static int maxFuelProgress = maxProgress * 2;
    private static Random random = new Random();

    public CrystalInfuserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_INFUSER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> CrystalInfuserBlockEntity.this.progress;
                    case 1 -> maxProgress;
                    case 2 -> CrystalInfuserBlockEntity.this.fuelProgress;
                    case 3 -> maxFuelProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CrystalInfuserBlockEntity.this.progress = value; break;
                    case 1: maxProgress = value; break;
                    case 2: CrystalInfuserBlockEntity.this.fuelProgress = value; break;
                    case 3: maxFuelProgress = value; break;
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    public CrystalInfuserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, PropertyDelegate propertyDelegate) {
        super(type, pos, state);
        this.propertyDelegate = propertyDelegate;
    }

    // ----------- Save data to NBT (server -> disk) -----------
    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        Inventories.writeNbt(nbt, this.inventory, registryLookup);

        nbt.putInt("Progress", this.progress);
        nbt.putInt("FuelProgress", this.fuelProgress);
    }

    // ----------- Load data from NBT -----------
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        Inventories.readNbt(nbt, this.inventory, registryLookup);

        this.progress = nbt.getInt("Progress");
        this.fuelProgress = nbt.getInt("FuelProgress");
    }

    // ----------- Sync to client -----------

    // 1. Packet for when the block updates (e.g., setBlockState or notifyListeners)
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    // 2. NBT for when the chunk is first loaded by the client
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    // ----------- Call this whenever progress changes -----------
    private void sync() {
        if (world != null && !world.isClient) {
            markDirty();
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return ModBlocks.CRYSTAL_INFUSER.getName();
    }

    public static int getInfusionTime() {
        return maxProgress;
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.getItem().equals(Items.NETHER_STAR);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrystalInfusionScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    private boolean isBurning() {
        return this.fuelProgress >= 0;
    }

    private static void createInfusionParticle(World world, BlockPos pos) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        world.addParticle(ModParticles.INFUSION_PARTICLE, x, y, z, 0, 0, 0);
    }

    // client-side only ticking
    public static void tickClient(CrystalInfuserBlockEntity entity) {
        World world = entity.getWorld();
        if (world != null && entity.progress > 0 && random.nextInt(14) == 0) {
            createInfusionParticle(world, entity.getPos());
        }
    }

    // server-side only ticking
    public static void tickServer(CrystalInfuserBlockEntity entity) {
        if (hasRecipe(entity)) {
            if (!entity.isBurning()) {
                boolean hasBurnt = burnOneFuelItem(entity);
                if (hasBurnt) {
                    entity.fuelProgress--;
                    entity.progress++;
                }
            } else {
                entity.fuelProgress--;
                entity.progress++;
            }

            if (entity.progress >= maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress(); // if one of 3 ingredients taken out - reset progress
        }

        // Send update to clients so client-side tick sees it
        entity.sync();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void resetFuelProgress() {
        this.fuelProgress = maxFuelProgress;
    }


    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 4) { // could be any item
            return true;
        } else if (slot == 0) {
            return stack.isOf(Items.ENCHANTED_BOOK);
        } else if (slot == 3) {
            return CrystalInfuserBlockEntity.isFuel(stack);
        } else {
            return stack.isIn(ModTags.CRYSTAL);
        }
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        Direction facing = getCachedState().get(CrystalInfuserBlock.FACING);

        if (side == Direction.UP) {
            return new int[]{0};
        } else if (side == Direction.DOWN) {
            return new int[]{4};
        } else if (side == facing.rotateYClockwise()) {
            return new int[]{1}; // Left
        } else if (side == facing.rotateYCounterclockwise()) {
            return new int[]{2}; // Right
        } else if (side == facing || side == facing.getOpposite()) {
            return new int[]{3}; // Front / Back
        }

        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
            return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return dir == Direction.DOWN && slot == 4;
    }

    private static Optional<RecipeEntry<CrystalInfusionRecipe>> getCurrentRecipe(CrystalInfuserBlockEntity entity) {
        if (entity.getWorld() == null) return Optional.empty();

        return entity.getWorld().getRecipeManager()
                .getFirstMatch(
                        CrystalInfusionRecipe.Type.INSTANCE,
                        new CrystalInfusionRecipeInput(List.of(
                            entity.inventory.get(0),
                            entity.inventory.get(1),
                            entity.inventory.get(2)
                            )
                        ), // slot 1 is the input slot
                        entity.getWorld()
                );
    }

    private static void craftItem(CrystalInfuserBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<RecipeEntry<CrystalInfusionRecipe>> recipe = getCurrentRecipe(entity);

        entity.removeStack(4, 1);

        if (recipe.isPresent() && hasRecipe(entity)) {
            ItemStack outputStack = recipe.get().value().getResult(entity.getWorld().getRegistryManager());

            entity.setStack(4, outputStack.copy());
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);

            entity.resetProgress();
        }
    }

    public static boolean burnOneFuelItem(CrystalInfuserBlockEntity entity) {
        if (isFuel(entity.getStack(3))) {
            entity.removeStack(3, 1);
            entity.resetFuelProgress();

            return true;
        } else {
            entity.resetProgress();

            return false;
        }
    }

    private static boolean hasRecipe(CrystalInfuserBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<RecipeEntry<CrystalInfusionRecipe>> recipe = getCurrentRecipe(entity);


        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, recipe.get().value().getResult(null).getItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(4).getItem() == output.asItem() || inventory.getStack(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(4).getMaxCount() > inventory.getStack(4).getCount();
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }
}
