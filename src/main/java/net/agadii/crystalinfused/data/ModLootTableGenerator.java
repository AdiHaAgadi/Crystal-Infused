package net.agadii.crystalinfused.data;

import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SAPPHIRE_BLOCK);
        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.TOPAZ_BLOCK);
        addDrop(ModBlocks.AMBER_BLOCK);
        addDrop(ModBlocks.PERIDOT_BLOCK);
        addDrop(ModBlocks.SUGILITE_BLOCK);
        addDrop(ModBlocks.SPINEL_BLOCK);
        addDrop(ModBlocks.PEARL_BLOCK);
        addDrop(ModBlocks.CRYSTAL_PURIFIER);

        addDrop(ModBlocks.SAPPHIRE_ORE, oreDrops(ModBlocks.SAPPHIRE_ORE, ModItems.RAW_SAPPHIRE));
        addDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, oreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.RAW_SAPPHIRE));
        addDrop(ModBlocks.RUBY_ORE, oreDrops(ModBlocks.RUBY_ORE, ModItems.RAW_RUBY));
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, oreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RAW_RUBY));
        addDrop(ModBlocks.TOPAZ_ORE, oreDrops(ModBlocks.TOPAZ_ORE, ModItems.RAW_TOPAZ));
        addDrop(ModBlocks.DEEPSLATE_TOPAZ_ORE, oreDrops(ModBlocks.DEEPSLATE_TOPAZ_ORE, ModItems.RAW_TOPAZ));
        addDrop(ModBlocks.AMBER_ORE, oreDrops(ModBlocks.AMBER_ORE, ModItems.RAW_AMBER));
        addDrop(ModBlocks.DEEPSLATE_AMBER_ORE, oreDrops(ModBlocks.DEEPSLATE_AMBER_ORE, ModItems.RAW_AMBER));
        addDrop(ModBlocks.PERIDOT_ORE, oreDrops(ModBlocks.PERIDOT_ORE, ModItems.RAW_PERIDOT));
        addDrop(ModBlocks.DEEPSLATE_PERIDOT_ORE, oreDrops(ModBlocks.DEEPSLATE_PERIDOT_ORE, ModItems.RAW_PERIDOT));
        addDrop(ModBlocks.SUGILITE_ORE, oreDrops(ModBlocks.SUGILITE_ORE, ModItems.RAW_SUGILITE));
        addDrop(ModBlocks.SPINEL_ORE, oreDrops(ModBlocks.SPINEL_ORE, ModItems.RAW_SPINEL));
        addDrop(ModBlocks.PEARL_ORE, oreDrops(ModBlocks.PEARL_ORE, ModItems.RAW_PEARL));
    }
}
