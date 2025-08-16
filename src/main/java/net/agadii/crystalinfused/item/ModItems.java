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

    public static final ItemStack BANE_OF_ARTHROPODS_VI_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.BANE_OF_ARTHROPODS, 5));
    public static final ItemStack EFFICIENCY_VI_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 6));
    public static final ItemStack FEATHER_FALLING_V_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.FEATHER_FALLING, 5));
    public static final ItemStack FIRE_ASPECT_III_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.FIRE_ASPECT, 3));
    public static final ItemStack FIRE_PROTECTION_V_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.FIRE_PROTECTION, 5));
    public static final ItemStack FORTUNE_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.FORTUNE, 4));
    public static final ItemStack FROST_WALKER_III_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.FROST_WALKER, 3));
    public static final ItemStack IMPALING_VI_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.IMPALING, 6));
    public static final ItemStack KNOCKBACK_III_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.KNOCKBACK, 3));
    public static final ItemStack LOOTING_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.LOOTING, 4));
    public static final ItemStack LOYALTY_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.LOYALTY, 4));
    public static final ItemStack LUCK_OF_THE_SEA_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.LUCK_OF_THE_SEA, 4));
    public static final ItemStack LURE_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.LURE, 4));
    public static final ItemStack PIERCING_V_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.PIERCING, 5));
    public static final ItemStack POWER_VI_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.POWER, 6));
    public static final ItemStack PROJECTILE_PROTECTION_V_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.PROJECTILE_PROTECTION, 5));
    public static final ItemStack PROTECTION_V_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.PROTECTION, 5));
    public static final ItemStack PUNCH_III_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.PUNCH, 3));
    public static final ItemStack QUICK_CHARGE_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.QUICK_CHARGE, 4));
    public static final ItemStack RESPIRATION_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.RESPIRATION, 4));
    public static final ItemStack SHARPNESS_VI_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, 6));
    public static final ItemStack SMITE_VI_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.SMITE, 6));
    public static final ItemStack SOUL_SPEED_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.SOUL_SPEED, 4));
    public static final ItemStack SWEEPING_EDGE_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.SWEEPING, 4));
    public static final ItemStack SWIFT_SNEAK_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.SWIFT_SNEAK, 4));
    public static final ItemStack THORNS_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.THORNS, 4));
    public static final ItemStack UNBREAKING_IV_BOOK = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 4));


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
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_SAPPHIRE);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_RUBY);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_TOPAZ);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_AMBER);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_PERIDOT);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_SUGILITE);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_SPINEL);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_PEARL);

        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, SAPPHIRE);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RUBY);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, TOPAZ);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, AMBER);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, PERIDOT);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, SUGILITE);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, SPINEL);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, PEARL);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_SAPPHIRE);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_RUBY);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_TOPAZ);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_AMBER);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_PERIDOT);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_SUGILITE);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_SPINEL);
        addToItemGroup(ModItemGroup.CRYSTAL_INFUSED, RAW_PEARL);

        addToItemStackGroup(ItemGroups.INGREDIENTS, BANE_OF_ARTHROPODS_VI_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, EFFICIENCY_VI_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, FEATHER_FALLING_V_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, FIRE_ASPECT_III_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, FIRE_PROTECTION_V_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, FORTUNE_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, FROST_WALKER_III_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, IMPALING_VI_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, KNOCKBACK_III_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, LOOTING_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, LOYALTY_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, LUCK_OF_THE_SEA_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, LURE_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, PIERCING_V_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, POWER_VI_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, PROJECTILE_PROTECTION_V_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, PROTECTION_V_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, PUNCH_III_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, QUICK_CHARGE_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, RESPIRATION_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, SHARPNESS_VI_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, SMITE_VI_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, SOUL_SPEED_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, SWEEPING_EDGE_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, SWIFT_SNEAK_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, THORNS_IV_BOOK);
        addToItemStackGroup(ItemGroups.INGREDIENTS, UNBREAKING_IV_BOOK);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    private static void addToItemStackGroup(ItemGroup group, ItemStack item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        CrystalInfused.LOGGER.info("Registering Mod Items for " + CrystalInfused.MOD_ID);
        addItemsToItemGroup();
    }
}
