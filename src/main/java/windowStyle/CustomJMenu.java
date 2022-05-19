package windowStyle;

import javax.swing.*;
import java.awt.*;

public class CustomJMenu extends JMenu {

    private static final Color colorForegroundMenuItem = new Color(215, 215, 215);
    private static final Color colorBackgroundMenuItem = new Color(56, 56, 56);


    public CustomJMenu(String s, boolean parent) {
        setBackground(colorBackgroundMenuItem);
        setForeground(colorForegroundMenuItem);
        setText(s);
        if (parent) {
            setOpaque(false);
            setBorderPainted(false);
        }
        setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
    }
}
