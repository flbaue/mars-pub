/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

package hausaufgabe5;

import org.junit.Assert;
import org.junit.Test;

import static hausaufgabe5.Implementation.f_1;
import static hausaufgabe5.Implementation.f_2;

/**
 * User: flbaue
 * Date: 30.11.13
 * Time: 18:45
 */
public class ImplementationTest {
    @Test
    public void testF() throws Exception {

        Assert.assertEquals(1, f_1(0));
        Assert.assertEquals(1, f_1(1));
        Assert.assertEquals(1, f_1(2));
        Assert.assertEquals(6, f_1(3));
        Assert.assertEquals(11, f_1(4));
        Assert.assertEquals(26, f_1(5));
        Assert.assertEquals(66, f_1(6));
        Assert.assertEquals(151, f_1(7));
        Assert.assertEquals(361, f_1(8));
        Assert.assertEquals(861, f_1(9));

        for (int i = 0; i < 100000; i++) {

            System.out.print("f(" + i + ") = ");
            System.out.print(f_1(i) + "\n");
        }

    }

    @Test
    public void testF2() throws Exception {
        Implementation im = new Implementation();

        for (int i = 0; i < 15; i++) {

            System.out.println("standard:\tf(" + i + ") = " + im.f1(i));
            im.printCounter();

            System.out.println("optimized:\tf(" + i + ") = " + im.f2(i));
            im.printCounter();

            System.out.println("");
        }
    }

    @Test
    public void testF2Max() throws Exception {
        for (int i = 0; i < 100000; i++) {

            System.out.print("f(" + i + ") = ");
            System.out.print(f_2(i) + "\n");
        }
    }
}
