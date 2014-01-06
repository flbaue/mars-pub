/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * AbstractMatrix.java
 * Florian Bauer
 * flbaue@posteo.de
 * 28.10.2013
 */
package hausaufgabe2.matrix;

import counter.StepCounter;

/**
 * @author Florian Bauer
 */
public abstract class AbstractMatrix implements Matrix {

    private MatrixImplType type;
    private StepCounter counter = new StepCounter();

    public AbstractMatrix(MatrixImplType type) {
        this.type = type;
    }

    public static Matrix makeMatrix(MatrixImplType type, int dimension) {
        if (type == MatrixImplType.A)
            return new MatrixA(dimension);
        if (type == MatrixImplType.B)
            return new MatrixB(dimension);
        if (type == MatrixImplType.C)
            return new MatrixC(dimension);

        throw new IllegalArgumentException("Unkonwn Typ");
    }

    public static Matrix makeMatrix(MatrixImplType type, double[][] values) {
        if (type == MatrixImplType.A)
            return new MatrixA(values);
        if (type == MatrixImplType.B)
            return new MatrixB(values);
        if (type == MatrixImplType.C)
            return new MatrixC(values);

        throw new IllegalArgumentException("Unkonwn Typ");
    }

    public Matrix add(final Matrix m) {

        Matrix resultMatrix = AbstractMatrix.makeMatrix(type, getSize());

        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                double value = getValue(x, y) + m.getValue(x, y);
                resultMatrix.setValue(x, y, value);
            }
        }
        return resultMatrix;
    }

    public Matrix scalarMulti(final double s) {

        Matrix resultMatrix = AbstractMatrix.makeMatrix(type, getSize());

        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                double value = getValue(x, y) * s;
                resultMatrix.setValue(x, y, value);
            }
        }
        return resultMatrix;
    }

    public Matrix matrixMulti(final Matrix m) {

        Matrix resultMatrix = AbstractMatrix.makeMatrix(type, getSize());

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                double value = 0;
                for (int k = 0; k < getSize(); k++) {
                    value = getValue(i, k) * m.getValue(k, j);

                }
                resultMatrix.setValue(j, i, value);
            }
        }
        return resultMatrix;
    }

    public Matrix pow(int x) {
        Matrix resultMatrix = this;

        for (int i = 1; i < x; i++) {
            counter.step();
            resultMatrix = matrixMulti(resultMatrix);
        }

        return resultMatrix;
    }

    public Matrix pow2(int k) {
        if (k == 0) {
            double[][] values = new double[getSize()][getSize()];
            for (int y = 0; y < getSize(); y++) {
                for (int x = 0; x < getSize(); x++) {
                    values[y][x] = 1;
                }
            }
            return makeMatrix(type, values);
        } else if ((k % 2) == 0) {
            Matrix t = pow2(k / 2);
            counter.step();
            return t.matrixMulti(t);
        } else {
            Matrix t = pow2((k - 1) / 2);
            counter.step();
            counter.step();
            return t.matrixMulti(t).matrixMulti(this);
        }
    }

    public StepCounter counter() {
        if (counter == null) {
            counter = new StepCounter();
        }
        return counter;
    }

    public enum MatrixImplType {
        A, B, C;
    }
}
