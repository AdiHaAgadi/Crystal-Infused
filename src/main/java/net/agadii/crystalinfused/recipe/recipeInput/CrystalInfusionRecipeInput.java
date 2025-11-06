package net.agadii.crystalinfused.recipe.recipeInput;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.List;

public record CrystalInfusionRecipeInput(List<ItemStack> inputs) implements RecipeInput {

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inputs.get(slot);
    }

    @Override
    public int getSize() {
        return inputs.size();
    }
}