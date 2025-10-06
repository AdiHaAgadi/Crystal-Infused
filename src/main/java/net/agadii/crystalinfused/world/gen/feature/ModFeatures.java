package net.agadii.crystalinfused.world.gen.feature;

import net.agadii.crystalinfused.CrystalInfused;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class ModFeatures {
    public static final PearlOreFeature PEARL_ORE =
            registerFeature("pearl_ore_feature", new PearlOreFeature(OreFeatureConfig.CODEC));

    private static <FC extends FeatureConfig, F extends Feature<FC>> F registerFeature(String name, F feature) {
        return Registry.register(Registries.FEATURE, new Identifier(CrystalInfused.MOD_ID, name), feature);
    }

    public static void registerModFeatures() {
        CrystalInfused.LOGGER.info("Registering Mod Features for " + CrystalInfused.MOD_ID);
    }
}
