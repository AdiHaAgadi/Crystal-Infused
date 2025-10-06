package net.agadii.crystalinfused.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PearlOreFeature extends OreFeature {
    public PearlOreFeature(Codec<OreFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<OreFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        // check 5 water blocks above pos
        for (int i = 1; i <= 5; i++) {
            if (!context.getWorld().getBlockState(pos.up(i)).isOf(Blocks.WATER)) {
                return false;
            }
        }
        return super.generate(context);
    }
}

//import com.mojang.serialization.Codec;
//import net.agadii.crystalinfused.CrystalInfused;
//import net.agadii.crystalinfused.block.ModBlocks;
//import net.minecraft.block.Block;
//import net.minecraft.block.Blocks;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.registry.tag.BiomeTags;
//import net.minecraft.registry.tag.FluidTags;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.Heightmap;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.BiomeKeys;
//import net.minecraft.world.gen.chunk.ChunkGenerator;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.OreFeature;
//import net.minecraft.world.gen.feature.OreFeatureConfig;
//import net.minecraft.world.gen.feature.util.FeatureContext;import com.mojang.serialization.Codec;
//import net.agadii.crystalinfused.CrystalInfused;
//import net.agadii.crystalinfused.block.ModBlocks;
//import net.minecraft.block.Block;
//import net.minecraft.block.Blocks;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.registry.tag.BiomeTags;
//import net.minecraft.registry.tag.FluidTags;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.Heightmap;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.BiomeKeys;
//import net.minecraft.world.gen.chunk.ChunkGenerator;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.OreFeature;
//import net.minecraft.world.gen.feature.OreFeatureConfig;
//import net.minecraft.world.gen.feature.util.FeatureContext;
//
//public class PearlOreFeature extends Feature<DefaultFeatureConfig> {
//
//    public PearlOreFeature(Codec<DefaultFeatureConfig> configCodec) {
//        super(configCodec);
//    }
//
//
//    @Override
//    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
//        // Access the world and position information from the context
//        BlockPos pos = context.getOrigin();
//
//        // Check if the biome is an ocean biome
//        if (context.getWorld().getBiome(pos).isIn(BiomeTags.IS_OCEAN)) {
//            // Check if the block at the current position is sand
//            if (context.getWorld().getBlockState(pos).isOf(Blocks.SAND)) {
//                // Check if there are at least 5 blocks of water above the sand block
//                boolean hasEnoughWater = true;
//                int WATER_BLOCKS_ABOVE = 5;
//
//                for (int i = 1; i <= WATER_BLOCKS_ABOVE; i++) {
//                    if (!context.getWorld().getBlockState(pos.up(i)).getFluidState().isOf(Fluids.WATER)) {
//                        hasEnoughWater = false;
//
//                        break;
//                    }
//                }
//
//                // If all conditions are met, replace the sand block with your custom ore
//                if (hasEnoughWater) {
//                    context.getWorld().setBlockState(pos, ModBlocks.PEARL_ORE.getDefaultState(), 2);
//
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//}