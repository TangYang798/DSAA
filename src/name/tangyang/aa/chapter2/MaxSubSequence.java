package name.tangyang.aa.chapter2;

import java.util.Arrays;
import java.util.Random;

/**
 * 求一个实数数组最大的子序列之和 p30
 * @author young
 *
 */
public class MaxSubSequence {

    public static void main(String[] args) {
    	int length = 20;
        int[] numbers = new int[length];
        Random ran = new Random();
        for (int i=0; i<length; i++) {
        	numbers[i] = ran.nextInt(200) - 100;
        }
        System.out.println(Arrays.toString(numbers));
        System.out.println(getSumOfMaxSubSeq(numbers, length));
    }

    //[94, -94, 54, 37, -88, 60, 6, 34, 23, 43, -28, 86, 6, -31, 1, -18, 99, 82, -65, -96]
    //index  start 2  end 17
    //366
    public static int getSumOfMaxSubSeq(int[] numbers, int length) {
        int maxSum = 0;
        int thisSum = 0;
        int start = -1;
        int end = -1;
        int sum = 0;
        for (int i=0; i<length; i++) {
             thisSum += numbers[i];
             if (thisSum > maxSum) {
                 end = i;
                 maxSum = thisSum;
             } else if (thisSum < 0) {
                 thisSum = 0;
             }
        }
        for (int i = end; i > -1; i--) {
            sum += numbers[i];
            if (sum == maxSum) {
                start = i;
                break;
            }
        }
        System.out.println("index  start " + start + "  end " + end);
        return maxSum;
    }
}
