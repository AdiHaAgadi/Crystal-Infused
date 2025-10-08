package net.agadii.crystalinfused.item;

import net.agadii.crystalinfused.CrystalInfused;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
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

    public static final Item RAW_SAPPHIRE = registerItem("raw_sapphire",
            new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby",
            new Item(new FabricItemSettings()));
    public static final Item RAW_TOPAZ = registerItem("raw_topaz",
            new Item(new FabricItemSettings()));
    public static final Item RAW_AMBER = registerItem("raw_amber",
            new Item(new FabricItemSettings()));
    public static final Item RAW_PERIDOT = registerItem("raw_peridot",
            new Item(new FabricItemSettings()));
    public static final Item RAW_SUGILITE = registerItem("raw_sugilite",
            new Item(new FabricItemSettings()));
    public static final Item RAW_SPINEL = registerItem("raw_spinel",
            new Item(new FabricItemSettings()));
    public static final Item RAW_PEARL = registerItem("raw_pearl",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CrystalInfused.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CrystalInfused.LOGGER.info("Registering Mod Items for " + CrystalInfused.MOD_ID);
    }
}
