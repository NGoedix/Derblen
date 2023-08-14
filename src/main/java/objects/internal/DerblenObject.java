package objects.internal;

public class DerblenObject {

    private float x;
    private float y;
    private float z;

    public DerblenObject() {}

    public DerblenObject(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public String getName() {
        if (getClass().getSimpleName().equals("PhysicObject"))
            return "Object";
        return getClass().getSimpleName();
    }
}
