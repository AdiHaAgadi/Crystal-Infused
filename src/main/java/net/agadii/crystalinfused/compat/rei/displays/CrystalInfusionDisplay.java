package net.agadii.crystalinfused.compat.rei.displays;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.agadii.crystalinfused.compat.rei.categories.CrystalInfusionCategory;
import net.agadii.crystalinfused.recipe.CrystalInfusionRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrystalInfusionDisplay extends BasicDisplay {
    public CrystalInfusionDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    public CrystalInfusionDisplay(RecipeEntry<CrystalInfusionRecipe> recipe) {
        super(getInputList(recipe.value()), List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
    }

    private static List<EntryIngredient> getInputList(CrystalInfusionRecipe recipe) {
        if (recipe == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>();
        for (ItemStack stack : recipe.getDisplayInputs()) {
            list.add(EntryIngredient.of(EntryStacks.of(stack)));
        }

        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CrystalInfusionCategory.CRYSTAL_INFUSION;
    }
}
