package net.agadii.crystalinfused.block.entity;

import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<CrystalPurifierBlockEntity> CRYSTAL_PURIFIER;
    public static BlockEntityType<CrystalInfuserBlockEntity> CRYSTAL_INFUSER;


    public static void registerBlockEntities() {
        CRYSTAL_PURIFIER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(CrystalInfused.MOD_ID, "crystal_purifier"),
                BlockEntityType.Builder.create(CrystalPurifierBlockEntity::new,
                        ModBlocks.CRYSTAL_PURIFIER).build(null));

        CRYSTAL_INFUSER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(CrystalInfused.MOD_ID, "crystal_infuser"),
                BlockEntityType.Builder.create(CrystalInfuserBlockEntity::new,
                        ModBlocks.CRYSTAL_INFUSER).build(null));
    }
}
