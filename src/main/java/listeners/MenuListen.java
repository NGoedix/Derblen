package listeners;

import screen.Window;

import javax.swing.*;
import javax.swing.event.MenuEvent;

public class MenuListen implements javax.swing.event.MenuListener {
    @Override
    public void menuSelected(MenuEvent e) {
        if (!(e.getSource() instanceof JMenu)) return;

        JMenu menu = (JMenu) e.getSource();
        if (menu.getText().equals("Arduino")) {
            Window.menuRefreshArduino();
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {}

    @Override
    public void menuCanceled(MenuEvent e) {}
}
