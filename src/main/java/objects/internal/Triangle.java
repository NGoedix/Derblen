package objects.internal;

public class Triangle {

    public PhysicObject complexObject;

    private Vertex v1;
    private Vertex v2;
    private Vertex v3;

    public Triangle(PhysicObject c, Vertex v1, Vertex v2, Vertex v3) {
        this.complexObject = c;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    public PhysicObject getComplexObject() {
        return complexObject;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public Vertex getV3() {
        return v3;
    }
}
