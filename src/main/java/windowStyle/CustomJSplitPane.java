package windowStyle;

import javax.swing.*;
import java.awt.*;

public class CustomJSplitPane extends JSplitPane {

    public CustomJSplitPane() {
        super();
        this.setDividerSize(5);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(131, 131, 131));
        if (this.getOrientation() == JSplitPane.HORIZONTAL_SPLIT)  { // Horizontal
            g.fillRect(this.getDividerLocation(), this.getY(), this.getDividerSize(), this.getHeight());
        } else { // Vertical
            g.fillRect(0, this.getDividerLocation(), this.getWidth(), this.getDividerSize());
        }
    }
}
