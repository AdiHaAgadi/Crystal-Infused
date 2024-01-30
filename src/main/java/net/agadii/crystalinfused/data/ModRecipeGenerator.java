package net.agadii.crystalinfused.data;

import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, List.of(ModBlocks.SAPPHIRE_ORE), RecipeCategory.MISC, ModItems.SAPPHIRE,
                1f, 200, "sapphire");
        offerSmelting(exporter, List.of(ModBlocks.RUBY_ORE), RecipeCategory.MISC, ModItems.RUBY,
                1f, 200, "ruby");
        offerSmelting(exporter, List.of(ModBlocks.TOPAZ_ORE), RecipeCategory.MISC, ModItems.TOPAZ,
                1f, 200, "topaz");
        offerSmelting(exporter, List.of(ModBlocks.AMBER_ORE), RecipeCategory.MISC, ModItems.AMBER,
                1f, 200, "amber");
        offerSmelting(exporter, List.of(ModBlocks.PERIDOT_ORE), RecipeCategory.MISC, ModItems.PERIDOT,
                1f, 200, "peridot");
        offerSmelting(exporter, List.of(ModBlocks.SUGILITE_ORE), RecipeCategory.MISC, ModItems.SUGILITE,
                1f, 200, "sugilite");
        offerSmelting(exporter, List.of(ModBlocks.SPINEL_ORE), RecipeCategory.MISC, ModItems.SPINEL,
                1f, 200, "spinel");
        offerSmelting(exporter, List.of(ModBlocks.PEARL_ORE), RecipeCategory.MISC, ModItems.PEARL,
                1f, 200, "pearl");

        offerBlasting(exporter, List.of(ModBlocks.SAPPHIRE_ORE), RecipeCategory.MISC, ModItems.SAPPHIRE,
                1f, 100, "sapphire");
        offerBlasting(exporter, List.of(ModBlocks.RUBY_ORE), RecipeCategory.MISC, ModItems.RUBY,
                1f, 100, "ruby");
        offerBlasting(exporter, List.of(ModBlocks.TOPAZ_ORE), RecipeCategory.MISC, ModItems.TOPAZ,
                1f, 100, "topaz");
        offerBlasting(exporter, List.of(ModBlocks.AMBER_ORE), RecipeCategory.MISC, ModItems.AMBER,
                1f, 100, "amber");
        offerBlasting(exporter, List.of(ModBlocks.PERIDOT_ORE), RecipeCategory.MISC, ModItems.PERIDOT,
                1f, 100, "peridot");
        offerBlasting(exporter, List.of(ModBlocks.SUGILITE_ORE), RecipeCategory.MISC, ModItems.SUGILITE,
                1f, 100, "sugilite");
        offerBlasting(exporter, List.of(ModBlocks.SPINEL_ORE), RecipeCategory.MISC, ModItems.SPINEL,
                1f, 100, "spinel");
        offerBlasting(exporter, List.of(ModBlocks.PEARL_ORE), RecipeCategory.MISC, ModItems.PEARL,
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
    }
}
