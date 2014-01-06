package teststuff;

/**
 * Created by florian on 12.12.13.
 */
public class Test {

    public static void main(String[] args) {

        int n = 10;

        for(int i = 1; i <= n; i++) {
            System.out.println("i=" + i);
            for(int j = 1; j >= i; j--) {
                System.out.println("j=" + j);
            }
            System.out.println("");
        }
    }



}
