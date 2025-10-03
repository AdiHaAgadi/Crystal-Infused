package net.agadii.crystalinfused.compat.rei.categories;

import com.google.common.collect.Lists;
import me.shedaniel.math.Dimension;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.agadii.crystalinfused.CrystalInfused;
import net.agadii.crystalinfused.block.ModBlocks;
import net.agadii.crystalinfused.block.entity.CrystalInfuserBlockEntity;
import net.agadii.crystalinfused.compat.rei.displays.CrystalInfusionDisplay;
import net.agadii.crystalinfused.compat.rei.widgets.BoundedWidget;
import net.agadii.crystalinfused.compat.rei.widgets.infusion.InfusionArrowsWidget;
import net.agadii.crystalinfused.compat.rei.widgets.infusion.InfusionBubblesWidget;
import net.agadii.crystalinfused.compat.rei.widgets.purification.PurificationFireWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import me.shedaniel.math.Rectangle;
import me.shedaniel.math.Point;

import java.util.Collections;
import java.util.List;

public class CrystalInfusionCategory implements DisplayCategory<BasicDisplay> {
    public static final CategoryIdentifier<CrystalInfusionDisplay> CRYSTAL_INFUSION =
            CategoryIdentifier.of(CrystalInfused.MOD_ID, "crystal_infusion");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return CRYSTAL_INFUSION;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("tag.crystalinfused.crystal_infusion");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRYSTAL_INFUSER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Identifier TEXTURE = new Identifier(CrystalInfused.MOD_ID, REIRuntime.getInstance().isDarkThemeEnabled()
                ? "textures/gui/rei/crystal_infuser_gui_dark.png"
                : "textures/gui/rei/crystal_infuser_gui.png"
        );

        Point startPoint = new Point(bounds.getCenterX() - 50, bounds.y + 7);

        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 100, 100)));

        BoundedWidget infusionArrowsWidget = new InfusionArrowsWidget(new Rectangle(
                new Point(startPoint.x + 43, startPoint.y + 22),
                new Dimension(14, 18)));
        infusionArrowsWidget.setAnimationDuration(10000);

        BoundedWidget infusionbubblesWidget = new InfusionBubblesWidget(new Rectangle(
                new Point(startPoint.x + 45, startPoint.y + 60),
                new Dimension(10, 19)));
        infusionbubblesWidget.setAnimationDuration(10000);

        widgets.add(infusionArrowsWidget);
        widgets.add(infusionbubblesWidget);

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 42, startPoint.y + 3)) // book slot
                .entries(display.getInputEntries().get(0))
                .disableBackground()
                .markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 3, startPoint.y + 42)) // gem 1 slot
                .entries(display.getInputEntries().get(1))
                .markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 81, startPoint.y + 42)) // gem 2 slot
                .entries(display.getInputEntries().get(2))
                .markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 42, startPoint.y + 82)) // fuel slot
                .entries(Collections.singleton(EntryStacks.of(Items.NETHER_STAR)))
                .markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 42, startPoint.y + 42)) // output slot
                .entries(display.getOutputEntries().get(0))
                .disableBackground()
                .markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 115;
    }
}
