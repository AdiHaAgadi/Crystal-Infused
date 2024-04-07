package net.agadii.crystalinfused.screen.slot;

import net.agadii.crystalinfused.screen.CrystalPurificationScreenHandler;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.Slot;

public class PurificationFuelSlot extends Slot {
    private final CrystalPurificationScreenHandler handler;

    public PurificationFuelSlot(CrystalPurificationScreenHandler handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return this.handler.isFuel(stack) || PurificationFuelSlot.isBucket(stack);
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return PurificationFuelSlot.isBucket(stack) ? 1 : super.getMaxItemCount(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.isOf(Items.BUCKET);
    }
}
