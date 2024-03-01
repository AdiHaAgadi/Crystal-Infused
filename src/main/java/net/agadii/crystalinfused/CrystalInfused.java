package net.agadii.crystalinfused;

import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.block.entity.ModBlockEntities;
import net.agadii.crystalinfused.item.ModItemGroup;
import net.agadii.crystalinfused.item.ModItems;
import net.agadii.crystalinfused.screen.ModScreenHandlers;
import net.agadii.crystalinfused.world.gen.ModWorldGeneration;
import net.agadii.crystalinfused.world.gen.feature.ModFeatures;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrystalInfused implements ModInitializer {
	public static final String MOD_ID = "crystalinfused";
    public static final Logger LOGGER = LoggerFactory.getLogger("crystalinfused");

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFeatures.registerModFeatures();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerAllScreenHandlers();

		ModWorldGeneration.generateModWorldGen();
	}
}