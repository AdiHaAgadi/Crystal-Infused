package net.agadii.crystalinfused.data;

import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SAPPHIRE_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBY_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBY_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TOPAZ_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TOPAZ_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_TOPAZ_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.AMBER_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.AMBER_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_AMBER_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PERIDOT_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PERIDOT_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_PERIDOT_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SUGILITE_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SUGILITE_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SPINEL_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SPINEL_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PEARL_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PEARL_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOPAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.PERIDOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUGILITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPINEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEARL, Models.GENERATED);
    }
}
