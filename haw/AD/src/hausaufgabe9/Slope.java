package hausaufgabe9;

/**
 * Created by florian on 29.12.13.
 */
public class Slope {

    private final Point min;
    private final Point max;

    public Slope(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public Point getMin() {
        return min;
    }

    public Point getMax() {
        return max;
    }

    public int getSpread() {
        return max.getValue() - min.getValue();
    }

    @Override
    public String toString() {
        return "Slope{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
