/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

package hausaufgabe3.pow;

import org.junit.Test;

public class PowTest {

    @Test
    public void pow1Test() {
        StepCounter counter = new StepCounter();
        double result = Pow.pow1(2, 1000, counter);
        System.out.println(result + " took " + counter.getSteps() + " steps");
    }

    @Test
    public void pow3Test() {
        StepCounter counter = new StepCounter();
        double result = Pow.pow3(2, 1000, counter);
        System.out.println(result + " took " + counter.getSteps() + " steps");
    }

}
