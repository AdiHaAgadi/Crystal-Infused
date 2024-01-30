package net.agadii.crystalinfused.item;

import net.agadii.crystalinfused.CrystalInfused;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SAPPHIRE = registerItem("sapphire",
            new Item(new FabricItemSettings()));
    public static final Item RUBY = registerItem("ruby",
            new Item(new FabricItemSettings()));
    public static final Item TOPAZ = registerItem("topaz",
            new Item(new FabricItemSettings()));
    public static final Item AMBER = registerItem("amber",
            new Item(new FabricItemSettings()));
    public static final Item PERIDOT = registerItem("peridot",
            new Item(new FabricItemSettings()));
    public static final Item SUGILITE = registerItem("sugilite",
            new Item(new FabricItemSettings()));
    public static final Item SPINEL = registerItem("spinel",
            new Item(new FabricItemSettings()));
    public static final Item PEARL = registerItem("pearl",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CrystalInfused.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.INGREDIENTS, SAPPHIRE);
        addToItemGroup(ItemGroups.INGREDIENTS, RUBY);
        addToItemGroup(ItemGroups.INGREDIENTS, TOPAZ);
        addToItemGroup(ItemGroups.INGREDIENTS, AMBER);
        addToItemGroup(ItemGroups.INGREDIENTS, PERIDOT);
        addToItemGroup(ItemGroups.INGREDIENTS, SUGILITE);
        addToItemGroup(ItemGroups.INGREDIENTS, SPINEL);
        addToItemGroup(ItemGroups.INGREDIENTS, PEARL);

        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, SAPPHIRE);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RUBY);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, TOPAZ);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, AMBER);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, PERIDOT);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, SUGILITE);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, SPINEL);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, PEARL);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        CrystalInfused.LOGGER.info("Registering Mod Items for " + CrystalInfused.MOD_ID);

        addItemsToItemGroup();
    }
}
