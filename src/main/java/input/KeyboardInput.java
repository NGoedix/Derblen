package input;

import screen.Render;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyboardInput implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch(key) {
            case KeyEvent.VK_R:
                Render.vTarget[0] += 0.1;
                break;
            case KeyEvent.VK_T:
                Render.vTarget[1] += 0.1;
                break;
            case KeyEvent.VK_Y:
                Render.vTarget[2] += 0.1;
                break;
            case KeyEvent.VK_U:
                Render.vTarget[3] += 0.1;
                break;
            case KeyEvent.VK_F:
                Render.vUp[0] += 0.1;
                break;
            case KeyEvent.VK_G:
                Render.vUp[1] += 0.1;
                break;
            case KeyEvent.VK_H:
                Render.vUp[2] += 0.1;
                break;
            case KeyEvent.VK_J:
                Render.vUp[3] += 0.1;
                break;
        }

        Render.getInstance().render();
        Render.getInstance().render();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
