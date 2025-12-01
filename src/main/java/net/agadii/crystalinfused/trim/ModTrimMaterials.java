package net.agadii.crystalinfused.trim;

import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> SAPPHIRE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "sapphire"));
    public static final RegistryKey<ArmorTrimMaterial> RUBY = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "ruby"));
    public static final RegistryKey<ArmorTrimMaterial> TOPAZ = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "topaz"));
    public static final RegistryKey<ArmorTrimMaterial> AMBER = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "amber"));
    public static final RegistryKey<ArmorTrimMaterial> PERIDOT = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "peridot"));
    public static final RegistryKey<ArmorTrimMaterial> SUGILITE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "sugilite"));
    public static final RegistryKey<ArmorTrimMaterial> SPINEL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "spinel"));
    public static final RegistryKey<ArmorTrimMaterial> PEARL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CrystalInfused.MOD_ID, "pearl"));


    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, SAPPHIRE, Registries.ITEM.getEntry(ModItems.SAPPHIRE),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
        register(registerable, RUBY, Registries.ITEM.getEntry(ModItems.RUBY),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
        register(registerable, TOPAZ, Registries.ITEM.getEntry(ModItems.TOPAZ),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
        register(registerable, AMBER, Registries.ITEM.getEntry(ModItems.AMBER),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
        register(registerable, PERIDOT, Registries.ITEM.getEntry(ModItems.PERIDOT),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
        register(registerable, SUGILITE, Registries.ITEM.getEntry(ModItems.SUGILITE),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
        register(registerable, SPINEL, Registries.ITEM.getEntry(ModItems.SPINEL),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
        register(registerable, PEARL, Registries.ITEM.getEntry(ModItems.PEARL),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0f);
    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey,
                                 RegistryEntry<Item> item, Style style, float itemModelIndex) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(armorTrimKey.getValue().getPath(), item, itemModelIndex, Map.of(),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        registerable.register(armorTrimKey, trimMaterial);
    }
}