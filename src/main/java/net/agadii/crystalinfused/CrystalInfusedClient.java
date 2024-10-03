package net.agadii.crystalinfused;

import net.agadii.crystalinfused.screen.CrystalInfusionScreen;
import net.agadii.crystalinfused.screen.CrystalPurificationScreen;
import net.agadii.crystalinfused.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CrystalInfusedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.CRYSTAL_PURIFICATION_SCREEN_HANDLER, CrystalPurificationScreen::new);
        HandledScreens.register(ModScreenHandlers.CRYSTAL_INFUSION_SCREEN_HANDLER, CrystalInfusionScreen::new);
    }
}
