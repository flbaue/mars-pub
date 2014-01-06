package hausaufgabe7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 28.12.13.
 */
public class StockScanner {

    public List<Point> findOptimum(List<Point> stock) {
        markPoints(stock);

        Point min = null;
        Point max = null;
        int spread = -1;

        for (int i = 0; i < stock.size(); i++) {
            Point p = stock.get(i);

            if (p.getStatus() == Point.MIN) {

                Point tmpMin = p;
                Point tmpMax = findMax(i, stock);

                if (tmpMax == null) {
                    continue;
                }

                int tmpSpread = tmpMax.getValue() - tmpMin.getValue();

                if (spread < tmpSpread) {
                    min = tmpMin;
                    max = tmpMax;
                    spread = tmpSpread;
                }
            }
        }
       List<Point> optimum = new ArrayList<>();
        optimum.add(min);
        optimum.add(max);
        return optimum;
    }

    private Point findMax(int start, List<Point> stock) {

        Point max = null;
        for (int i = start + 1; i < stock.size(); i++) {
            Point newMax = stock.get(i);
            if (newMax.getStatus() == Point.MAX) {
                if (max == null) {
                    max = newMax;
                } else if (max.getValue() < newMax.getValue()) {
                    max = newMax;
                }
            }
        }

        return max;
    }

    private void markPoints(List<Point> stock) {
        for (int i = 0; i < stock.size(); i++) {
            Point p1 = stock.get(i);

            // Erster Wert
            if (i == 0) {
                Point p2 = stock.get(i + 1);
                int c = p1.compareTo(p2);
                if (c == -1) {
                    p1.setStatus(Point.MIN);
                } else if (c == 1) {
                    p1.setStatus(Point.MAX);
                }

                // Letzter Wert
            } else if (i == stock.size() - 1) {
                Point p0 = stock.get(i - 1);
                int c = p1.compareTo(p0);
                if (c == -1) {
                    p1.setStatus(Point.MIN);
                } else if (c == 1) {
                    p1.setStatus(Point.MAX);
                }

            } else {
                Point p0 = stock.get(i - 1);
                Point p2 = stock.get(i + 1);

                int c0 = p1.compareTo(p0);
                int c2 = p1.compareTo(p2);

                if (c0 == -1 && c2 == -1) {
                    p1.setStatus(Point.MIN);
                } else if (c0 == 1 && c2 == 1) {
                    p1.setStatus(Point.MAX);
                }
            }
        }
    }
}
