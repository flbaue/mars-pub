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

import hausaufgabe1.liste.AdList;
import hausaufgabe1.liste.DefaultAdList;

/**
 * @author Florian Bauer
 */
public class MatrixCAdList extends AbstractMatrix {

    private static final MatrixImplType TYPE = MatrixImplType.C;
    private AdList<Double> values;
    private int rowLength;

    /**
     * @param typ
     */
    public MatrixCAdList(int n) {
        super(TYPE);
        rowLength = n;
        values = new DefaultAdList<Double>();
    }

    /**
     * @param typ
     */
    public MatrixCAdList(double[][] values) {
        super(TYPE);
        rowLength = values.length;
        this.values = new DefaultAdList<Double>();
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
                values.insert(null, position);
            } else {
                counter().step();
                values.insert(value, position);
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

    public int getListCount() {
        return ((DefaultAdList<Double>) values).refCounter().getSteps();
    }

}
