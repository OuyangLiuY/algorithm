package algo.zuo.code073;

import java.util.HashMap;
import java.util.Map;

public class Code03_TargetSum {


    public int findTargetSumWays(int[] nums, int target) {
        return f1(nums, target, 0, 0);
    }

    // 当来到i位置，那么0～ i-1 上已经形成的累加和是sum，
    // num【i～】 范围上，每个数字可以标记+或者-
    // 最终形成累加和为target的不同表达数
    private int f1(int[] nums, int target, int i, int sum) {
        if (i == nums.length)
            return sum == target ? 1 : 0;
        return f1(nums, target, i + 1, sum + nums[i])
                + f1(nums, target, i + 1, sum - nums[i]);
    }

    // 记忆话搜索版本
    public int findTargetSumWays2(int[] nums, int target) {
        //由于 sum 可以为负数，所以不能使用dp[i][sum]。可以使用二级hash表来表示
        Map<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
        return f2(nums, target, 0, 0, dp);
    }

    private int f2(int[] nums, int target, int i, int sum, Map<Integer, HashMap<Integer, Integer>> dp) {
        if (i == nums.length)
            return sum == target ? 1 : 0;
        // 尝试从缓存表中获取
        if (dp.containsKey(i) && dp.get(i).containsKey(sum))
            return dp.get(i).get(sum);
        int ans = f2(nums, target, i + 1, sum + nums[i], dp)
                + f2(nums, target, i + 1, sum - nums[i], dp);
        dp.putIfAbsent(i, new HashMap<>());
        dp.get(i).put(sum, ans);
        return ans;
    }

    // 为了解决hash常数慢，采用评移技巧，因为sum可能为负数，所以 sum + s的技巧让其保证在大于0的数组中
    public int findTargetSumWays3(int[] nums, int target) {
        //由于 sum 可以为负数，所以不能使用dp[i][sum]。可以使用二级hash表来表示
        int S = 0;
        for (int num : nums) {
            S += num;
        }
        if (target < -S || target > S) return 0; // 越界，说明sum永远不可能为target
        int N = nums.length;
        int M = 2 * S + 1;
        int[][] dp = new int[N + 1][M];
        // 原来：dp[N][target]
        dp[N][target + S] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = -S; j <= S; j++) {
                if (j + nums[i] + S < M)
                    // 原来：dp[i][j] = dp[i+1][j + nums[i]]
                    dp[i][j + S] = dp[i + 1][j + nums[i] + S];
                if (j - nums[i] + S >= 0)
                    // 原来：dp[i][j] = dp[i+1][j - nums[i]]
                    dp[i][j + S] += dp[i + 1][j - nums[i] + S];
            }
        }
        return dp[0][0 + S];
    }
    public static int findTargetSumWays4(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum < target || ((target & 1) ^ (sum & 1)) == 1) {
            return 0;
        }
        return subsets(nums, (target + sum) >> 1);
    }

    // 求非负数组nums有多少个子序列累加和是t
    // 01背包问题(子集累加和严格是t) + 空间压缩
    // dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
    public static int subsets(int[] nums, int t) {
        if (t < 0) {
            return 0;
        }
        int[] dp = new int[t + 1];
        dp[0] = 1;
        for (int num : nums) { // i省略了
            for (int j = t; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[t];
    }
}
