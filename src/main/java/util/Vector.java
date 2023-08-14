package util;

public class Vector {

    public static float dotProduct(float[] v1, float[] v2) {
        return v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2];
    }

    public static float length(float[] v) {
        return (float) Math.sqrt(dotProduct(v, v));
    }

    public static float[] normalize(float[] v) {
        float l = length(v);
        return new float[] { v[0] / l, v[1] / l, v[2] /l };
    }

    public static float[] subtract(float[] v1, float[] v2) {
        return new float[] { v1[0] - v2[0], v1[1] - v2[1], v1[2] - v2[2] };
    }

    public static float[] add(float[] v1, float[] v2) {
        return new float[] { v1[0] + v2[0], v1[1] + v2[1], v1[2] + v2[2] };
    }

    public static float[] multiplication(float[] v1, float v) {
        return new float[] { v1[0] * v, v1[1] * v, v1[2] * v };
    }

    public static float[] division(float[] v1, float v) {
        return new float[] { v1[0] / v, v1[1] / v, v1[2] / v };
    }

    public static float[] crossProduct(float[] v1, float[] v2) {
        float[] v = new float[3];
        v[0] = v1[1] * v2[2] - v1[2] * v2[1];
        v[1] = v1[2] * v2[0] - v1[0] * v2[2];
        v[2] = v1[0] * v2[1] - v1[1] * v2[0];
        return v;
    }
}
