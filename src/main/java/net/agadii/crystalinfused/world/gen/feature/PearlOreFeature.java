package net.agadii.crystalinfused.world.gen.feature;

import com.mojang.serialization.Codec;
import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class PearlOreFeature extends Feature<DefaultFeatureConfig> {
    private int WATER_BLOCKS_ABOVE_ORE = 5;

    public PearlOreFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        BlockPos surfacePos = context.getWorld().getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos);
        int waterCount = 0;

        // Check for at least 5 water blocks above the surface
        for (int i = 1; i <= this.WATER_BLOCKS_ABOVE_ORE; i++) {
            if (context.getWorld().getBlockState(surfacePos.up(i)).isOf(Blocks.WATER)) {
                waterCount++;
            }
        }

        // If conditions are met, generate 'pearl ore' blocks
        if (waterCount >= this.WATER_BLOCKS_ABOVE_ORE && context.getWorld().getBlockState(surfacePos).isOf(Blocks.SAND)) {
            context.getWorld().setBlockState(surfacePos, ModBlocks.PEARL_ORE.getDefaultState(), 2);
            return true; // Return true if the feature generated successfully
        }

        return false; // Return false if the feature did not generate
    }
}