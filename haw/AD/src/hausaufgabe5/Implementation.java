/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

package hausaufgabe5;

/**
 * User: flbaue
 * Date: 30.11.13
 * Time: 18:42
 */
public class Implementation {

    private int[] counter;
    private int[] results;

    public static int f_1(int n) {
        Implementation impl = new Implementation();
        return impl.f1(n);
    }

    public static int f_2(int n) {
        Implementation impl = new Implementation();
        return impl.f2(n);
    }

    public int f1(int n) {
        if (counter == null) {
            counter = new int[n + 1];
        }
        counter[n] += 1;

        if (n < 3) {
            return 1;
        } else {
            return f1(n - 1) + 2 * f1(n - 2) + 3 * f1(n - 3);
        }
    }

    public int f2(int n) {
        if (counter == null) {
            counter = new int[n + 1];
        }
        counter[n] += 1;

        if (results == null || results.length == 0) {
            results = new int[n + 1];

            if (results.length > 2)
                results[2] = 1;

            if (results.length > 1)
                results[1] = 1;

            results[0] = 1;
        }

        if (results[n] == 0) {

            int f_1 = results[n - 1];
            if (f_1 == 0) {
                f_1 = f2(n - 1);
            }

            int f_2 = results[n - 2];
            if (f_2 == 0) {
                f_2 = f2(n - 2);
            }

            int f_3 = results[n - 3];
            if (f_3 == 0) {
                f_3 = f2(n - 3);
            }

            results[n] = f_1 + 2 * f_2 + 3 * f_3;
        }
        return results[n];
    }

    public void reset() {
        counter = null;
        results = null;
    }

    public int[] getCounter() {
        return counter;
    }

    public void printCounter() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counter.length; i++) {
            sb.append(i + "=>" + counter[i] + " ");
        }
        System.out.println(sb.toString());
        reset();
    }
}
