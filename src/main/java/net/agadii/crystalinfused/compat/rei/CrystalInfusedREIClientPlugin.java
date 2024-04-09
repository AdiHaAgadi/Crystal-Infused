package net.agadii.crystalinfused.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.recipe.CrystalPurificationRecipe;
import net.agadii.crystalinfused.screen.CrystalPurificationScreen;

public class CrystalInfusedREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrystalPurificationCategory());

        registry.addWorkstations(CrystalPurificationCategory.CRYSTAL_PURIFICATION, EntryStacks.of(ModBlocks.CRYSTAL_PURIFIER));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrystalPurificationRecipe.class, CrystalPurificationRecipe.Type.INSTANCE, CrystalPurificationDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), CrystalPurificationScreen.class,
                CrystalPurificationCategory.CRYSTAL_PURIFICATION);
    }
}
