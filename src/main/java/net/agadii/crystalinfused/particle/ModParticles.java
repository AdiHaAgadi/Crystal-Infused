package net.agadii.crystalinfused.particle;

import net.agadii.crystalinfused.CrystalInfused;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType INFUSION_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(CrystalInfused.MOD_ID, "infusion_particle"),
                INFUSION_PARTICLE);
    }
}
