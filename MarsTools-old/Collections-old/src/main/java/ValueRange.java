import java.io.Serializable;

/**
 * Created by Florian Bauer on 23.03.14. flbaue@posteo.de
 */
public class ValueRange<T extends Serializable & Comparable<T>> {

    private final T minValue;
    private final T maxValue;

    public ValueRange(final T minValue, final T maxValue) {
        //TODO Copy objects for reference safety
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public final T getMinValue() {
        //TODO Copy object for reference safety
        return minValue;
    }

    public final T getMaxValue() {
        //TODO Copy object for reference safety
        return maxValue;
    }

    public boolean contains(final T value) {
        return value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0;
    }

    public String toString() {
        return "ValueRange [" + minValue + " -- " + maxValue + "]";
    }
}
