/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * RefCounter.java
 * Florian Bauer
 * flbaue@posteo.de
 * 26.10.2013
 */
package counter;

/**
 * @author Florian Bauer
 */
public class StepCounter {
    private int steps;

    public void step() {
//        System.out.println("tick!");
        steps++;
    }

    public int getSteps() {
        return steps;
    }

    public void reset() {
        steps = 0;
    }
}
