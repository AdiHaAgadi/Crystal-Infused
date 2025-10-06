package net.agadii.crystalinfused.world;

import net.agadii.crystalinfused.CrystalInfused;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final RegistryKey<PlacedFeature> DEEPSLATE_SAPPHIRE_ORE_PLACED_KEY = registerKey("deepslate_sapphire_ore_placed");
    public static final RegistryKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static final RegistryKey<PlacedFeature> DEEPSLATE_RUBY_ORE_PLACED_KEY = registerKey("deepslate_ruby_ore_placed");
    public static final RegistryKey<PlacedFeature> AMBER_ORE_PLACED_KEY = registerKey("amber_ore_placed");
    public static final RegistryKey<PlacedFeature> DEEPSLATE_AMBER_ORE_PLACED_KEY = registerKey("deepslate_amber_ore_placed");
    public static final RegistryKey<PlacedFeature> TOPAZ_ORE_PLACED_KEY = registerKey("topaz_ore_placed");
    public static final RegistryKey<PlacedFeature> DEEPSLATE_TOPAZ_ORE_PLACED_KEY = registerKey("deepslate_topaz_ore_placed");
    public static final RegistryKey<PlacedFeature> PERIDOT_ORE_PLACED_KEY = registerKey("peridot_ore_placed");
    public static final RegistryKey<PlacedFeature> DEEPSLATE_PERIDOT_ORE_PLACED_KEY = registerKey("deepslate_peridot_ore_placed");
    public static final RegistryKey<PlacedFeature> SUGILITE_ORE_PLACED_KEY = registerKey("sugilite_ore_placed");
    public static final RegistryKey<PlacedFeature> SPINEL_ORE_PLACED_KEY = registerKey("spinel_ore_placed");
    public static final RegistryKey<PlacedFeature> PEARL_ORE_PLACED_KEY = registerKey("pearl_ore_placed");


    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, SAPPHIRE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SAPPHIRE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, DEEPSLATE_SAPPHIRE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEEPSLATE_SAPPHIRE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, RUBY_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBY_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, DEEPSLATE_RUBY_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEEPSLATE_RUBY_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, AMBER_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.AMBER_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, DEEPSLATE_AMBER_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEEPSLATE_AMBER_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, TOPAZ_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TOPAZ_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, DEEPSLATE_TOPAZ_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEEPSLATE_TOPAZ_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, PERIDOT_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PERIDOT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, DEEPSLATE_PERIDOT_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEEPSLATE_PERIDOT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(80))));
        register(context, SUGILITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SUGILITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.TOP)));
        register(context, SPINEL_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SPINEL_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(1, 4),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.TOP)));
        register(context, PEARL_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PEARL_ORE_KEY),
                ModOrePlacement.modifiersWithCount(UniformIntProvider.create(25, 50),
                        HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.TOP)));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(CrystalInfused.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
