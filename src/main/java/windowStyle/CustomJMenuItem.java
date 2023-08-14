package windowStyle;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CustomJMenuItem extends JMenuItem {

    private static final Color colorBackgroundMenuItem = new Color(56, 56, 56);
    private static final Color colorForegroundMenuItem = new Color(215, 215, 215);

    public CustomJMenuItem(String s, String image) {
        setText(s);
        setBackground(colorBackgroundMenuItem);
        setForeground(colorForegroundMenuItem);
        setPreferredSize(new Dimension(140, 20));
        setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        if (image != null) {
            Image imgFile = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(image))).getImage();
            Image resizedImgFile = imgFile.getScaledInstance(16, 16,  Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(resizedImgFile));
        }
    }
}
