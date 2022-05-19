package windowStyle;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import java.awt.*;

public class DarkTheme extends DefaultMetalTheme {

    public ColorUIResource getWindowTitleInactiveBackground() {
        return new ColorUIResource(new Color(58, 58, 58));
    }

    public ColorUIResource getWindowTitleBackground() {
        return new ColorUIResource(new Color(58, 58, 58));
    }

    public ColorUIResource getPrimaryControlHighlight() {
        return new ColorUIResource(new Color(58, 58, 58));
    }

    public ColorUIResource getPrimaryControlDarkShadow() {
        return new ColorUIResource(new Color(58, 58, 58));
    }

    public ColorUIResource getPrimaryControl() {
        return new ColorUIResource(new Color(58, 58, 58));
    }

    public ColorUIResource getControlHighlight() {
        return new ColorUIResource(new Color(58, 58, 58));
    }

    public ColorUIResource getControlDarkShadow() {
        return new ColorUIResource(new Color(58, 58, 58));
    }

    public ColorUIResource getControl() {
        return new ColorUIResource(new Color(58, 58, 58));
    }
}
