package net.agadii.crystalinfused.compat.rei.widgets.infusion;

import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.clothconfig2.api.animator.NumberAnimator;
import me.shedaniel.clothconfig2.api.animator.ValueAnimator;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.compat.rei.widgets.BoundedWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class InfusionBubblesWidget extends BoundedWidget {
    private Rectangle bounds;
    private double animationDuration = -1;
    private final Identifier TEXTURE = Identifier.of(CrystalInfused.MOD_ID, "textures/gui/rei/infusion_bubbles.png");
    private final NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
            .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
            .asFloat();

    public InfusionBubblesWidget(Rectangle bounds) {
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
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.darkBackgroundAlpha.update(delta);
        renderBackground(context, false, 1.0F);
        if (darkBackgroundAlpha.value() > 0) {
            renderBackground(context, true, this.darkBackgroundAlpha.value());
        }
    }

    public void renderBackground(DrawContext context, boolean dark, float alpha) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(770, 771, 1, 0);
        RenderSystem.blendFunc(770, 771);

        if (getAnimationDuration() > 0) {
            int height = MathHelper.ceil((System.currentTimeMillis() / (animationDuration / 19) % 19d));
            context.drawTexture(TEXTURE, getX(), getY() + 19 - height, 0, 19 - height, 10, height);
        } else {
            context.drawTexture(TEXTURE, getX(), getY(), 0, 0, 10, 19);
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }
}

