package net.agadii.crystalinfused.recipe;

import net.agadii.crystalinfused.CrystalInfused;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(CrystalInfused.MOD_ID, CrystalPurificationRecipe.Serializer.ID),
                CrystalPurificationRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(CrystalInfused.MOD_ID, CrystalPurificationRecipe.Type.ID),
                CrystalPurificationRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(CrystalInfused.MOD_ID, CrystalInfusionRecipe.Serializer.ID),
                CrystalInfusionRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(CrystalInfused.MOD_ID, CrystalInfusionRecipe.Type.ID),
                CrystalInfusionRecipe.Type.INSTANCE);
    }
}
