package input;

import screen.Render;

import java.awt.event.*;

public class MouseInput extends MouseAdapter implements MouseListener, MouseMotionListener, MouseWheelListener {

    private int x;
    private int y;

    private static boolean toRight = false;

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
        switch (e.getModifiersEx()) {
            case InputEvent.BUTTON2_DOWN_MASK:
                float rX = Render.getCameraRotationX();
                float rY = Render.getCameraRotationY();

                Render.vTarget[0] +=  -(float) (x - e.getX()) / 100;
                Render.vTarget[1] -= (float) (y - e.getY()) / 100;
                Render.setRotationX(rX - (float) (y - e.getY()));
                Render.setRotationY(rY + (toRight ? (float) (x - e.getX()) : -(float) (x - e.getX())));

                break;
        }

        x = e.getX();
        y = e.getY();

        Render.getInstance().render();
        Render.getInstance().render();
    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        double rX = Math.abs(Render.getCameraRotationX());
        toRight = rX % 360 > 90 && rX % 360 < 270;
    }

    public void mouseWheelMoved(MouseWheelEvent e) {}
}
