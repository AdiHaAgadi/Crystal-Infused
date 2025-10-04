package net.agadii.crystalinfused.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class InfusionParticle extends SpriteBillboardParticle {
    private final double angleSpeed;
    private final double spiralRadius;

    protected InfusionParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0, 0, 0);
        this.setSprite(spriteProvider);

        this.maxAge = 30;
        this.scale = 0.2F;
        this.gravityStrength = 0;

        this.angleSpeed = 0.2;
        this.spiralRadius = 0.15;
    }

    @Override
    public void tick() {
        super.tick();

        double progress = (double) this.age / this.maxAge;
        double angle = this.age * this.angleSpeed;

        this.x += Math.cos(angle) * this.spiralRadius * (1 - progress);
        this.z += Math.sin(angle) * this.spiralRadius * (1 - progress);
        this.y += 0.02; // slowly moves upward
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }


        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new InfusionParticle(world, x, y, z, this.spriteProvider);
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
}
