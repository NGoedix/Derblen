package objects;

import objects.internal.PhysicObject;
import objects.internal.Triangle;
import objects.internal.Vertex;

public class Cube extends PhysicObject {
    
    public Cube() {
        // FRONT
        Vertex v1, v2, v3;
        v1 = new Vertex(0.5f, -0.5f, 0.5f);
        v2 = new Vertex(0.5f, 0.5f, 0.5f);
        v3 = new Vertex(-0.5f, 0.5f, 0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        v1 = new Vertex(0.5f, -0.5f, 0.5f);
        v2 = new Vertex(-0.5f, 0.5f, 0.5f);
        v3 = new Vertex(-0.5f, -0.5f, 0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        // BACK
        v1 = new Vertex(-0.5f, -0.5f, -0.5f);
        v2 = new Vertex(-0.5f, 0.5f, -0.5f);
        v3 = new Vertex(0.5f, 0.5f, -0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        v1 = new Vertex(-0.5f, -0.5f, -0.5f);
        v2 = new Vertex(0.5f, 0.5f, -0.5f);
        v3 = new Vertex(0.5f, -0.5f, -0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        // RIGHT
        v1 = new Vertex(-0.5f, -0.5f, 0.5f);
        v2 = new Vertex(-0.5f, 0.5f, 0.5f);
        v3 = new Vertex(-0.5f, 0.5f, -0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        v1 = new Vertex(-0.5f, -0.5f, 0.5f);
        v2 = new Vertex(-0.5f, 0.5f, -0.5f);
        v3 = new Vertex(-0.5f, -0.5f, -0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        // LEFT
        v1 = new Vertex(0.5f, -0.5f, -0.5f);
        v2 = new Vertex(0.5f, 0.5f, -0.5f);
        v3 = new Vertex(0.5f, 0.5f, 0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        v1 = new Vertex(0.5f, -0.5f, -0.5f);
        v2 = new Vertex(0.5f, 0.5f, 0.5f);
        v3 = new Vertex(0.5f, -0.5f, 0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        // BOTTOM
        v1 = new Vertex(0.5f, -0.5f, -0.5f);
        v2 = new Vertex(0.5f, -0.5f, 0.5f);
        v3 = new Vertex(-0.5f, -0.5f, 0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        v1 = new Vertex(0.5f, -0.5f, -0.5f);
        v2 = new Vertex(-0.5f, -0.5f, 0.5f);
        v3 = new Vertex(-0.5f, -0.5f, -0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        // UP
        v1 = new Vertex(0.5f, 0.5f, 0.5f);
        v2 = new Vertex(0.5f, 0.5f, -0.5f);
        v3 = new Vertex(-0.5f, 0.5f, -0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));

        v1 = new Vertex(0.5f, 0.5f, 0.5f);
        v2 = new Vertex(-0.5f, 0.5f, -0.5f);
        v3 = new Vertex(-0.5f, 0.5f, 0.5f);
        triangles.add(new Triangle(this, v1, v2, v3));
    }
    
}
