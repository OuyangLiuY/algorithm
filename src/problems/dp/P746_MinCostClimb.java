package problems.dp;

/**
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 */
public class P746_MinCostClimb {

    public int minCostClimbingStairs(int[] cost) {
        return Math.min(f1(cost, 0), f1(cost, 1));
    }

    // 从某一个位置出发，的最小花费
    private int f1(int[] cost, int i) {
        if (i >= cost.length) return 0;
        int curCost = cost[i];
        int c1 = f1(cost, i + 1);
        int c2 = f1(cost, i + 2);
        return curCost + Math.min(c1, c2);
    }

    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
