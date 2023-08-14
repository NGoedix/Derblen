package screen;

import input.KeyboardInput;
import input.MouseInput;
import objects.Cube;
import objects.internal.PhysicObject;
import objects.internal.Triangle;
import objects.internal.Vertex;
import util.Calc;
import util.Draw;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;

public class Render extends Canvas {

    private static ArrayList<Triangle> triangles;
    private static ArrayList<PhysicObject> objects;

    private Graphics graphics;
    private static Render render;

    private static float cameraRotationX;
    private static float cameraRotationY;
    private static float cameraRotationZ;

    private static float cameraX;
    private static float cameraY;
    private static float cameraZ;

    private static float fYaw;

    public static float[] vUp = new float[]{ 0, 1, 0, 1 };
    public static float[] vTarget = new float[]{ 0, 0, 1, 1 };

    public Render() {
        // Store the instance
        render = this;

        // Configuration of the Canvas
        setFocusable(true);
        setVisible(true);
        setBackground(Color.BLACK);

        // Listeners
        addKeyListener(new KeyboardInput());
        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseInput());
        addMouseWheelListener(new MouseInput());

        // Store objects and add a cube
        objects = new ArrayList<>();
        triangles = new ArrayList<>();
        addObject(new Cube());
    }

    public void start() {
        // Create buffer strategy, setCanvas to draw this Object and make first render
        createBufferStrategy(3);
        Draw.setCanvas(this);
        render();
        render();
    }

    public void render() {
        // Get buffer strategy and graphics to draw
        BufferStrategy bufferStrategy = getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();

        // Clear the render screen
        graphics.setColor(Color.BLACK);
        graphics.clearRect(0, 0, getWidth(), getHeight());

        renderTriangles(triangles);

        logs();

        // Show
        bufferStrategy.show();
        graphics.dispose();
    }

    public static void renderTriangles(ArrayList<Triangle> triangles) {
        float[][] matWorld = Calc.getIdentityMatrix();

        float[] vUp = { 0, 1, 0, 1 };
        float[] vTarget = { 0, 0, 1, 1 };

        vUp = Render.vUp;
        vTarget = Render.vTarget;

        float[][] cameraMatrixRot = Calc.getRotationYMatrix(0);
        float[] vLookDir = Calc.multiplyMatrix(cameraMatrixRot, vTarget);
        vTarget = new float[] { cameraX + vLookDir[0], cameraY + vLookDir[1], cameraZ + vLookDir[2]};
        float[][] cameraMatrix = Calc.pointAt(new float[] { Render.getCameraX(), Render.getCameraY(), Render.getCameraZ()}, vTarget, vUp);

        float[][] matView = Calc.quickInverse(cameraMatrix);

        for (Triangle t : triangles) {
            // Get the triangle vertices
            Vertex v1 = t.getV1();
            Vertex v2 = t.getV2();
            Vertex v3 = t.getV3();

            float[] temp1 = Calc.multiplyMatrix(matWorld, new float[] { v1.getX(), v1.getY(), v1.getZ(), 1 });
            float[] temp2 = Calc.multiplyMatrix(matWorld, new float[] { v2.getX(), v2.getY(), v2.getZ(), 1 });
            float[] temp3 = Calc.multiplyMatrix(matWorld, new float[] { v3.getX(), v3.getY(), v3.getZ(), 1 });

            // World matrix transform
            Triangle triangleTransformed = new Triangle(
                    null,
                    new Vertex(temp1[0], temp1[1], temp1[2]),
                    new Vertex(temp2[0], temp2[1], temp2[2]),
                    new Vertex(temp3[0], temp3[1], temp3[2])
            );

            Vertex v4 = triangleTransformed.getV1();
            Vertex v5 = triangleTransformed.getV2();
            Vertex v6 = triangleTransformed.getV3();

            float[] temp4 = Calc.multiplyMatrix(matView, new float[] { v4.getX(), v4.getY(), v4.getZ(), 1 });
            float[] temp5 = Calc.multiplyMatrix(matView, new float[] { v5.getX(), v5.getY(), v5.getZ(), 1 });
            float[] temp6 = Calc.multiplyMatrix(matView, new float[] { v6.getX(), v6.getY(), v6.getZ(), 1 });

            Triangle triangleViewed = new Triangle(
                    null,
                    new Vertex(temp4[0], temp4[1], temp4[2]),
                    new Vertex(temp5[0], temp5[1], temp5[2]),
                    new Vertex(temp6[0], temp6[1], temp6[2])
            );

            // Get the projection of each vertex
            int[] p1 = Calc.getProjectedVertex(triangleViewed.getV1());
            int[] p2 = Calc.getProjectedVertex(triangleViewed.getV2());
            int[] p3 = Calc.getProjectedVertex(triangleViewed.getV3());

            // Create the arrays that contain the triangle positions x and y
            int[] x = new int[] {p1[0], p2[0], p3[0]};
            int[] y = new int[] {p1[1], p2[1], p3[1]};

            Draw.fillTriangle(0, new Polygon(x, y, 3), false);
        }
    }

    public static void addObject(PhysicObject c) {
        objects.add(c);
        triangles.addAll(c.getTriangles());
        Window.getObjectsTree().addObject(c, "test");
    }

    public static Render getInstance() {
        return render;
    }

    public Graphics getGraphics() {
        return graphics;
    }


    public static void setRotationX(float rotationX) {
        Render.cameraRotationX = rotationX;
    }

    public static void setRotationY(float rotationY) {
        Render.cameraRotationY = rotationY;
    }

    public static void setRotationZ(float rotationZ) {
        Render.cameraRotationZ = rotationZ;
    }

    public static float getCameraRotationX() {
        return cameraRotationX;
    }

    public static float getCameraRotationZ() {
        return cameraRotationZ;
    }

    public static float getCameraRotationY() {
        return cameraRotationY;
    }

    public static float getCameraX() {
        return cameraX;
    }

    public static float getCameraY() {
        return cameraY;
    }

    public static float getCameraZ() {
        return cameraZ;
    }

    public void logs() {
        graphics.drawString("Rotation X: " + getCameraRotationX(), 10, 20);
        graphics.drawString("Rotation Y: " + getCameraRotationY(), 10, 40);
        graphics.drawString("Rotation Z: " + getCameraRotationZ(), 10, 60);
    }
}
