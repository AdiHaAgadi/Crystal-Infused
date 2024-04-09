package net.agadii.crystalinfused.compat.rei.widgets;

import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;

public abstract class PurificationFire extends WidgetWithBounds {
    public final int getX() {
        return getBounds().getX();
    }

    public final int getY() {
        return getBounds().getY();
    }

    public abstract double getAnimationDuration();

    public abstract void setAnimationDuration(double animationDurationMS);

    public final PurificationFire animationDurationMS(double animationDurationMS) {
        setAnimationDuration(animationDurationMS);
        return this;
    }

    public final PurificationFire animationDurationTicks(double animationDurationTicks) {
        return animationDurationMS(animationDurationTicks * 50);
    }

    public final PurificationFire disableAnimation() {
        return animationDurationMS(-1);
    }
}
