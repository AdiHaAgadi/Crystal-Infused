package net.agadii.crystalinfused.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.compat.rei.categories.CrystalInfusionCategory;
import net.agadii.crystalinfused.compat.rei.categories.CrystalPurificationCategory;
import net.agadii.crystalinfused.compat.rei.displays.CrystalInfusionDisplay;
import net.agadii.crystalinfused.compat.rei.displays.CrystalPurificationDisplay;
import net.agadii.crystalinfused.recipe.CrystalInfusionRecipe;
import net.agadii.crystalinfused.recipe.CrystalPurificationRecipe;
import net.agadii.crystalinfused.screen.CrystalInfusionScreen;
import net.agadii.crystalinfused.screen.CrystalPurificationScreen;

public class CrystalInfusedREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrystalPurificationCategory());
        registry.add(new CrystalInfusionCategory());

        registry.addWorkstations(CrystalPurificationCategory.CRYSTAL_PURIFICATION, EntryStacks.of(ModBlocks.CRYSTAL_PURIFIER));
        registry.addWorkstations(CrystalInfusionCategory.CRYSTAL_INFUSION, EntryStacks.of(ModBlocks.CRYSTAL_INFUSER));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrystalPurificationRecipe.class, CrystalPurificationRecipe.Type.INSTANCE, CrystalPurificationDisplay::new);
        registry.registerRecipeFiller(CrystalInfusionRecipe.class, CrystalInfusionRecipe.Type.INSTANCE, CrystalInfusionDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), CrystalPurificationScreen.class,
                CrystalPurificationCategory.CRYSTAL_PURIFICATION);
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), CrystalInfusionScreen.class,
                CrystalInfusionCategory.CRYSTAL_INFUSION); // TODO: update dimensions
    }
}
