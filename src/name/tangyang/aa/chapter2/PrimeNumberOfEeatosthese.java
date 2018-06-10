package name.tangyang.aa.chapter2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * horner method p36
 * 厄拉多塞筛法
 */
public class PrimeNumberOfEeatosthese {
    public static void main(String[] args) {
        int target = 3000000;
        Integer[] results = getPrimeNumbers(target);
        System.out.println(Arrays.toString(results));
    }

    public static Integer[] getPrimeNumbers(int limit) {
        boolean[] source = new boolean[limit];
        double middle = Math.sqrt(limit);
        for (int i=2; i<middle; i++) {
            for (int k=2, j=k*i; j<limit; j=++k*i) {
                source[j] = true;
            }
        }
        List<Integer> results = new ArrayList<>();
        for (int i=1; i<limit; i++) {
            if (!source[i]) results.add(i);
        }
        return results.toArray(new Integer[results.size()]);
    }

}
