package net.agadii.crystalinfused.world;

import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.world.gen.feature.ModFeatures;
import net.agadii.crystalinfused.world.gen.feature.PearlOreFeature;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_SAPPHIRE_ORE_KEY = registerKey("deepslate_sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_RUBY_ORE_KEY = registerKey("deepslate_ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AMBER_ORE_KEY = registerKey("amber_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_AMBER_ORE_KEY = registerKey("deepslate_amber_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TOPAZ_ORE_KEY = registerKey("topaz_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_TOPAZ_ORE_KEY = registerKey("deepslate_topaz_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PERIDOT_ORE_KEY = registerKey("peridot_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_PERIDOT_ORE_KEY = registerKey("deepslate_peridot_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SUGILITE_ORE_KEY = registerKey("sugilite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPINEL_ORE_KEY = registerKey("spinel_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PEARL_ORE_KEY = registerKey("pearl_ore");



    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchRuleTest(Blocks.NETHERRACK);
        RuleTest endstoneReplacables = new BlockMatchRuleTest(Blocks.END_STONE);
        RuleTest sandReplacables = new TagMatchRuleTest(BlockTags.SAND);

        List<OreFeatureConfig.Target> overworldSapphireOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.SAPPHIRE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldRubyOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.RUBY_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_RUBY_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldAmberOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.AMBER_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_AMBER_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldTopazOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.TOPAZ_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_TOPAZ_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldPeridotOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.PERIDOT_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_PERIDOT_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherSugiliteOres =
                List.of(OreFeatureConfig.createTarget(netherrackReplacables, ModBlocks.SUGILITE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> endSpinelOres =
                List.of(OreFeatureConfig.createTarget(endstoneReplacables, ModBlocks.SPINEL_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> seaPearlOres =
                List.of(OreFeatureConfig.createTarget(sandReplacables, ModBlocks.PEARL_ORE.getDefaultState()));

        register(context, SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSapphireOres, 4));
        register(context, DEEPSLATE_SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSapphireOres, 4));
        register(context, RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 4));
        register(context, DEEPSLATE_RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 4));
        register(context, AMBER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldAmberOres, 4));
        register(context, DEEPSLATE_AMBER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldAmberOres, 4));
        register(context, TOPAZ_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTopazOres, 4));
        register(context, DEEPSLATE_TOPAZ_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTopazOres, 4));
        register(context, PERIDOT_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPeridotOres, 4));
        register(context, DEEPSLATE_PERIDOT_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPeridotOres, 4));
        register(context, SUGILITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherSugiliteOres, 4));
        register(context, SPINEL_ORE_KEY, Feature.ORE, new OreFeatureConfig(endSpinelOres, 4));
        register(context, PEARL_ORE_KEY, ModFeatures.PEARL_ORE , new OreFeatureConfig(seaPearlOres, 5));

    }

    public static RegistryKey<ConfiguredFeature<?,?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(CrystalInfused.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
