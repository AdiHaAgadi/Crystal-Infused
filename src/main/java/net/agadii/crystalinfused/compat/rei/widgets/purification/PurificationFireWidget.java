package net.agadii.crystalinfused.compat.rei.widgets.purification;

import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.clothconfig2.api.animator.NumberAnimator;
import me.shedaniel.clothconfig2.api.animator.ValueAnimator;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.compat.rei.widgets.BoundedWidget;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PurificationFireWidget extends BoundedWidget {
    private Rectangle bounds;
    private double animationDuration = -1;
    private final Identifier TEXTURE = new Identifier(CrystalInfused.MOD_ID, "textures/gui/rei/purification_fire.png");
    private final NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
            .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
            .asFloat();

    public PurificationFireWidget(Rectangle bounds) {
        this.bounds = new Rectangle(Objects.requireNonNull(bounds));
    }

    @Override
    public double getAnimationDuration() {
        return animationDuration;
    }

    @Override
    public void setAnimationDuration(double animationDurationMS) {
        this.animationDuration = animationDurationMS;
        if (this.animationDuration <= 0)
            this.animationDuration = -1;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.darkBackgroundAlpha.update(delta);
        renderBackground(matrices, false, 1.0F);
        if (darkBackgroundAlpha.value() > 0) {
            renderBackground(matrices, true, this.darkBackgroundAlpha.value());
        }
    }

    public void renderBackground(MatrixStack matrices, boolean dark, float alpha) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(770, 771, 1, 0);
        RenderSystem.blendFunc(770, 771);
        if (getAnimationDuration() > 0) {
            int height = 14 - MathHelper.ceil((System.currentTimeMillis() / (animationDuration / 14) % 14d));
            drawTexture(matrices, getX(), getY() + 14 - height, 0, 14 - height, 21, height);
        } else {
            drawTexture(matrices, getX(), getY(), 0, 0, 21, 14);
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }
}
