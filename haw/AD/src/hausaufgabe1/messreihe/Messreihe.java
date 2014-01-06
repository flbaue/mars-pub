/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * Messreihe.java
 * Florian Bauer
 * flbaue@posteo.de
 * 26.10.2013
 */
package hausaufgabe1.messreihe;

/**
 * Messreihe
 * Interface für ein Messreihenkonstrukt.
 *
 * @author Florian Bauer
 */
public interface Messreihe {
    /**
     * Returns the average value.
     *
     * @return der mittelwert als double
     */
    double mittelwert();

    /**
     * Returns the standard deviation.
     *
     * @return die varianz als double
     */
    double varianz();

    /**
     * Adds a value.
     *
     * @param w den nächste Messwert hinzufügen.
     */
    void addWert(double w);
}
