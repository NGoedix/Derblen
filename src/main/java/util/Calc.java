package util;

import objects.internal.Vertex;
import screen.Render;

public class Calc {

    /**
     * This function store the perspective projection configuration
     * statically to avoid re-math continuously the projection static values.
     * @return Matrix 4x4 with information of the projection.
     */
    public static float[][] getProjMatrix() {
        float[][] matrix = new float[4][4];

        float fNear = 0.1f;
        float fFar = 1000.0f;
        float fRange = fFar - fNear;
        float fFov = 90.0f;
        float fAspectRatio = (float) Render.getInstance().getHeight() / (float) Render.getInstance().getWidth();
        float fFovRad = 1.0f / (float) Math.tan(fFov * 0.5f / 180.0f * Math.PI);

        matrix[0][0] = fAspectRatio * fFovRad;
        matrix[1][1] = fFovRad;
        matrix[2][2] = fFar / fRange;
        matrix[3][2] = (-fFar * fNear) / fRange;
        matrix[2][3] = 1.0f;
        matrix[3][3] = 0.0f;

        return matrix;
    }

    public static float[][] getRotationXMatrix(double fTheta) {
        float[][] m = new float[4][4];

        m[0][0] = 1;
        m[1][1] = (float) Math.cos(fTheta);
        m[1][2] = (float) Math.sin(fTheta);
        m[2][1] = (float) -Math.sin(fTheta);
        m[2][2] = (float) Math.cos(fTheta);
        m[3][3] = 1;

        return m;
    }

    public static float[][] getRotationYMatrix(double fTheta) {
        float[][] m = new float[4][4];

        m[0][0] = (float) Math.cos(fTheta);
        m[0][2] = (float) Math.sin(fTheta);
        m[2][0] = (float) -Math.sin(fTheta);
        m[1][1] = 1.0f;
        m[2][2] = (float) Math.cos(fTheta);
        m[3][3] = 1.0f;

        return m;
    }

    public static float[][] getRotationZMatrix(double fTheta) {
        float[][] m = new float[4][4];

        m[0][0] = (float) Math.cos(fTheta);
        m[0][1] = (float) Math.sin(fTheta);
        m[1][0] = (float) -Math.sin(fTheta);
        m[1][1] = (float) Math.cos(fTheta);
        m[2][2] = 1;
        m[3][3] = 1;

        return m;
    }

    public static float[] multiplyMatrix(float[][] m, float[] i) {
        float[] o = new float[4];

        o[0] = i[0] * m[0][0] + i[1] * m[1][0] + i[2] * m[2][0] + i[3] * m[3][0];
        o[1] = i[0] * m[0][1] + i[1] * m[1][1] + i[2] * m[2][1] + i[3] * m[3][1];
        o[2] = i[0] * m[0][2] + i[1] * m[1][2] + i[2] * m[2][2] + i[3] * m[3][2];
        o[3] = i[0] * m[0][3] + i[1] * m[1][3] + i[2] * m[2][3] + i[3] * m[3][3];

        return o;
    }

    public static float[] multiplyMatrix(float[] i, float[][] m) {
        float[] o = new float[3];

        o[0] = i[0] * m[0][0] + i[1] * m[1][0] + i[2] * m[2][0] + m[3][0];
        o[1] = i[0] * m[0][1] + i[1] * m[1][1] + i[2] * m[2][1] + m[3][1];
        o[2] = i[0] * m[0][2] + i[1] * m[1][2] + i[2] * m[2][2] + m[3][2];
        float w = i[0] * m[0][3] + i[1] * m[1][3] + i[2] * m[2][3] + m[3][3];

        if (w != 0.0f) {
            o[0] /= w;
            o[1] /= w;
            o[2] /= w;
        }

        return o;
    }

    public static float[][] pointAt(float[] pos, float[] target, float[] up) {
        float[] newForward = Vector.subtract(target, pos);
        newForward = Vector.normalize(newForward);

        float[] a = Vector.multiplication(newForward, Vector.dotProduct(up, newForward));
        float[] newUp = Vector.subtract(up, a);
        newUp = Vector.normalize(newUp);

        float[] newRight = Vector.crossProduct(newUp, newForward);

        float[][] matrix = new float[4][4];
        matrix[0][0] = newRight[0];
        matrix[0][1] = newRight[1];
        matrix[0][2] = newRight[2];
        matrix[0][3] = 0.0f;
        matrix[1][0] = newUp[0];
        matrix[1][1] = newUp[1];
        matrix[1][2] = newUp[2];
        matrix[1][3] = 0.0f;
        matrix[2][0] = newForward[0];
        matrix[2][1] = newForward[1];
        matrix[2][2] = newForward[2];
        matrix[2][3] = 0.0f;
        matrix[3][0] = pos[0];
        matrix[3][1] = pos[1];
        matrix[3][2] = pos[2];
        matrix[3][3] = 1.0f;

        return matrix;
    }

    public static float[][] quickInverse(float[][] v) {
        float[][] matrix = new float[4][4];
        matrix[0][0] = v[0][0];
        matrix[0][1] = v[1][0];
        matrix[0][2] = v[2][0];
        matrix[0][3] = 0.0f;
        matrix[1][0] = v[0][1];
        matrix[1][1] = v[1][1];
        matrix[1][2] = v[2][1];
        matrix[1][3] = 0.0f;
        matrix[2][0] = v[0][2];
        matrix[2][1] = v[1][2];
        matrix[2][2] = v[2][2];
        matrix[2][3] = 0.0f;
        matrix[3][0] = -(v[3][0] * matrix[0][0] + v[3][1] * matrix[1][0] + v[3][2] * matrix[2][0]);
        matrix[3][1] = -(v[3][0] * matrix[0][1] + v[3][1] * matrix[1][1] + v[3][2] * matrix[2][1]);
        matrix[3][2] = -(v[3][0] * matrix[0][2] + v[3][1] * matrix[1][2] + v[3][2] * matrix[2][2]);
        matrix[3][3] = 1.0f;
        return matrix;
    }

    // https://www.scratchapixel.com/lessons/mathematics-physics-for-computer-graphics/geometry/lookat-function

    public static float[][] getIdentityMatrix() {
        float[][] o = new float[4][4];
        o[0][0] = 1.0f;
        o[1][1] = 1.0f;
        o[2][2] = 1.0f;
        o[3][3] = 1.0f;
        return o;
    }

    public static int[] getProjectedVertex(Vertex v) {
        float[] multiplied = multiplyMatrix(new float[] {v.getX(), v.getY(), v.getZ()}, getRotationZMatrix(0));
        multiplied = multiplyMatrix(multiplied, getRotationXMatrix(0));
        multiplied = multiplyMatrix(multiplied, getRotationYMatrix(0));

        multiplied[2] += 3.0f;

        multiplied = multiplyMatrix(multiplied, getProjMatrix());

        multiplied[0] += 1.0f;
        multiplied[1] += 1.0f;

        multiplied[0] *= 0.5f * (float) Render.getInstance().getWidth();
        multiplied[1] *= 0.5f * (float) Render.getInstance().getHeight();

        return new int[] {(int) multiplied[0], (int) multiplied[1]};
    }
}
