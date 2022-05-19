package listeners;

import screen.Window;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ComponentListen implements ComponentListener {
    @Override
    public void componentResized(ComponentEvent e) {
        // If window resized
        Window.resizeComponents(e.getComponent().getWidth(), e.getComponent().getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}