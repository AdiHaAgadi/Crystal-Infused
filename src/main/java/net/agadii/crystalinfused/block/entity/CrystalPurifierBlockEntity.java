package net.agadii.crystalinfused.block.entity;

import com.google.common.collect.Maps;
import net.agadii.crystalinfused.block.CrystalPurifierBlock;
import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.recipe.CrystalPurificationRecipe;
import net.agadii.crystalinfused.screen.CrystalPurificationScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CrystalPurifierBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 200;
    private int fuelTime = 0;
    private int fuelProgress = 0;

    public CrystalPurifierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_PURIFIER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return CrystalPurifierBlockEntity.this.progress;
                    case 1: return CrystalPurifierBlockEntity.this.maxProgress;
                    case 2: return CrystalPurifierBlockEntity.this.fuelTime;
                    case 3: return CrystalPurifierBlockEntity.this.fuelProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CrystalPurifierBlockEntity.this.progress = value; break;
                    case 1: CrystalPurifierBlockEntity.this.maxProgress = value; break;
                    case 2: CrystalPurifierBlockEntity.this.fuelTime = value; break;
                    case 3: CrystalPurifierBlockEntity.this.fuelProgress = value; break;
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return ModBlocks.CRYSTAL_PURIFIER.getName();
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        }
        Item item = fuel.getItem();
        return createFuelTimeMap().getOrDefault(item, 0);
    }

    public static Map<Item, Integer> createFuelTimeMap() {
        LinkedHashMap<Item, Integer> map = Maps.newLinkedHashMap();
        map.put(Items.DRAGON_EGG, 10000);
        map.put(Items.DRAGON_BREATH, 600);

        return map;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrystalPurificationScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("crystal_purifier.progress", progress);
        nbt.putInt("crystal_purifier.fuel_progress", fuelProgress);

    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        this.fuelTime = this.getFuelTime(inventory.get(0));

        super.readNbt(nbt);
        this.progress = nbt.getInt("crystal_purifier.progress");
        this.fuelProgress = nbt.getInt("crystal_purifier.fuel_progress");

    }

    private boolean isBurning() {
        return this.fuelProgress > 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, CrystalPurifierBlockEntity entity) {
        if (world.isClient()) {
            return;
        }

        if (hasRecipe(entity) && !entity.isBurning()) {
            entity.fuelTime = entity.getFuelTime(entity.getStack(0));
            burnOneFuelItem(entity);

            if (entity.fuelTime > 0) {
                entity.fuelProgress++;
            }
        }

        if (hasRecipe(entity)) {
            if (!entity.isBurning()) {
                entity.fuelTime = entity.getFuelTime(entity.getStack(0));
                burnOneFuelItem(entity);

                if (entity.fuelTime > 0) {
                    entity.fuelProgress++;
                }

                markDirty(world, blockPos, blockState);
            } else {
                entity.fuelProgress++;
                entity.progress++;

                if (entity.progress >= entity.maxProgress) {
                    craftItem(entity);
                }

                if (entity.fuelProgress >= entity.fuelTime) {
                    burnOneFuelItem(entity);
                }

                markDirty(world, blockPos, blockState);
            }

        } else {
            entity.resetProgress();

            if (entity.isBurning()) {
                entity.fuelProgress++;

                if (entity.fuelProgress >= entity.fuelTime) {
                    entity.resetFuelProgress();
                }
            }

            markDirty(world, blockPos, blockState);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void resetFuelProgress() {
        this.fuelProgress = 0;
    }


    private static void craftItem(CrystalPurifierBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CrystalPurificationRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(CrystalPurificationRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if(hasRecipe(entity)) {
            entity.removeStack(1, 1);

            entity.setStack(2, new ItemStack(recipe.get().getOutput(null).getItem(),
                    entity.getStack(2).getCount() + 1));

            entity.resetProgress();
        }
    }

    public static void burnOneFuelItem(CrystalPurifierBlockEntity entity) {
        entity.removeStack(0, 1);
        entity.resetFuelProgress();
    }

    private static boolean hasRecipe(CrystalPurifierBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CrystalPurificationRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(CrystalPurificationRecipe.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput(null).getItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output.asItem() || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
}
