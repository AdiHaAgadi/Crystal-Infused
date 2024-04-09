package net.agadii.crystalinfused.compat.rei;

import com.google.common.collect.Lists;
import me.shedaniel.math.Dimension;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.block.entity.CrystalPurifierBlockEntity;
import net.agadii.crystalinfused.compat.rei.widgets.PurificationFire;
import net.agadii.crystalinfused.compat.rei.widgets.PurificationFireWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import me.shedaniel.math.Rectangle;
import me.shedaniel.math.Point;
import java.util.LinkedList;
import java.util.List;

public class CrystalPurificationCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(CrystalInfused.MOD_ID, "textures/gui/crystal_purifier_rei_gui.png");
    public static final CategoryIdentifier<CrystalPurificationDisplay> CRYSTAL_PURIFICATION =
            CategoryIdentifier.of(CrystalInfused.MOD_ID, "crystal_purification");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return CRYSTAL_PURIFICATION;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("tag.crystalinfused.crystal_purification");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRYSTAL_PURIFIER.asItem().getDefaultStack());
    }

    public List<Widget> setupDisplay(CrystalPurificationDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 86, 47)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 17))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 116, startPoint.y + 35))
                .entries(display.getOutputEntries().get(0)));


        return widgets;
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.y + 5);
        double purificationTime = CrystalPurifierBlockEntity.getPurificationTime();

        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 86, 41)));

        PurificationFire purificationFireWidget = new PurificationFireWidget(new Rectangle(
                new Point(startPoint.x + 2, startPoint.y + 24),
                new Dimension(21, 14)));
        purificationFireWidget.setAnimationDuration(10000);

        widgets.add(purificationFireWidget);
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 28, startPoint.y + 13))
                .animationDurationTicks(purificationTime));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 65, startPoint.y + 13))
                .entries(display.getOutputEntries().get(0))
                .disableBackground()
                .markOutput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 5, startPoint.y + 5))
                .entries(display.getInputEntries().get(0))
                .markInput());
        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 49;
    }
}
