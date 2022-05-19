package windowStyle;

import javax.swing.*;
import java.awt.*;

public class CustomSplitPaneUI extends javax.swing.plaf.SplitPaneUI {

    @Override
    public void resetToPreferredSizes(JSplitPane jc) {
        jc.getUI().resetToPreferredSizes(jc);
    }

    @Override
    public void setDividerLocation(JSplitPane jc, int location) {
        jc.getUI().setDividerLocation(jc, location);
    }

    @Override
    public int getDividerLocation(JSplitPane jc) {
        return jc.getUI().getDividerLocation(jc);
    }

    @Override
    public int getMinimumDividerLocation(JSplitPane jc) {
        return jc.getUI().getMinimumDividerLocation(jc);
    }

    @Override
    public int getMaximumDividerLocation(JSplitPane jc) {
        return jc.getUI().getMaximumDividerLocation(jc);
    }

    @Override
    public void finishedPaintingChildren(JSplitPane jc, Graphics g) {
        jc.getUI().finishedPaintingChildren(jc, g);
    }
}
