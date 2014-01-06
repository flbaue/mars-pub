/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * DefaultMessreihe.java
 * Florian Bauer
 * flbaue@posteo.de
 * 26.10.2013
 */
package hausaufgabe1.messreihe;

import java.util.ArrayList;
import java.util.List;

/**
 * Impl1Messreihe
 * Implementierung des Messreihe interfaces mit naivem Algorithmus.
 *
 * @author Florian Bauer
 */
public class MessreiheA implements Messreihe {

    List<Double> werte = new ArrayList<Double>();

    /**
     * {@inheritDoc }
     */
    @Override
    public double mittelwert() {

        double sum = 0;
        final int n = werte.size();
        for (Double w : werte) {
            sum += w;
        }
        return (1.0 / n) * sum;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double varianz() {

        double sum = 0;
        final double factor = 1.0 / (werte.size() - 1);
        for (Double w : werte) {
            sum += Math.pow((w - mittelwert()), 2);
        }
        return Math.sqrt(factor * sum);
    }

    @Override
    public void addWert(double w) {
        werte.add(w);
    }

}
