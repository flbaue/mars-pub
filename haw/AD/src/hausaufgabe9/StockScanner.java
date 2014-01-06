package hausaufgabe9;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 29.12.13.
 */
public class StockScanner {

    public Slope findOptimum(List<Point> stock) {
        markPoints(stock);

        List<Slope> slopes = new ArrayList<>();

        Point min = null;
        Point max = null;

        for (int i = stock.size() - 1; i >= 0; i--) {

            Point p = stock.get(i);

            if (p.getStatus() == Point.MIN) {
                if (min == null || min.getValue() > p.getValue())
                    min = p;

            } else if (p.getStatus() == Point.MAX) {
                if (max == null)
                    max = p;

                else if (max.getValue() < p.getValue()) {
                    if (min == null)
                        min = stock.get(max.getPosition() - 1);

                    slopes.add(new Slope(min, max));
                    min = null;
                    max = p;
                }
            }

            if (i == 0)
                if (min != null && max != null && min.getPosition() < max.getPosition())
                    slopes.add(new Slope(min, max));
        }

        Slope bestSlope = null;
        for (Slope s : slopes) {
            if (bestSlope == null)
                bestSlope = s;
            else if (bestSlope.getSpread() < s.getSpread())
                bestSlope = s;
        }

        return bestSlope;
    }

    private void markPoints(List<Point> stock) {
        for (int i = 0; i < stock.size(); i++) {
            Point p1 = stock.get(i);

            // Erster Wert
            if (i == 0) {
                Point p2 = stock.get(i + 1);
                int c = p1.compareTo(p2);
                if (c == -1)
                    p1.setStatus(Point.MIN);
                else if (c == 1)
                    p1.setStatus(Point.MAX);


                // Letzter Wert
            } else if (i == stock.size() - 1) {
                Point p0 = stock.get(i - 1);
                int c = p1.compareTo(p0);
                if (c == -1)
                    p1.setStatus(Point.MIN);
                else if (c == 1)
                    p1.setStatus(Point.MAX);

            } else {
                Point p0 = stock.get(i - 1);
                Point p2 = stock.get(i + 1);

                int c0 = p1.compareTo(p0);
                int c2 = p1.compareTo(p2);

                if (c0 == -1 && c2 == -1)
                    p1.setStatus(Point.MIN);
                else if (c0 == 1 && c2 == 1)
                    p1.setStatus(Point.MAX);
            }
        }
    }

}
