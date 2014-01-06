/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * Impl2MessreiheTest.java
 * Florian Bauer
 * flbaue@posteo.de
 * 26.10.2013
 */
package hausaufgabe1.messreihe;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Florian Bauer
 */
public class MessreiheTest {

    @Test
    public void m1test() {
        Messreihe m1 = new MessreiheA();
        m1.addWert(21);
        m1.addWert(23);
        m1.addWert(25);

        assertTrue(m1.mittelwert() == 23);
        assertTrue(m1.varianz() == 2);

    }

    @Test
    public void m2test() {

        Messreihe m2 = new MessreiheB();
        m2.addWert(21);
        m2.addWert(23);
        m2.addWert(25);

        assertTrue(m2.mittelwert() == 23);
        assertTrue(m2.varianz() == 2);
    }

}
