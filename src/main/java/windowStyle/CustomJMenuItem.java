package windowStyle;

import javax.swing.*;
import java.awt.*;

public class CustomJMenuItem extends JMenuItem {

    private static final Color colorBackgroundMenuItem = new Color(56, 56, 56);
    private static final Color colorForegroundMenuItem = new Color(215, 215, 215);

    public CustomJMenuItem(String s) {
        setText(s);
        setBackground(colorBackgroundMenuItem);
        setForeground(colorForegroundMenuItem);
        setPreferredSize(new Dimension(140, 20));
        setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
    }
}
