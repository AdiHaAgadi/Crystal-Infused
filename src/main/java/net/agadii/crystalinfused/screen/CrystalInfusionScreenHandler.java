package net.agadii.crystalinfused.screen;

import net.agadii.crystalinfused.block.entity.CrystalInfuserBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;

public class CrystalInfusionScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final CrystalInfuserBlockEntity blockEntity;

    public CrystalInfusionScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(4));
    }

    public CrystalInfusionScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity entity, PropertyDelegate delegate) {
        super(ModScreenHandlers.CRYSTAL_INFUSION_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) entity), 5);
        this.inventory = (Inventory)entity;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        this.blockEntity = (CrystalInfuserBlockEntity) entity;

        this.addSlot(new Slot(inventory, 0, 80, 25));
        this.addSlot(new Slot(inventory, 1, 41, 64));
        this.addSlot(new Slot(inventory, 2, 119, 64));
        this.addSlot(new Slot(inventory, 3, 80, 111));

        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 4, 80, 64));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);
    }


    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledArrowsProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowsSize = 18; // This is the height in pixels of your arrows

        return progress != 0 ? progress * progressArrowsSize / maxProgress : 0;
    }

    public int getScaledBubblesProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressBubblesSize = 19; // This is the height in pixels of your bubbles

        return progress != 0 ? progress * progressBubblesSize / maxProgress : 0;
    }

    public int getScaledFuelTankProgress() {
        int fuelProgress = this.propertyDelegate.get(2); // fuel progress
        int maxFuelProgress = this.propertyDelegate.get(3);  // Max fuel progress
        int progressFuelTankSize = 20; // This is the width in pixels of your fuel tank

        return fuelProgress != maxFuelProgress ? fuelProgress * progressFuelTankSize / maxFuelProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 142 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 200));
        }
    }

    public boolean isFuel(ItemStack stack) {
        return CrystalInfuserBlockEntity.isFuel(stack);
    }
}