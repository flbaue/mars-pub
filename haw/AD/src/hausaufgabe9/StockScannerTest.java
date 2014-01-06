package hausaufgabe9;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by florian on 29.12.13.
 */
public class StockScannerTest {

    private List<Point> stock;
    private StockScanner stockScanner = new StockScanner();

    @Before
    public void setUp() throws Exception {

        stock = new ArrayList<>();
    }

    @Test
    public void testRandom() throws Exception {

        int numberOfPoints = 100000;
        stock = new ArrayList<>(numberOfPoints);

        for (int i = 0; i < numberOfPoints; i++) {
            int value = (int) Math.round(Math.random() * 10);
            if (i == numberOfPoints / 2)
                value = 15;
            stock.add(new Point(value, i));
        }

        Slope optimum = stockScanner.findOptimum(stock);

        assertEquals(15, optimum.getMax().getValue());
        assertEquals(numberOfPoints / 2, optimum.getMax().getPosition());
    }

    @Test
    public void testLine() throws Exception {

        stock.add(new Point(1, 0));
        stock.add(new Point(1, 1));
        stock.add(new Point(1, 2));
        stock.add(new Point(1, 3));
        stock.add(new Point(1, 4));
        stock.add(new Point(1, 5));
        stock.add(new Point(1, 6));
        stock.add(new Point(1, 7));
        stock.add(new Point(1, 8));
        stock.add(new Point(1, 9));

        Slope optimum = stockScanner.findOptimum(stock);
        assertNull(optimum);
    }

    @Test
    public void testUp() throws Exception {

        stock.add(new Point(0, 0));
        stock.add(new Point(1, 1));
        stock.add(new Point(2, 2));
        stock.add(new Point(3, 3));
        stock.add(new Point(4, 4));
        stock.add(new Point(5, 5));
        stock.add(new Point(6, 6));
        stock.add(new Point(7, 7));
        stock.add(new Point(8, 8));
        stock.add(new Point(9, 9));

        Slope optimum = stockScanner.findOptimum(stock);

        assertEquals(9, optimum.getSpread());
    }

    @Test
    public void testDown() throws Exception {

        stock.add(new Point(10, 0));
        stock.add(new Point(9, 1));
        stock.add(new Point(8, 2));
        stock.add(new Point(7, 3));
        stock.add(new Point(6, 4));
        stock.add(new Point(5, 5));
        stock.add(new Point(4, 6));
        stock.add(new Point(3, 7));
        stock.add(new Point(2, 8));
        stock.add(new Point(1, 9));

        Slope optimum = stockScanner.findOptimum(stock);

        assertNull(optimum);
    }

    @Test
    public void testHalf() throws Exception {

        stock.add(new Point(1, 0));
        stock.add(new Point(1, 1));
        stock.add(new Point(2, 2));
        stock.add(new Point(3, 3));
        stock.add(new Point(4, 4));
        stock.add(new Point(5, 5));
        stock.add(new Point(0, 6));
        stock.add(new Point(1, 7));
        stock.add(new Point(2, 8));
        stock.add(new Point(6, 9));

        Slope optimum = stockScanner.findOptimum(stock);

        assertEquals(6, optimum.getSpread());
        assertEquals(6, optimum.getMin().getPosition());
        assertEquals(9, optimum.getMax().getPosition());
    }

    @Test
    public void testTime() throws Exception {


        for (int numberOfPoints = 10000; numberOfPoints <= 250000; numberOfPoints += 20000) {

            stock = new ArrayList<>(numberOfPoints);

            for (int i = 0; i < numberOfPoints; i++) {
                int value = (int) Math.round(Math.random() * 10);
                stock.add(new Point(value, i));
            }

            long start = 0;
            long end = 0;

            System.out.println("O(n) Mode: " + numberOfPoints + " points");
            start = System.currentTimeMillis();
            Slope optimum = stockScanner.findOptimum(stock);
            end = System.currentTimeMillis();
//            System.out.println("\nOptimum:");
//            System.out.println(optimum);
            System.out.println("Milliseconds: " + (end - start));

        }
    }

}
