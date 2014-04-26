import Collections.List;

import java.io.Serializable;

/**
 * Created by Florian Bauer on 23.03.14. flbaue@posteo.de
 */
public class RangeCheckUtils {

    public static boolean isValueInRange(final long value, final long minValue, final long maxValue) {
        return value >= minValue && value <= maxValue;
    }

    public static boolean isValueInRange(final double value, final double minValue, final double maxValue,
                                         final double delta) {
        return value >= (minValue - delta) && value <= (maxValue + delta);
    }

    public static <T> boolean isValueInRange(final Comparable<T> value, final T minValue, final T maxValue) {
        return value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0;
    }

    public static boolean isValueValid(final long value, final long[] validValues) {
        for (long currentValue : validValues) {
            if (value == currentValue) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Serializable & Comparable<T>> boolean isValueInRange(final T value,
                                                                                  final ValueRange<T> valueRange) {
        return valueRange.contains(value);
    }

    public static <T extends Serializable & Comparable<T>> boolean isValueInMultiRange(final T value,
                                                                                       final List<ValueRange<T>>
                                                                                               valueRanges) {
        for (final ValueRange<T> valueRange : valueRanges) {
            if (valueRange.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
