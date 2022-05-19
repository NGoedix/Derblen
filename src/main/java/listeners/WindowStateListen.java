package listeners;

import screen.Window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class WindowStateListen implements WindowStateListener {
    @Override
    public void windowStateChanged(WindowEvent e) {
        if (e.getNewState() == 6 || e.getNewState() == 0) {
            Window.resizeComponents(e.getComponent().getWidth(), e.getComponent().getHeight());
        }
    }
}
