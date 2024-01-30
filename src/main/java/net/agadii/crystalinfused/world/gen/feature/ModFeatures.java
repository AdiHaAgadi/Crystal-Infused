package net.agadii.crystalinfused.world.gen.feature;

import net.agadii.crystalinfused.CrystalInfused;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModFeatures {
    public static final Feature<DefaultFeatureConfig> PEARL_ORE =
            registerFeature("pearl_ore_feature", new PearlOreFeature(DefaultFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return Registry.register(Registries.FEATURE, new Identifier(CrystalInfused.MOD_ID, name), feature);
    }

    public static void registerModFeatures() {
        CrystalInfused.LOGGER.info("Registering Mod Features for " + CrystalInfused.MOD_ID);
    }
}
