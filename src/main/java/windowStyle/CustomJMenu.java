package windowStyle;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CustomJMenu extends JMenu {

    private static final Color colorForegroundMenuItem = new Color(215, 215, 215);
    private static final Color colorBackgroundMenuItem = new Color(56, 56, 56);


    public CustomJMenu(String s, boolean parent, String image) {
        setBackground(colorBackgroundMenuItem);
        setForeground(colorForegroundMenuItem);
        setText(s);
        if (parent) {
            setOpaque(false);
            setBorderPainted(false);
        }
        setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        if (image != null) {
            Image imgFile = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(image))).getImage();
            Image resizedImgFile = imgFile.getScaledInstance(16, 16,  Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(resizedImgFile));
        }
    }
}
