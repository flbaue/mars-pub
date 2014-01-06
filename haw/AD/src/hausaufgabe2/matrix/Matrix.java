/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * Matrix.java
 * Florian Bauer
 * flbaue@posteo.de
 * 28.10.2013
 */
package hausaufgabe2.matrix;

import counter.StepCounter;

/**
 * @author Florian Bauer
 */
public interface Matrix {

    /**
     * Adds 2 Matrixes together ( matrix = matrix + matrix).
     *
     * @param m other matrix
     * @return the resulting matrix
     */
    Matrix add(final Matrix m);

    /**
     * Multiples a matrix by a scalar.
     *
     * @param s scalar as double
     * @return the resulting matrix
     */
    Matrix scalarMulti(final double s);

    /**
     * Multiples a matrix with another matrix ( matrix = matrix * matrix ).
     *
     * @param m other matrix
     * @return the resulting matrix
     */
    Matrix matrixMulti(final Matrix m);

    /**
     * Raises the matrix value by the power of the given exponent.
     *
     * @param x the exponent
     * @return the resulting matrix
     */
    Matrix pow(final int x);

    Matrix pow2(final int x);

    /**
     * Retrieves a specific value from the matrix.
     *
     * @param x position
     * @param y position
     * @return the double value
     */
    double getValue(final int x, final int y);

    /**
     * Stores a specific value in the matrix.
     *
     * @param x     position
     * @param y     position
     * @param value
     */
    void setValue(final int x, final int y, final double value);

    /**
     * The dimension of the matrix.
     *
     * @return int
     */
    int getSize();

    /**
     * Counts how many elements are actually stored after internal
     * optimizations.
     *
     * @return number of elements
     */
    int getNumberOfStoredElements();

    /**
     * @return
     */
    StepCounter counter();
}
