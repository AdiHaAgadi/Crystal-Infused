package net.agadii.crystalinfused.tag;

import net.agadii.crystalinfused.CrystalInfused;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> CRYSTAL = TagKey.of(RegistryKeys.ITEM,  new Identifier(CrystalInfused.MOD_ID, "crystals"));
    public static final TagKey<Item> RAW_CRYSTAL = TagKey.of(RegistryKeys.ITEM,  new Identifier(CrystalInfused.MOD_ID, "raw_crystals"));


}