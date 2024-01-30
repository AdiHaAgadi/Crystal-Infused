package net.agadii.crystalinfused.item;

import net.agadii.crystalinfused.CrystalInfused;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup CRYSTAL_INFUSED;

    public static void registerItemGroups() {
        CRYSTAL_INFUSED = FabricItemGroup.builder(new Identifier(CrystalInfused.MOD_ID, "crystal_infused"))
                .displayName(Text.literal("Crystal Infused"))
                .icon(() -> new ItemStack(ModItems.SPINEL)).build();
    }
}
