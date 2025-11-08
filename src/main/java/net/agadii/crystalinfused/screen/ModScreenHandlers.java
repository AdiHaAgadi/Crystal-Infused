package net.agadii.crystalinfused.screen;

import net.agadii.crystalinfused.CrystalInfused;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static ScreenHandlerType<CrystalPurificationScreenHandler> CRYSTAL_PURIFICATION_SCREEN_HANDLER =
            new ExtendedScreenHandlerType<>(
                    CrystalPurificationScreenHandler::new,
                    BlockPos.PACKET_CODEC
            );

    public static ScreenHandlerType<CrystalInfusionScreenHandler> CRYSTAL_INFUSION_SCREEN_HANDLER =
            new ExtendedScreenHandlerType<>(
                    CrystalInfusionScreenHandler::new,
                    BlockPos.PACKET_CODEC
            );

    public static void registerAllScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CrystalInfused.MOD_ID, "crystal_purifier"),
                CRYSTAL_PURIFICATION_SCREEN_HANDLER);

        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CrystalInfused.MOD_ID, "crystal_infuser"),
                CRYSTAL_INFUSION_SCREEN_HANDLER);
    }
}