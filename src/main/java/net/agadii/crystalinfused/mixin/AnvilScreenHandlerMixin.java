package net.agadii.crystalinfused.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
    @Inject(method = "updateResult", at = @At("TAIL"))
    private void allowOverlevelBooks(CallbackInfo ci) {
        AnvilScreenHandler handler = (AnvilScreenHandler) (Object) this;

        Inventory input = ((ForgingScreenHandlerAccessor) handler).getInput();
        Inventory outputInv = ((ForgingScreenHandlerAccessor) handler).getOutput();

        ItemStack left = input.getStack(0);
        ItemStack right = input.getStack(1);
        ItemStack output = outputInv.getStack(0);

        if (left.isEmpty() || right.isEmpty() || output.isEmpty()) return;

        Map<Enchantment, Integer> outEnchants = EnchantmentHelper.get(output);

        for (Map.Entry<Enchantment, Integer> e : outEnchants.entrySet()) {
            Enchantment ench = e.getKey();
            int level = e.getValue();

            int vanillaMax = ench.getMaxLevel();

            // Allow exactly +1 over vanilla max
            if (level == vanillaMax + 1) {
                ItemStack newBook = new ItemStack(output.getItem());
                EnchantedBookItem.addEnchantment(newBook, new EnchantmentLevelEntry(ench, level));
                outputInv.setStack(0, newBook);
            }
        }
    }
}