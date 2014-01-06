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
public class MatrixBAdList extends AbstractMatrix {

    private static final MatrixImplType TYPE = MatrixImplType.B;
    private AdList<Double>[] values;
    private int rowLength;

    /**
     * @param typ
     */
    @SuppressWarnings("unchecked")
    public MatrixBAdList(int n) {
        super(TYPE);
        rowLength = n;
        values = new AdList[rowLength];
        for (int y = 0; y < rowLength; y++) {
            values[y] = new DefaultAdList<Double>();
        }
    }

    /**
     * @param typ
     */
    @SuppressWarnings("unchecked")
    public MatrixBAdList(double[][] values) {
        super(TYPE);
        rowLength = values.length;
        this.values = new AdList[rowLength];
        for (int y = 0; y < rowLength; y++) {
            this.values[y] = new DefaultAdList<Double>();
            for (int x = 0; x < rowLength; x++) {
                setValue(x, y, values[y][x]);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double getValue(int x, int y) {
        int position = x;
        counter().step();
        Double value = values[y].get(position);
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
        int position = x;
        counter().step();
        if (values[y].size() == position) {
            if (value == 0) {
                counter().step();
                values[y].add(null);
            } else {
                counter().step();
                values[y].add(value);
            }
        } else {
            if (value == 0) {
                counter().step();
                values[y].insert(null, position);
            } else {
                counter().step();
                values[y].insert(value, position);
            }
        }
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
        int sum = 0;
        for (int y = 0; y < values.length; y++) {
            for (int pos = 0; pos < values[y].size(); pos++) {
                if (values[y].get(pos) != null)
                    sum++;
            }
        }
        return sum;
    }

    public int getListCount() {
        int sum = 0;
        for (int y = 0; y < rowLength; y++) {
            sum += ((DefaultAdList<Double>) values[y]).refCounter().getSteps();
        }
        return sum;
    }
}
