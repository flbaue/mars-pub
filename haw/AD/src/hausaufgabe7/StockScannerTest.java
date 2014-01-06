package hausaufgabe7;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 28.12.13.
 */
public class StockScannerTest {

    private List<Point> stock;
    private StockScanner stockScanner = new StockScanner();
    private StockScanner2 stockScanner2 = new StockScanner2();

//    @Before
//    public void setUp() throws Exception {
//
//        int numberOfPoints = 500;
//        stock = new ArrayList<>(numberOfPoints);
//
//        for(int i = 0; i < numberOfPoints; i++) {
//            int value = (int) Math.round(Math.random() * 10);
//            stock.add(new Point(value,i));
//        }

//        stock.add(new Point(10,0));
//        stock.add(new Point(9,1));
//        stock.add(new Point(8,2));
//        stock.add(new Point(7,3));
//        stock.add(new Point(6,4));
//        stock.add(new Point(5,5));
//        stock.add(new Point(4,6));
//        stock.add(new Point(3,7));
//        stock.add(new Point(2,8));
//        stock.add(new Point(1,9));

//        stock.add(new Point(0,0));
//        stock.add(new Point(1,1));
//        stock.add(new Point(2,2));
//        stock.add(new Point(3,3));
//        stock.add(new Point(4,4));
//        stock.add(new Point(5,5));
//        stock.add(new Point(6,6));
//        stock.add(new Point(7,7));
//        stock.add(new Point(8,8));
//        stock.add(new Point(9,9));

//        stock.add(new Point(1,0));
//        stock.add(new Point(1,1));
//        stock.add(new Point(2,2));
//        stock.add(new Point(3,3));
//        stock.add(new Point(4,4));
//        stock.add(new Point(5,5));
//        stock.add(new Point(0,6));
//        stock.add(new Point(1,7));
//        stock.add(new Point(2,8));
//        stock.add(new Point(6,9));

//    }
//
//    @Test
//    public void testFindOptimum() throws Exception {
//
//        long start = 0;
//        long end = 0;
//
//        System.out.println("Normal:");
//        start = System.currentTimeMillis();
//        List<Point> optimum = stockScanner.findOptimum(stock);
//        end = System.currentTimeMillis();
////        System.out.println("Stock:");
////        System.out.println(stock);
//        System.out.println("\noptimum:");
//        System.out.println(optimum);
//        System.out.println("Milliseconds: " + (end - start));
//
//        System.out.println("\nTeile und Hersche:");
//        start = System.currentTimeMillis();
//        List<Point> optimum2 = stockScanner2.findOptimum(stock);
//        end = System.currentTimeMillis();
////        System.out.println("Stock:");
////        System.out.println(stock);
//        System.out.println("\noptimum:");
//        System.out.println(optimum2);
//        System.out.println("Milliseconds: " + (end - start));
//    }

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

            System.out.println("O(n^2) Mode: " + numberOfPoints + " points");
            start = System.currentTimeMillis();
            List<Point> optimum = stockScanner.findOptimum(stock);
            end = System.currentTimeMillis();
            System.out.println("Milliseconds: " + (end - start));

            System.out.println("O(n log(n)) Mode:" + numberOfPoints + " points");
            start = System.currentTimeMillis();
            List<Point> optimum2 = stockScanner2.findOptimum(stock);
            end = System.currentTimeMillis();
            System.out.println("Milliseconds: " + (end - start));

            System.out.println("");

        }
    }
}
