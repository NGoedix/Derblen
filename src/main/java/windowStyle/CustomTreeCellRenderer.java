package windowStyle;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        String stringValue = tree.convertValueToText(value, sel, expanded, leaf, row, hasFocus);

        this.hasFocus = hasFocus;
        setText(stringValue);

        Color fg = new Color(227, 227, 227);

        setForeground(fg);

        Icon icon;
        if (leaf) {
            Image originalImage = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("icons/import.png")).getImage();
            Image resizedImage = originalImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resizedImage);
        } else if (expanded) {
            icon = getOpenIcon();
        } else {
            icon = getClosedIcon();
        }

        if (!tree.isEnabled()) {
            setEnabled(false);
            LookAndFeel laf = UIManager.getLookAndFeel();
            Icon disabledIcon = laf.getDisabledIcon(tree, icon);
            if (disabledIcon != null) icon = disabledIcon;
            setDisabledIcon(icon);
        } else {
            setEnabled(true);
            setIcon(icon);
        }
        setComponentOrientation(tree.getComponentOrientation());

        selected = sel;

        return this;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        if ((getTextSelectionColor() instanceof UIResource)) {
            setTextSelectionColor(new Color(227, 227, 227));
        }
        if ((getTextNonSelectionColor() instanceof UIResource)) {
            setTextNonSelectionColor(new Color(227, 227, 227));
        }
        if ((getBackgroundSelectionColor() instanceof UIResource)) {
            setBackgroundSelectionColor(new Color(0, 100, 150, 75));
//            setBackgroundSelectionColor(new Color(0, 0, 0, 0));
        }
        if ((getBackgroundNonSelectionColor() instanceof UIResource)) {
            setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
        }
        if ((getBorderSelectionColor() instanceof UIResource)) {
            setBorderSelectionColor(new Color(0, 0, 0, 0));
        }
    }
}
