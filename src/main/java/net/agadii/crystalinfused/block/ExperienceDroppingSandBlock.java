package net.agadii.crystalinfused.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.BlockView;

public class ExperienceDroppingSandBlock extends SandBlock {
    private final IntProvider experienceDropped;

    public ExperienceDroppingSandBlock(int color, AbstractBlock.Settings settings) {
        this(color, settings, ConstantIntProvider.create(0));
    }

    public ExperienceDroppingSandBlock(int color, Settings settings, IntProvider experience) {
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
