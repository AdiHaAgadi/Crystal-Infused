package net.agadii.crystalinfused.block;

import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.item.ModItemGroup;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.sound.Sound;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import static net.minecraft.block.AbstractFurnaceBlock.LIT;

public class ModBlocks {
    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));
    public static final Block TOPAZ_BLOCK = registerBlock("topaz_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));
    public static final Block AMBER_BLOCK = registerBlock("amber_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));
    public static final Block PERIDOT_BLOCK = registerBlock("peridot_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));
    public static final Block SUGILITE_BLOCK = registerBlock("sugilite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));
    public static final Block SPINEL_BLOCK = registerBlock("spinel_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));
    public static final Block PEARL_BLOCK = registerBlock("pearl_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f).requiresTool()));

    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(3.0f).requiresTool(),
                    UniformIntProvider.create(3, 7)));
    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(3.0f).requiresTool(),
                    UniformIntProvider.create(3, 7)));
    public static final Block TOPAZ_ORE = registerBlock("topaz_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(3.0f).requiresTool(),
                    UniformIntProvider.create(3, 7)));
    public static final Block AMBER_ORE = registerBlock("amber_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(3.0f).requiresTool(),
                    UniformIntProvider.create(3, 7)));
    public static final Block PERIDOT_ORE = registerBlock("peridot_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(3.0f).requiresTool(),
                    UniformIntProvider.create(3, 7)));
    public static final Block SUGILITE_ORE = registerBlock("sugilite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(3.0f).requiresTool(),
                    UniformIntProvider.create(3, 7)));
    public static final Block SPINEL_ORE = registerBlock("spinel_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(3.0f, 9.0f).requiresTool(),
                    UniformIntProvider.create(3, 7)));
    public static final Block PEARL_ORE = registerBlock("pearl_ore",
            new ExperienceDroppingSandBlock(14406560, FabricBlockSettings.copyOf(Blocks.SAND).mapColor(MapColor.PALE_YELLOW).strength(1.0f).requiresTool().sounds(BlockSoundGroup.SAND),
                    UniformIntProvider.create(3, 7)));

    Block a = Blocks.DIAMOND_ORE;
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(ModBlocks.SAPPHIRE_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5f, 3.0f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE),
                    UniformIntProvider.create(3, 7)));
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(ModBlocks.RUBY_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5f, 3.0f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE),
                    UniformIntProvider.create(3, 7)));
    public static final Block DEEPSLATE_AMBER_ORE = registerBlock("deepslate_amber_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(ModBlocks.AMBER_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5f, 3.0f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE),
                    UniformIntProvider.create(3, 7)));
    public static final Block DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(ModBlocks.TOPAZ_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5f, 3.0f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE),
                    UniformIntProvider.create(3, 7)));
    public static final Block DEEPSLATE_PERIDOT_ORE = registerBlock("deepslate_peridot_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(ModBlocks.PERIDOT_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5f, 3.0f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE),
                    UniformIntProvider.create(3, 7)));

    public static final Block CRYSTAL_PURIFIER = registerBlock("crystal_purifier",
            new CrystalPurifierBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)
                    .strength(3.5f).requiresTool().sounds(BlockSoundGroup.COPPER)
                    .luminance(state -> state.get(CrystalPurifierBlock.LIT) ? 13 : 0)));

    public static final Block CRYSTAL_INFUSER = registerBlock("crystal_infuser",
            new CrystalInfuserBlock(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).mapColor(MapColor.BLACK)
                    .strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, new Identifier(CrystalInfused.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(CrystalInfused.MOD_ID, name),
                 new BlockItem(block, new FabricItemSettings()));

        return item;
    }
    public static void registerModBlocks() {
        CrystalInfused.LOGGER.info("Registering Mod Blocks for " + CrystalInfused.MOD_ID);
    }
}
