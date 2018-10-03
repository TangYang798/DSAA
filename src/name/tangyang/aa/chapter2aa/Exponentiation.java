package name.tangyang.aa.chapter2aa;

import java.util.Random;

/**
 * 幂运算 p33
 * @author NUC
 *
 */
public class Exponentiation {

    public static void main(String[] args) {
        Random r = new Random();
        int a =  r.nextInt(10);
        int b = r.nextInt(15);
        System.out.println("a - " + a + "   b - " + b);
        System.out.println(Math.pow(a, b));
        System.out.println(getPOW(a, b));
    }

    public static long getPOW(long i, long j) {
        if (j == 0) return 1;
        if (isEven(j)) {
            return getPOW(i * i, j/2);
        } else {
            return getPOW(i * i, j/2) * i;
        }
    }

    public static boolean isEven(long j) {
        if (j % 2 == 0) return true;
        return false;
    }

}
