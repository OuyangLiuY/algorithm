package algo.zuo.code66.dp;


/**
 *  斐波那契数
 *  斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列
 *  该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 *  也就是：F(0) = 0，F(1) = 1
 *  F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 *  给定 n ，请计算 F(n)
 *  测试链接 : https://leetcode.cn/problems/fibonacci-number/
 *  注意：最优解来自矩阵快速幂，时间复杂度可以做到O(log n)
 *  后续课程一定会讲述！本节课不涉及！
 */
public class Code01_FibonacciNumber {
    public static int fib1(int n) {
        return f1(n);
    }
    // 递归
    private static int f1(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        return f1(i - 1) + f1(i - 2);
    }

    // dp
    public static int fib3(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2]; // F(n - 1) + F(n - 2)，
        }
        return dp[n];
    }
}
