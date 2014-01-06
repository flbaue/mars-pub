package hausaufgabe6;

/**
 * Created by florian on 12.12.13.
 */
public class MuesliZutat implements Comparable<MuesliZutat> {

    private static int count = 0;

    private final int id;
    private final int volumen;
    private final int preis;

    public MuesliZutat(int volumen, int preis) {
        this.volumen = volumen;
        this.preis = preis;
        id = count;
        count += 1;
    }

    public int getVolumen() {
        return volumen;
    }

    public int getPreis() {
        return preis;
    }

    public double getVolumenPreis() {
        return preis / volumen;
    }

    @Override
    public int compareTo(MuesliZutat o) {

        if (this.getVolumenPreis() < o.getVolumenPreis()) {
            return -1;
        }
        if (this.getVolumenPreis() > o.getVolumenPreis()) {
            return +1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return " {id=" + id + " volumen=" + volumen + " preis=" + preis + " volumenpreis=" + (preis / volumen) + "} ";
    }
}
