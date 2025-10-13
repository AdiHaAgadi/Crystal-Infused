package net.agadii.crystalinfused.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ColorCode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;

public class ExperienceDroppingFallingBlock extends ColoredFallingBlock {
    private final IntProvider experienceDropped;

    public ExperienceDroppingFallingBlock(ColorCode color, AbstractBlock.Settings settings) {
        this(color, ConstantIntProvider.create(0), settings);
    }

    public ExperienceDroppingFallingBlock(ColorCode color, IntProvider experience, Settings settings) {
        super(color, settings);
        this.experienceDropped = experience;
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (dropExperience) {
            this.dropExperienceWhenMined(world, pos, tool, this.experienceDropped);
        }
    }
}
