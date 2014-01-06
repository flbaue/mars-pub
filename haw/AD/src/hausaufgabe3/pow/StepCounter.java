/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

package hausaufgabe3.pow;

public class StepCounter {

    private int steps;

    public StepCounter() {
        steps = 0;
    }

    public void step() {
        steps++;
    }

    public int getSteps() {
        return steps;
    }

}
