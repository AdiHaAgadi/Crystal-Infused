package net.agadii.crystalinfused.data;

import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.item.ModItems;
import net.agadii.crystalinfused.recipe.ModRecipes;
import net.agadii.crystalinfused.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, List.of(ModBlocks.SAPPHIRE_ORE), RecipeCategory.MISC, ModItems.RAW_SAPPHIRE,
                1f, 200, "sapphire");
        offerSmelting(exporter, List.of(ModBlocks.DEEPSLATE_SAPPHIRE_ORE), RecipeCategory.MISC, ModItems.RAW_SAPPHIRE,
                1f, 200, "sapphire");
        offerSmelting(exporter, List.of(ModBlocks.RUBY_ORE), RecipeCategory.MISC, ModItems.RAW_RUBY,
                1f, 200, "ruby");
        offerSmelting(exporter, List.of(ModBlocks.DEEPSLATE_RUBY_ORE), RecipeCategory.MISC, ModItems.RAW_RUBY,
                1f, 200, "ruby");
        offerSmelting(exporter, List.of(ModBlocks.TOPAZ_ORE), RecipeCategory.MISC, ModItems.RAW_TOPAZ,
                1f, 200, "topaz");
        offerSmelting(exporter, List.of(ModBlocks.DEEPSLATE_TOPAZ_ORE), RecipeCategory.MISC, ModItems.RAW_TOPAZ,
                1f, 200, "topaz");
        offerSmelting(exporter, List.of(ModBlocks.AMBER_ORE), RecipeCategory.MISC, ModItems.RAW_AMBER,
                1f, 200, "amber");
        offerSmelting(exporter, List.of(ModBlocks.DEEPSLATE_AMBER_ORE), RecipeCategory.MISC, ModItems.RAW_AMBER,
                1f, 200, "amber");
        offerSmelting(exporter, List.of(ModBlocks.PERIDOT_ORE), RecipeCategory.MISC, ModItems.RAW_PERIDOT,
                1f, 200, "peridot");
        offerSmelting(exporter, List.of(ModBlocks.DEEPSLATE_PERIDOT_ORE), RecipeCategory.MISC, ModItems.RAW_PERIDOT,
                1f, 200, "peridot");
        offerSmelting(exporter, List.of(ModBlocks.SUGILITE_ORE), RecipeCategory.MISC, ModItems.RAW_SUGILITE,
                1f, 200, "sugilite");
        offerSmelting(exporter, List.of(ModBlocks.SPINEL_ORE), RecipeCategory.MISC, ModItems.RAW_SPINEL,
                1f, 200, "spinel");
        offerSmelting(exporter, List.of(ModBlocks.PEARL_ORE), RecipeCategory.MISC, ModItems.RAW_PEARL,
                1f, 200, "pearl");

        offerBlasting(exporter, List.of(ModBlocks.SAPPHIRE_ORE), RecipeCategory.MISC, ModItems.RAW_SAPPHIRE,
                1f, 100, "sapphire");
        offerBlasting(exporter, List.of(ModBlocks.DEEPSLATE_SAPPHIRE_ORE), RecipeCategory.MISC, ModItems.RAW_SAPPHIRE,
                1f, 100, "sapphire");
        offerBlasting(exporter, List.of(ModBlocks.RUBY_ORE), RecipeCategory.MISC, ModItems.RAW_RUBY,
                1f, 100, "ruby");
        offerBlasting(exporter, List.of(ModBlocks.DEEPSLATE_RUBY_ORE), RecipeCategory.MISC, ModItems.RAW_RUBY,
                1f, 100, "ruby");
        offerBlasting(exporter, List.of(ModBlocks.TOPAZ_ORE), RecipeCategory.MISC, ModItems.RAW_TOPAZ,
                1f, 100, "topaz");
        offerBlasting(exporter, List.of(ModBlocks.DEEPSLATE_TOPAZ_ORE), RecipeCategory.MISC, ModItems.RAW_TOPAZ,
                1f, 100, "topaz");
        offerBlasting(exporter, List.of(ModBlocks.AMBER_ORE), RecipeCategory.MISC, ModItems.RAW_AMBER,
                1f, 100, "amber");
        offerBlasting(exporter, List.of(ModBlocks.DEEPSLATE_AMBER_ORE), RecipeCategory.MISC, ModItems.RAW_AMBER,
                1f, 100, "amber");
        offerBlasting(exporter, List.of(ModBlocks.PERIDOT_ORE), RecipeCategory.MISC, ModItems.RAW_PERIDOT,
                1f, 100, "peridot");
        offerBlasting(exporter, List.of(ModBlocks.DEEPSLATE_PERIDOT_ORE), RecipeCategory.MISC, ModItems.RAW_PERIDOT,
                1f, 100, "peridot");
        offerBlasting(exporter, List.of(ModBlocks.SUGILITE_ORE), RecipeCategory.MISC, ModItems.RAW_SUGILITE,
                1f, 100, "sugilite");
        offerBlasting(exporter, List.of(ModBlocks.SPINEL_ORE), RecipeCategory.MISC, ModItems.RAW_SPINEL,
                1f, 100, "spinel");
        offerBlasting(exporter, List.of(ModBlocks.PEARL_ORE), RecipeCategory.MISC, ModItems.RAW_PEARL,
                1f, 100, "pearl");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.DECORATIONS,
                ModBlocks.SAPPHIRE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.DECORATIONS,
                ModBlocks.RUBY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.TOPAZ, RecipeCategory.DECORATIONS,
                ModBlocks.TOPAZ_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.AMBER, RecipeCategory.DECORATIONS,
                ModBlocks.AMBER_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PERIDOT, RecipeCategory.DECORATIONS,
                ModBlocks.PERIDOT_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SUGILITE, RecipeCategory.DECORATIONS,
                ModBlocks.SUGILITE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SPINEL, RecipeCategory.DECORATIONS,
                ModBlocks.SPINEL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PEARL, RecipeCategory.DECORATIONS,
                ModBlocks.PEARL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.CRYSTAL_PURIFIER)
                .input('#', Blocks.COPPER_BLOCK)
                .input('X', Blocks.GLASS)
                .input('I', Items.COPPER_INGOT)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .criterion("has_copper", VanillaRecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.CRYSTAL_INFUSER)
                .input('#', Items.NETHERITE_INGOT)
                .input('X', ModTags.CRYSTAL)
                .input('I', ItemTags.WOOL)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .criterion("has_netherite", VanillaRecipeProvider.conditionsFromItem(Items.NETHERITE_INGOT)).offerTo(exporter);
    }
}
