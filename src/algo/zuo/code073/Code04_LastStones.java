package algo.zuo.code073;

/**
 * 有一堆石头，用整数数组 `stones` 表示。其中 `stones[i]` 表示第 `i` 块石头的重量。
 * <p>
 * 每一回合，从中选出**任意两块石头**，然后将它们一起粉碎。假设石头的重量分别为 `x` 和 `y`，且 `x <= y`。那么粉碎的可能结果如下：
 * <p>
 * - 如果 `x == y`，那么两块石头都会被完全粉碎；
 * - 如果 `x != y`，那么重量为 `x` 的石头将会完全粉碎，而重量为 `y` 的石头新重量为 `y-x`。
 * <p>
 * 最后，**最多只会剩下一块** 石头。返回此石头 **最小的可能重量** 。如果没有石头剩下，就返回 `0`。
 * https://leetcode.cn/problems/last-stone-weight-ii/
 */
public class Code04_LastStones {

    public static void main(String[] args) {
        Code04_LastStones demo = new Code04_LastStones();
        int[] stones = {2, 7, 4, 1, 8, 1};
        int ans = demo.lastStoneWeightII(stones);
        System.out.println(ans);
    }

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int near = near(stones, sum / 2);
        return sum - near - near;
    }

    // 非负数组stones，子序列累加和不超过t，返回最接近t的累加和
    // 01背包问题（子集累加和尽量接近t）
    private int near(int[] stones, int t) {
        int[] dp = new int[t + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = t; j >= stones[i]; j--) {
                // dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-stones[i]] + stones[i]);
                // dp[i][j] 前i个数，累加和 <= j, 并保证这个累加和尽量大
                // 要 i =》 dp[i-1][j]
                // 不要 i =〉dp[i-1][j - nums[i]] + nums[i]
                // 解释： dp[i-1][j - nums[i]]，去i-1位置上去找，但是这个数不能超过 j - nums[i]
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return dp[t];
    }


}
