/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * MatrixA.java
 * Florian Bauer
 * flbaue@posteo.de
 * 28.10.2013
 */
package hausaufgabe2.matrix;

/**
 * @author Florian Bauer
 */
public class MatrixA extends AbstractMatrix {

    private static final MatrixImplType TYPE = MatrixImplType.A;
    private double[][] values;

    /**
     * @param typ
     */
    public MatrixA(int n) {
        super(TYPE);
        values = new double[n][n];
    }

    /**
     * @param typ
     */
    public MatrixA(double[][] values) {
        super(TYPE);
        this.values = values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double getValue(int x, int y) {
        counter().step();
        return values[y][x];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setValue(int x, int y, double value) {
        counter().step();
        values[y][x] = value;

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getSize() {
        return values.length;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getNumberOfStoredElements() {
        return values.length * values.length;
    }

}
