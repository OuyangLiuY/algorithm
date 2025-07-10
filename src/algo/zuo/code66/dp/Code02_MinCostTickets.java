package algo.zuo.code66.dp;


/**
 * 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行
 * 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出
 * 每一项是一个从 1 到 365 的整数
 * 火车票有 三种不同的销售方式
 * 一张 为期1天 的通行证售价为 costs[0] 美元
 * 一张 为期7天 的通行证售价为 costs[1] 美元
 * 一张 为期30天 的通行证售价为 costs[2] 美元
 * 通行证允许数天无限制的旅行
 * 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证
 * 那么我们可以连着旅行 7 天(第2~8天)
 * 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费
 * 测试链接 : https://leetcode.cn/problems/minimum-cost-for-tickets/
 */
public class Code02_MinCostTickets {
    // 无论提交什么方法都带着这个数组      0  1  2
    public static int[] durations = {1, 7, 30};

    // 暴力尝试
    // 如何暴力递归：
    // 当i来到某个位置，有三种情况，
    // cast【0】的情况下求最下，
    // cast【1】的情况下求最下，
    // cost【2】的情况下求最下
    public static int mincostTickets1(int[] days, int[] costs) {
        return f1(days, costs, 0);
    }

    private static int f1(int[] days, int[] costs, int i) {
        // base case,当i来到数据最后一个位置，那么此时没有数据，直接返回
        if (i == days.length) return 0;
        // 当i来到day【i】表示有一趟旅行
        int ans = Integer.MAX_VALUE;
        // 新创建一个索引j，用来计算，j最大能到的位置，那么就是下一次i开始的位置
        for (int k = 0, j = i; k < 3; k++) {
            // 当我随机选了一种的情况下，我管的天数，
            // j 不能超过days长度，当前位置idx所代表的天数 + durations所管的天数 < days
            while (j < days.length && (durations[k] + days[i]) > days[j]) {
                // 因为方案2持续的天数最多，30天
                // 所以while循环最多执行30次
                // 枚举行为可以认为是O(1)
                j++;
            }
            i = j; // 下一次i从j的位置开始计算
            ans = Math.min(ans, costs[k] + f1(days, costs, i));
        }
        return ans;
    }

    // 记忆话搜索
    public static int mincostTickets2(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        for (int i = 0; i < days.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        return f1(days, costs, 0, dp);
    }

    private static int f1(int[] days, int[] costs, int i, int[] dp) {
        // base case,当i来到数据最后一个位置，那么此时没有数据，直接返回
        if (i == days.length) return 0;
        // 如果来到了i位置，但是这个位置上的数据已经被修改过了，所以直接返回缓存的值
        if (dp[i] != Integer.MAX_VALUE) return dp[i];
        // 当i来到day【i】表示有一趟旅行
        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < 3; k++) {
            while (j < days.length && (durations[k] + days[i]) > days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + f1(days, costs, j, dp));// 下一次i从j的位置开始计算
        }
        dp[i] = ans;
        return ans;
    }

    // 严格的动态规划，从顶到底。来到365天位置，返回0，因为没有可用天数，依次从n 到 0位置，开始调用
    // 最大天数为365天，dp = 365
    // 动态规划到转移方程：如何获取，先做出尝试。
    public static int mincostTickets3(int[] days, int[] costs) {
        int N = days.length;
        int[] dp = new int[366];
        for (int i = 0; i < days.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[N] = 0; // if (i == days.length) return 0;
        // 如果来到了i位置，但是这个位置上的数据已经被修改过了，所以直接返回缓存的值
        for (int i = N - 1; i >= 0; i--) {
            for (int k = 0, j = i; k < 3; k++) {
                while (j < days.length && (durations[k] + days[i]) > days[j]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[k] + dp[j]);// 下一次i从j的位置开始计算
            }

        }
        return dp[0]; // 因为递归函数从0开始求，那么最后0位置就最小花费
    }
}
