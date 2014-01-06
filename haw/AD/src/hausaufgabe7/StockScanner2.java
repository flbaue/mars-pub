package hausaufgabe7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 29.12.13.
 */
public class StockScanner2 {

    public List<Point> findOptimum(List<Point> stock) {
        List<Point> result = find(stock);
        int spread = result.get(1).getValue() - result.get(0).getValue();
        if (spread <= 0) {
            throw new IllegalArgumentException("No optimal points");
        } else {
            return result;
        }
    }

    private List<Point> find(List<Point> stock) {

        if (stock.size() == 3) {
            return spread(stock);
        }

        if (stock.size() == 2) {
            return stock;
        }

        int middle = stock.size() / 2;

        List<Point> left = stock.subList(0, middle);
        List<Point> right = stock.subList(middle, stock.size());

        List<Point> optLeft = find(left);
        int leftSpread = optLeft.get(1).getValue() - optLeft.get(0).getValue();


        List<Point> optRight = find(right);
        int rightSpread = optRight.get(1).getValue() - optRight.get(0).getValue();


        Point min = findMin(optLeft);
        Point max = findMax(optRight);
        int spread = max.getValue() - min.getValue();


        if (leftSpread >= rightSpread && leftSpread >= spread) {
            return optLeft;
        } else if (rightSpread >= leftSpread && rightSpread >= spread) {
            return optRight;
        } else {
            List<Point> optimum = new ArrayList<>();
            optimum.add(min);
            optimum.add(max);
            return optimum;
        }

    }

    private Point findMax(List<Point> stock) {
        Point max = null;

        for (Point p : stock) {
            if (max == null)
                max = p;

            if (max.getValue() < p.getValue())
                max = p;
        }

        return max;
    }

    private Point findMin(List<Point> stock) {
        Point min = null;

        for (Point p : stock) {
            if (min == null)
                min = p;

            if (min.getValue() > p.getValue())
                min = p;
        }

        return min;
    }

    private List<Point> spread(List<Point> stock) {
        Point max = null;
        Point min = null;

        for (Point p : stock) {
            if (min == null)
                min = p;

            if (max == null)
                max = p;

            if (p.getValue() < min.getValue())
                min = p;

            if (p.getValue() > max.getValue())
                max = p;
        }

        List<Point> result = new ArrayList<>();

        if (min.getPosition() < max.getPosition()) {
            result.add(min);
            result.add(max);
        } else {
            result.add(max);
            result.add(min);
        }
        return result;
    }
}
