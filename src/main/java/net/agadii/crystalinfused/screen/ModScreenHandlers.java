package net.agadii.crystalinfused.screen;

import net.agadii.crystalinfused.CrystalInfused;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<CrystalPurificationScreenHandler> CRYSTAL_PURIFICATION_SCREEN_HANDLER =
            new ExtendedScreenHandlerType<>(CrystalPurificationScreenHandler::new);

    public static ScreenHandlerType<CrystalInfusionScreenHandler> CRYSTAL_INFUSION_SCREEN_HANDLER =
            new ExtendedScreenHandlerType<>(CrystalInfusionScreenHandler::new);

    public static void registerAllScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(CrystalInfused.MOD_ID, "crystal_purifier"),
                CRYSTAL_PURIFICATION_SCREEN_HANDLER);

        Registry.register(Registries.SCREEN_HANDLER, new Identifier(CrystalInfused.MOD_ID, "crystal_infuser"),
                CRYSTAL_INFUSION_SCREEN_HANDLER);
    }
}