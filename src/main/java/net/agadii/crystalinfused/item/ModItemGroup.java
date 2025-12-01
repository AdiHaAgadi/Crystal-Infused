package net.agadii.crystalinfused.item;

import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup CRYSTAL_INFUSED = Registry.register(Registries.ITEM_GROUP, Identifier.of(CrystalInfused.MOD_ID, "crystal_infused"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.crystal_infused"))
                    .icon(() -> new ItemStack(ModItems.SPINEL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SAPPHIRE);
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.TOPAZ);
                        entries.add(ModItems.AMBER);
                        entries.add(ModItems.PERIDOT);
                        entries.add(ModItems.SUGILITE);
                        entries.add(ModItems.SPINEL);
                        entries.add(ModItems.PEARL);

                        entries.add(ModItems.RAW_SAPPHIRE);
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModItems.RAW_TOPAZ);
                        entries.add(ModItems.RAW_AMBER);
                        entries.add(ModItems.RAW_PERIDOT);
                        entries.add(ModItems.RAW_SUGILITE);
                        entries.add(ModItems.RAW_SPINEL);
                        entries.add(ModItems.RAW_PEARL);

                        entries.add(ModBlocks.SAPPHIRE_BLOCK);
                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(ModBlocks.TOPAZ_BLOCK);
                        entries.add(ModBlocks.AMBER_BLOCK);
                        entries.add(ModBlocks.PERIDOT_BLOCK);
                        entries.add(ModBlocks.SUGILITE_BLOCK);
                        entries.add(ModBlocks.SPINEL_BLOCK);
                        entries.add(ModBlocks.PEARL_BLOCK);

                        entries.add(ModBlocks.SAPPHIRE_ORE);
                        entries.add(ModBlocks.RUBY_ORE);
                        entries.add(ModBlocks.TOPAZ_ORE);
                        entries.add(ModBlocks.AMBER_ORE);
                        entries.add(ModBlocks.PERIDOT_ORE);
                        entries.add(ModBlocks.SUGILITE_ORE);
                        entries.add(ModBlocks.SPINEL_ORE);
                        entries.add(ModBlocks.PEARL_ORE);

                        entries.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
                        entries.add(ModBlocks.DEEPSLATE_RUBY_ORE);
                        entries.add(ModBlocks.DEEPSLATE_TOPAZ_ORE);
                        entries.add(ModBlocks.DEEPSLATE_AMBER_ORE);
                        entries.add(ModBlocks.DEEPSLATE_PERIDOT_ORE);

                        entries.add(ModBlocks.CRYSTAL_PURIFIER);
                        entries.add(ModBlocks.CRYSTAL_INFUSER);
                    }).build());

    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ModItems.SAPPHIRE);
            entries.add(ModItems.RUBY);
            entries.add(ModItems.TOPAZ);
            entries.add(ModItems.AMBER);
            entries.add(ModItems.PERIDOT);
            entries.add(ModItems.SUGILITE);
            entries.add(ModItems.SPINEL);
            entries.add(ModItems.PEARL);

            entries.add(ModItems.RAW_SAPPHIRE);
            entries.add(ModItems.RAW_RUBY);
            entries.add(ModItems.RAW_TOPAZ);
            entries.add(ModItems.RAW_AMBER);
            entries.add(ModItems.RAW_PERIDOT);
            entries.add(ModItems.RAW_SUGILITE);
            entries.add(ModItems.RAW_SPINEL);
            entries.add(ModItems.RAW_PEARL);
        });
    }
}
