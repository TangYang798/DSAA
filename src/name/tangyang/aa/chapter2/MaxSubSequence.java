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

    public static int getSumOfMaxSubSeq(int[] numbers, int length) {
        int maxSum = 0;
        int thisSum = 0;
//        int start = -1;
//        int end = -1;
//        int tmpStart = -1;
//        boolean flag = false;
        for (int i=0; i<length; i++) {
             thisSum += numbers[i];
             if (thisSum > maxSum) {
//                 if (flag) start = i;
//                 flag = false;
//                 end = i;
                 maxSum = thisSum;
             } else if (thisSum < 0) {
//                 flag = true;
//                 tmpStart = i;
                 thisSum = 0;
             }
        }
//        System.out.println("index  start " + start + "  end " + end);
        return maxSum;
    }
}
