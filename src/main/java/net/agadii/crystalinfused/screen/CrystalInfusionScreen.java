package net.agadii.crystalinfused.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.agadii.crystalinfused.CrystalInfused;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CrystalInfusionScreen extends HandledScreen<CrystalInfusionScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(CrystalInfused.MOD_ID, "textures/gui/crystal_infuser_gui.png");

    private final PlayerInventory playerInventory; // Store player inventory


    public CrystalInfusionScreen(CrystalInfusionScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.playerInventory = inventory; // Initialize player inventory
        backgroundHeight = 224;
        playerInventoryTitleY = backgroundHeight - 94;


    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrows(matrices, x, y);
        renderFuelProgressBubbles(matrices, x, y);
        renderFuelTank(matrices, x, y);
    }

    private void renderProgressArrows(MatrixStack matrices, int x, int y) {
        if (handler.isCrafting()) {
            int scaledProgress = handler.getScaledArrowsProgress();
            drawTexture(matrices, x + 81, y + 44, 176, 30, 14, scaledProgress);
        }
    }

    private void renderFuelProgressBubbles(MatrixStack matrices, int x, int y) {
        if (handler.isCrafting()) {
            int scaledProgress = handler.getScaledBubblesProgress();
            drawTexture(matrices, x + 83, y + 101 - scaledProgress, 176, 19 - scaledProgress, 10, scaledProgress);
        }
    }

    private void renderFuelTank(MatrixStack matrices, int x, int y) {
        int scaledFuelProgress = handler.getScaledFuelTankProgress();
        drawTexture(matrices, x + 78, y + 102, 176, 57, scaledFuelProgress, 6);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
