package util;

import screen.Render;

import java.awt.*;

public class Draw {

    private static Render render;

    public static void setCanvas(Render render) {
        Draw.render = render;
    }

    /**
     *
     * @param scalar The color scale that will use
     * @param p The polygon that will be drawn
     * @param mark If the polygon will be drawn with blue scale
     */
    public static void fillTriangle(double scalar, Polygon p, boolean mark) {
//        render.getGraphics().setColor(Calc.getColor(-scalar, !mark));
        render.getGraphics().setColor(Color.white);
        render.getGraphics().fillPolygon(p);
    }

    public static void drawSquare(Point origin, int width, int height) {
        // TODO
        // render.getGraphics()
    }

}
