package hausaufgabe6;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 12.12.13.
 */
public class MuesliMixerTest {
    @Test
    public void testMuesliMixen() throws Exception {
        MuesliZutat z0 = new MuesliZutat(1,2);
        MuesliZutat z1 = new MuesliZutat(1,1);
        MuesliZutat z2 = new MuesliZutat(1,3);
        MuesliZutat z3 = new MuesliZutat(1,2);
        List<MuesliZutat> zutaten = new ArrayList<>();
        zutaten.add(z0);
        zutaten.add(z1);
        zutaten.add(z2);
        zutaten.add(z3);

        MuesliMixer mixer = new MuesliMixer(zutaten,5);
        List<MuesliZutat> muesli = mixer.muesliMixen();

        System.out.println(muesli);
    }

    @Test
    public void testMuesliMixenMax() throws Exception {

        List<MuesliZutat> zutaten = new ArrayList<>();

        int max = 100000;
        for(int i = 0; i < max; i++) {
            zutaten.add(new MuesliZutat((int)(Math.random()*10) + 1,(int)(Math.random()*10) + 1));
        }

        MuesliMixer mixer = new MuesliMixer(zutaten,100000);
        List<MuesliZutat> muesli = mixer.muesliMixen();

        System.out.println(muesli);
    }

    @Test
    public void testMuesliMixen2() throws Exception {
        MuesliZutat z0 = new MuesliZutat(1,1);
        MuesliZutat z1 = new MuesliZutat(1,1);
        MuesliZutat z2 = new MuesliZutat(1,1);
        MuesliZutat z3 = new MuesliZutat(2,2);
        List<MuesliZutat> zutaten = new ArrayList<>();
        zutaten.add(z3);
        zutaten.add(z1);
        zutaten.add(z2);
        zutaten.add(z0);

        MuesliMixer mixer = new MuesliMixer(zutaten,2);
        List<MuesliZutat> muesli = mixer.muesliMixen();

        System.out.println(muesli);
    }
}
