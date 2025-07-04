package code66.algo.dp;

/**
 * 是的，丑数（Ugly Number） 的正确定义是：只包含质因数 2、3 或 5 的正整数。换句话说，一个丑数可以表示为：
 * 丑数 = 2^a + 3^b + 5^c (a,b,c >= 0)
 * <p>
 * https://leetcode.cn/problems/ugly-number-ii/
 */
public class Code04_UglyNumberII {

    public static void main(String[] args) {
        System.out.println(new Code04_UglyNumberII().nthUglyNumber2(37));
    }

    // 暴力计算
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int N = 1;
        for (int i = 0; i < n; i++) {
            int res = 0;
            while (N > 0) {
                if (isUgly(N)) {
                    res = N;
                    N++;
                    break;
                }
                N++;
            }
            ugly[i] = res;
        }
        return ugly[n - 1];
    }

    private boolean isUgly(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        // 给定一个值n，判断这个值是不是丑数
        int[] factor = {2, 3, 5};
        for (int j : factor) {
            while (n % j == 0) {
                n /= j;
            }
        }
        return n == 1; // 当N变成1，那么说明能狗被除尽
    }

    // 最优解
    // 思想：准备3个指针，因为下个位置的值，必须是能被2，3，5除尽，所以有一下规律
    //  i2：代表当前数乘以2，所代表的值
    //  i3: 代表当前数乘以3，所代表的值
    //  i5: 代表当前数乘以5，所代表的值
    // 如何获取下一个位置所代表的值，那么就是上面三个所代表的值小值。
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        // 从1位置开始填充
        dp[1] = 1;
        for (int i = 2, i2 = 1, i3 = 1, i5 = 1; i <= n; i++) {
            int a2 = dp[i2] * 2;
            int a3 = dp[i3] * 3;
            int a5 = dp[i5] * 5;
            int min = Math.min(a2, Math.min(a3, a5));
            if (min == a2) i2++;
            if (min == a3) i3++;
            if (min == a5) i5++;
            dp[i] = min;
        }
        return dp[n];
    }

}
