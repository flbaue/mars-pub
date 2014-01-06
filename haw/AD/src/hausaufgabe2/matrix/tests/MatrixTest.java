/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * MatrixInterfaceTest.java
 * Florian Bauer
 * flbaue@posteo.de
 * 27.10.2013
 */
package hausaufgabe2.matrix.tests;

import hausaufgabe1.messreihe.Messreihe;
import hausaufgabe1.messreihe.MessreiheB;
import hausaufgabe2.matrix.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Florian Bauer
 */
public class MatrixTest {

    @Test
    public void aufgabe6Test() {
        int n = 3;
        double[][] valuesA = MatrixGeneratorUtil.randomMatrix(n, 90);
        double[][] valuesB = MatrixGeneratorUtil.randomMatrix(n, 90);

        Matrix a1 = new MatrixA(valuesA);
        Matrix b1 = new MatrixA(valuesB);

        Matrix a2 = new MatrixB(valuesA);
        Matrix b2 = new MatrixB(valuesB);

        Matrix a3 = new MatrixC(valuesA);
        Matrix b3 = new MatrixC(valuesB);

        a1 = a1.matrixMulti(b1);
        a2 = a2.matrixMulti(b2);
        a3 = a3.matrixMulti(b3);

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                assertTrue(a1.getValue(x, y) == a2.getValue(x, y)
                        && a2.getValue(x, y) == a3.getValue(x, y));
            }
        }

    }

    @Test
    public void aufgabe7Test() {
        int n = 100;
        int t = 1000;
        int p = 10;

        Messreihe mA = new MessreiheB();
        Messreihe mB = new MessreiheB();
        Messreihe mC = new MessreiheB();

        for (int i = 0; i < t; i++) {
            double[][] values = MatrixGeneratorUtil.randomMatrix(n, p);
            Matrix matrixA = new MatrixA(values);
            Matrix matrixB = new MatrixB(values);
            Matrix matrixC = new MatrixC(values);

            // System.out.println("Testlauf " + i);
            // System.out.println("MatrixA=\t" +
            // matrixA.getNumberOfStoredElements());
            // System.out.println("MatrixB=\t" +
            // matrixB.getNumberOfStoredElements());
            // System.out.println("MatrixC=\t" +
            // matrixC.getNumberOfStoredElements());

            mA.addWert(matrixA.getNumberOfStoredElements());
            mB.addWert(matrixB.getNumberOfStoredElements());
            mC.addWert(matrixC.getNumberOfStoredElements());
        }

        System.out.println("Average number of elements for n=" + n + "\tt=" + t
                + "\tp=" + p);

        System.out.println("MatrixA: " + mA.mittelwert());

        System.out.println("MatrixB: " + mB.mittelwert());

        System.out.println("MatrixC: " + mC.mittelwert());
    }

    @Test
    public void aufgabe8Test() {
        int n = 10;
        int t = 5;
        int p = 1;

        Messreihe mA1 = new MessreiheB();
        Messreihe mB1 = new MessreiheB();
        Messreihe mC1 = new MessreiheB();

        for (int i = 0; i < t; i++) {
            double[][] values = MatrixGeneratorUtil.randomMatrix(n, p);
            Matrix matrixA = new MatrixA(values);
            Matrix matrixB = new MatrixBAdList(values);
            Matrix matrixC = new MatrixCAdList(values);

            mA1.addWert(matrixA.counter().getSteps());
            mB1.addWert(((MatrixBAdList) matrixB).getListCount());
            mC1.addWert(((MatrixCAdList) matrixC).getListCount());

        }

        System.out.println("Initialization");
        System.out.println("Average steps for p=" + p);
        System.out.println("MatrixA:\tsteps=" + mA1.mittelwert());
        System.out.println("MatrixB:\tsteps=" + mB1.mittelwert());
        System.out.println("MatrixC:\tsteps=" + mC1.mittelwert());

        mA1 = new MessreiheB();
        mB1 = new MessreiheB();
        mC1 = new MessreiheB();

        for (int i = 0; i < t; i++) {
            double[][] values = MatrixGeneratorUtil.randomMatrix(n, p);
            Matrix matrixA = new MatrixA(values);
            Matrix matrixB = new MatrixBAdList(values);
            Matrix matrixC = new MatrixCAdList(values);
            Matrix matrixX = new MatrixA(values);

            matrixA.add(matrixX);
            matrixB.add(matrixX);
            matrixC.add(matrixX);

            mA1.addWert(matrixA.counter().getSteps());
            mB1.addWert(((MatrixBAdList) matrixB).getListCount());
            mC1.addWert(((MatrixCAdList) matrixC).getListCount());

        }

        System.out.println("Addition");
        System.out.println("Average steps for p=" + p);
        System.out.println("MatrixA:\tsteps=" + mA1.mittelwert());
        System.out.println("MatrixB:\tsteps=" + mB1.mittelwert());
        System.out.println("MatrixC:\tsteps=" + mC1.mittelwert());
    }

//    @Test
//    public void aufgabe3_4Test() {
//	int n = 1000;
//	int t = 5;
//	int p = 1;
//	int k = 100;
//
//	Messreihe mA1 = new MessreiheB();
//	Messreihe mA2 = new MessreiheB();
//	Messreihe mB1 = new MessreiheB();
//	Messreihe mB2 = new MessreiheB();
//	Messreihe mC1 = new MessreiheB();
//	Messreihe mC2 = new MessreiheB();
//	StepCounter counter = null;
//
//	for (int i = 0; i < t; i++) {
//	    double[][] values = MatrixGeneratorUtil.randomMatrix(n, p);
//	    Matrix matrixA = new MatrixA(values);
//	    Matrix matrixB = new MatrixB(values);
//	    Matrix matrixC = new MatrixC(values);
//
//	    counter = matrixA.counter();
//	    matrixA.pow(k);
//	    mA1.addWert(counter.getSteps());
//	    counter.reset();
//	    matrixA.pow2(k);
//	    mA2.addWert(counter.getSteps());
//
//	    counter = matrixB.counter();
//	    matrixB.pow(k);
//	    mB1.addWert(counter.getSteps());
//	    counter.reset();
//	    matrixB.pow2(k);
//	    mB2.addWert(counter.getSteps());
//
//	    counter = matrixC.counter();
//	    matrixC.pow(k);
//	    mC1.addWert(counter.getSteps());
//	    counter.reset();
//	    matrixC.pow2(k);
//	    mC2.addWert(counter.getSteps());
//	}
//
//	System.out.println("Average steps for k=" + k);
//
//	System.out.println("MatrixA:pow\tsteps=" + mA1.mittelwert());
//	System.out.println("MatrixA:pow2\tsteps=" + mA2.mittelwert());
//
//	System.out.println("MatrixA:pow\tsteps=" + mB1.mittelwert());
//	System.out.println("MatrixA:pow2\tsteps=" + mB2.mittelwert());
//
//	System.out.println("MatrixA:pow\tsteps=" + mC1.mittelwert());
//	System.out.println("MatrixA:pow2\tsteps=" + mC2.mittelwert());
//    }

    @Test
    public void pow3Test() {

    }
}
