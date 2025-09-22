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

import java.util.HashMap;
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

        Map<Enchantment, Integer> inLeftEnchants = EnchantmentHelper.get(left);
        Map<Enchantment, Integer> inRightEnchants = EnchantmentHelper.get(right);
        Map<Enchantment, Integer> outEnchants = EnchantmentHelper.get(output);

        Map<Enchantment, Integer> newEnchants = new HashMap<>(outEnchants);

        for (Map.Entry<Enchantment, Integer> e : outEnchants.entrySet()) {
            // default to 0 if enchantment does not exist in specific input
            int leftEnchantmentLevel = 0;
            int rightEnchantmentLevel = 0;

            Enchantment ench = e.getKey();
            int level = e.getValue();

            if (inLeftEnchants.keySet().contains(ench)) {
                leftEnchantmentLevel = inLeftEnchants.get(ench);
            }

            if (inRightEnchants.keySet().contains(ench)) {
                rightEnchantmentLevel = inRightEnchants.get(ench);
            }

            int vanillaMax = ench.getMaxLevel();

            // if at least one has the level above the vanilla max via mod - both max will not create max + 1
            System.out.println("vanilla max: " + vanillaMax);
            System.out.println("output level: " + level);
            if (leftEnchantmentLevel == vanillaMax + 1 || rightEnchantmentLevel == vanillaMax + 1) {
                // Allow exactly +1 over vanilla max
//                outputInv.getStack(0).().
                newEnchants.put(ench, vanillaMax + 1);
            }
        }

        EnchantmentHelper.set(newEnchants, outputInv.getStack(0));
    }
}