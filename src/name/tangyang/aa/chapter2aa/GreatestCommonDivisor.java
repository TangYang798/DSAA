package name.tangyang.aa.chapter2aa;

/**
 * 求两个数最大公约数 p32
 * @author NUC
 *
 */
public class GreatestCommonDivisor {
    public static void main(String[] args) {
        int a = 3454;
        int b = 6578;
        System.out.println(getGCD(a, b));
    }

    public static int getGCD(int a, int b) {
        while (b != 0) {
            int remain = a % b;
            a = b;
            b = remain;
        }
        return a;
    }
}
