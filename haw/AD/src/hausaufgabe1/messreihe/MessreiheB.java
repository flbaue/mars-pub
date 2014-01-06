/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * Impl2Messreihe.java
 * Florian Bauer
 * flbaue@posteo.de
 * 26.10.2013
 */
package hausaufgabe1.messreihe;

/**
 * Impl2Messreihe
 * <p/>
 * Implementation of the {@link Messreihe} interface using running algorithms
 * for the average and the standard deviation. Algorithms are based on this:
 * http://subluminal.wordpress.com/2008/07/31/running-standard-deviations/
 *
 * @author Florian Bauer
 */
public class MessreiheB implements Messreihe {

    private double avg = 0;
    private double pwrSumAvg = 0;
    private double stdDev = 0;
    private int count = 0;

    /**
     * {@inheritDoc }
     */
    @Override
    public double mittelwert() {
        return avg;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double varianz() {
        return stdDev;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addWert(double value) {
        count++;
        avg += (value - avg) / count;
        pwrSumAvg += (value * value - pwrSumAvg) / count;
        stdDev = Math.sqrt((pwrSumAvg * count - count * avg * avg)
                / (count - 1));

    }
}
