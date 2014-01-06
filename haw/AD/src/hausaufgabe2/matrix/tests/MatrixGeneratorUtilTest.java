/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * Florian Bauer
 * flbaue@posteo.de
 * 07.10.2013
 * MatrixGeneratorUtilTest.java
 */
package hausaufgabe2.matrix.tests;

import hausaufgabe2.matrix.MatrixGeneratorUtil;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Florian Bauer
 */
public class MatrixGeneratorUtilTest {

    /**
     *
     */
    @Test
    public void randomMatrixTest() {
        double[][] matrix = MatrixGeneratorUtil.randomMatrix(10000, 90);
        assertTrue(matrix.length == 15);
        assertTrue(matrix[0].length == 15);
    }

}
