package problems;

import java.util.*;

public class Main4 {

    /**
     * 在一款赛车游戏中，参赛车辆必须在比赛前通过网络注册。每辆车都会被分配一个注册号码，
     * 该号码会被存储在数据库中。注册号码由数字 0-9 组成，可以为正数也可以为负数。
     * 负数表示该车辆已经在线注册过，而正数表示该车辆是新注册的车辆。在比赛开始前，
     * 系统会自动为每辆车分配一个赛道号。赛道号是该车辆注册号码的最小排列，且不能以零开头。
     * 请你编写一个算法，生成每辆车的赛道号。
     * @param regNum
     * @return
     */
    public static int generateTrackNumber(int regNum) {
        if (regNum == 0) return 0;

        boolean isNegative = regNum < 0;
        char[] digits = Integer.toString(Math.abs(regNum)).toCharArray();
        Arrays.sort(digits);

        if (isNegative) {
            // 对于负数，最大排列（因为负数越大越小）
            reverse(digits);
            return -Integer.parseInt(new String(digits));
        } else {
            // 对于正数，最小排列且不能以0开头
            int firstNonZero = 0;
            while (firstNonZero < digits.length && digits[firstNonZero] == '0') {
                firstNonZero++;
            }
            if (firstNonZero == digits.length) {
                // 全是0
                return 0;
            }
            // 把第一个非0和第一个0交换
            char temp = digits[0];
            digits[0] = digits[firstNonZero];
            digits[firstNonZero] = temp;
            return Integer.parseInt(new String(digits));
        }
    }

    private static void reverse(char[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            char t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        // 多个边界测试用例
        System.out.println(generateTrackNumber(310));      // 103
        System.out.println(generateTrackNumber(1003));     // 1003
        System.out.println(generateTrackNumber(0));        // 0
        System.out.println(generateTrackNumber(-310));     // -310
        System.out.println(generateTrackNumber(-1003));    // -3100
        System.out.println(generateTrackNumber(987654321)); // 123456789
        System.out.println(generateTrackNumber(-987654321));// -987654321
        System.out.println(generateTrackNumber(1000));     // 1000
        System.out.println(generateTrackNumber(10));       // 10
        System.out.println(generateTrackNumber(-10));      // -10
        System.out.println(generateTrackNumber(1));        // 1
        System.out.println(generateTrackNumber(-1));       // -1
        System.out.println(generateTrackNumber(100));      // 100
        System.out.println(generateTrackNumber(-100));     // -100
    }
}