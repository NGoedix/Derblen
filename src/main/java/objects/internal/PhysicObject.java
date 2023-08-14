package objects.internal;

import java.util.ArrayList;

public class PhysicObject extends DerblenObject {

    protected ArrayList<Triangle> triangles;

    private float rotationX;
    private float rotationY;
    private float rotationZ;

    public PhysicObject() {
        this(0, 0, 0, new ArrayList<>());
    }

    public PhysicObject(float x, float y, float z) {
        super(x, y, z);
    }

    public PhysicObject(float x, float y, float z, ArrayList<Triangle> triangles) {
        this(x, y, z);
        this.triangles = triangles;
    }

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }
}
