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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Florian Bauer
 */
public class MatrixC extends AbstractMatrix {

    private static final MatrixImplType TYPE = MatrixImplType.C;
    private List<Double> values;
    private int rowLength;

    /**
     * @param typ
     */
    public MatrixC(int n) {
        super(TYPE);
        rowLength = n;
        values = new ArrayList<Double>(rowLength * rowLength);
    }

    /**
     * @param typ
     */
    public MatrixC(double[][] values) {
        super(TYPE);
        rowLength = values.length;
        this.values = new ArrayList<Double>(rowLength * rowLength);
        for (int y = 0; y < values.length; y++) {
            for (int x = 0; x < values.length; x++) {
                setValue(x, y, values[y][x]);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double getValue(int x, int y) {
        int position = rowLength * y + x;
        counter().step();
        Double value = values.get(position);
        if (value != null)
            return value;
        else
            return 0;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setValue(int x, int y, double value) {
        int position = rowLength * y + x;
        counter().step();
        if (values.size() == position) {
            if (value == 0) {
                counter().step();
                values.add(null);
            } else {
                counter().step();
                values.add(value);
            }
        } else {
            if (value == 0) {
                counter().step();
                values.set(position, null);
            } else {
                counter().step();
                values.set(position, value);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getSize() {
        return rowLength;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getNumberOfStoredElements() {
        int sum = 0;
        for (int pos = 0; pos < values.size(); pos++) {
            if (values.get(pos) != null)
                sum++;
        }
        return sum;
    }

}
