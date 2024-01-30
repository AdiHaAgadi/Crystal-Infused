package net.agadii.crystalinfused.world;

import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AMBER_ORE_KEY = registerKey("amber_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TOPAZ_ORE_KEY = registerKey("topaz_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PERIDOT_ORE_KEY = registerKey("peridot_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SUGILITE_ORE_KEY = registerKey("sugilite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPINEL_ORE_KEY = registerKey("spinel_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PEARL_ORE_KEY = registerKey("pearl_ore");



    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchRuleTest(Blocks.NETHERRACK);
        RuleTest endstoneReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldSapphireOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.SAPPHIRE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.SAPPHIRE_ORE.getDefaultState())); // TODO: replace to deepslate varient of ore
        List<OreFeatureConfig.Target> overworldRubyOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.RUBY_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.RUBY_ORE.getDefaultState())); // TODO: replace to deepslate varient of ore
        List<OreFeatureConfig.Target> overworldAmberOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.AMBER_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.AMBER_ORE.getDefaultState())); // TODO: replace to deepslate varient of ore
        List<OreFeatureConfig.Target> overworldTopazOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.TOPAZ_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.TOPAZ_ORE.getDefaultState())); // TODO: replace to deepslate varient of ore
        List<OreFeatureConfig.Target> overworldPeridotOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.PERIDOT_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.PERIDOT_ORE.getDefaultState())); // TODO: replace to deepslate varient of ore

        List<OreFeatureConfig.Target> netherSugiliteOres =
                List.of(OreFeatureConfig.createTarget(netherrackReplacables, ModBlocks.SUGILITE_ORE.getDefaultState())); // TODO: replace to deepslate varient of ore
        List<OreFeatureConfig.Target> endSpinelOres =
                List.of(OreFeatureConfig.createTarget(endstoneReplacables, ModBlocks.SPINEL_ORE.getDefaultState())); // TODO: replace to deepslate varient of ore

        register(context, SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSapphireOres, 7));
        register(context, RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 7));
        register(context, AMBER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldAmberOres, 7));
        register(context, TOPAZ_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTopazOres, 7));
        register(context, PERIDOT_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPeridotOres, 7));
        register(context, SUGILITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherSugiliteOres, 7));
        register(context, SPINEL_ORE_KEY, Feature.ORE, new OreFeatureConfig(endSpinelOres, 7));

    }

    public static RegistryKey<ConfiguredFeature<?,?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(CrystalInfused.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
