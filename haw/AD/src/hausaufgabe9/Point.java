package hausaufgabe9;

/**
 * Created by florian on 28.12.13.
 */
public class Point implements Comparable<Point> {

    public static final String MIN = "MIN";
    public static final String NORMAL = "NORMAL";
    public static final String MAX = "MAX";
    private final int value;
    private final int position;
    private String status;

    public Point(int value, int position) {
        this.value = value;
        this.position = position;
        status = NORMAL;
    }

    public int getPosition() {
        return position;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        if (!status.equals(MIN) && !status.equals(MAX)) {
            throw new IllegalArgumentException("Unsupported status code");
        }

        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (position != point.position) return false;
        if (value != point.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + position;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "pos=" + position +
                ", val=" + value +
                ", stat=" + status +
                '}';
    }
}
