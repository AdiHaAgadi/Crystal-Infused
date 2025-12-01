package net.agadii.crystalinfused.mixin;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

        ItemEnchantmentsComponent inLeftEnchants = EnchantmentHelper.getEnchantments(left);
        ItemEnchantmentsComponent inRightEnchants = EnchantmentHelper.getEnchantments(right);
        ItemEnchantmentsComponent outEnchants = EnchantmentHelper.getEnchantments(output);

        ItemEnchantmentsComponent.Builder enchantsBuilder = new ItemEnchantmentsComponent.Builder(outEnchants);

        for (RegistryEntry<Enchantment> currEnchantment : outEnchants.getEnchantments()) {

            Enchantment enchant = currEnchantment.value();

            int leftEnchantmentLevel = inLeftEnchants.getLevel(currEnchantment);
            int rightEnchantmentLevel = inRightEnchants.getLevel(currEnchantment);

            int vanillaMax = enchant.getMaxLevel();

            // if at least one has the level above the vanilla max via mod - both max will not create max + 1
            if (leftEnchantmentLevel == vanillaMax + 1 || rightEnchantmentLevel == vanillaMax + 1) {
                // Allow exactly +1 over vanilla max
                enchantsBuilder.set(currEnchantment, vanillaMax + 1);
            }
        }

        ItemEnchantmentsComponent newEnchants = enchantsBuilder.build();
        EnchantmentHelper.set(outputInv.getStack(0), newEnchants);
    }
}