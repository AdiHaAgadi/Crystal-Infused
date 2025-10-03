package net.agadii.crystalinfused;

import net.agadii.crystalinfused.particle.InfusionParticle;
import net.agadii.crystalinfused.particle.ModParticles;
import net.agadii.crystalinfused.screen.CrystalInfusionScreen;
import net.agadii.crystalinfused.screen.CrystalPurificationScreen;
import net.agadii.crystalinfused.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CrystalInfusedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.CRYSTAL_PURIFICATION_SCREEN_HANDLER, CrystalPurificationScreen::new);
        HandledScreens.register(ModScreenHandlers.CRYSTAL_INFUSION_SCREEN_HANDLER, CrystalInfusionScreen::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.INFUSION_PARTICLE, InfusionParticle.Factory::new);

    }
}
