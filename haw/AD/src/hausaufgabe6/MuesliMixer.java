package hausaufgabe6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by florian on 12.12.13.
 */
public class MuesliMixer {


    private List<MuesliZutat> zutaten;
    private double maxVolumen;

    public MuesliMixer(List<MuesliZutat> zutaten, double maxVolumen) {

        this.zutaten = zutaten;
        this.maxVolumen = maxVolumen;
    }

    public List<MuesliZutat> muesliMixen() {

        Collections.sort(zutaten);
        List<MuesliZutat> muesli = new ArrayList<>();
        return mix(muesli);
    }

    private List<MuesliZutat> mix(List<MuesliZutat> muesli) {

        if (zutaten.isEmpty()) {
            return muesli;
        }

        if (istMuesliVoll(muesli)) {
            return muesli;
        }

        MuesliZutat zutat = zutaten.get(0);
        if (passtRein(zutat, muesli)) {
            muesli.add(zutat);
        }

        zutaten.remove(0);
        return mix(muesli);
    }

    private boolean istMuesliVoll(List<MuesliZutat> muesli) {
        int volumen = 0;
        for (MuesliZutat z : muesli) {
            volumen += z.getVolumen();
        }
        return volumen == maxVolumen;
    }

    private boolean passtRein(MuesliZutat zutat, List<MuesliZutat> muesli) {

        int volumen = zutat.getVolumen();
        for (MuesliZutat z : muesli) {
            volumen += z.getVolumen();
        }
        return volumen <= maxVolumen;
    }

}
