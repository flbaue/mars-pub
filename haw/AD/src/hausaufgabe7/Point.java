package hausaufgabe7;

/**
 * Created by florian on 28.12.13.
 */
public class Point implements Comparable<Point> {

    public static final int MIN = -1;
    public static final int NORMAL = 0;
    public static final int MAX = 1;
    private final int value;
    private final int position;
    private int status;

    public int getPosition() {
        return position;
    }

    public Point(int value, int position) {
        this.value = value;
        this.position = position;
        status = NORMAL;
    }

    @Override
    public int compareTo(Point o) {
        if (getValue() < o.getValue()) {
            return -1;
        } else if (getValue() > o.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getValue() {
        return value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {

        if (status != MIN && status != MAX) {
            throw new IllegalArgumentException("Unsupported status code");
        }

        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (status != point.status) return false;
        if (value != point.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "pos=" + position +
                ", val=" + value +
                '}';
    }
}
