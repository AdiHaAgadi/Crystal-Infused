package net.agadii.crystalinfused.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.agadii.crystalinfused.CrystalInfused;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CrystalPurificationScreen extends HandledScreen<CrystalPurificationScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(CrystalInfused.MOD_ID, "textures/gui/crystal_purifier_gui.png");

    public CrystalPurificationScreen(CrystalPurificationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
        renderFuelProgressFlame(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE, + 80, y + 34, 176, 0,  handler.getScaledProgress(), 16);
        }
    }

    private void renderFuelProgressFlame(DrawContext context, int x, int y) {
        int scaledProgress = handler.getScaledFuelProgress();
        context.drawTexture(TEXTURE, x + 53, y + 49 - scaledProgress, 176, 58 - scaledProgress,  21, scaledProgress);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}