package algo.zuo.code67.dp;

import java.util.Arrays;

/**
 * 最小路径和
 * https://leetcode.cn/problems/minimum-path-sum/description/
 */
public class Code01_MinPathSum {


    public int minPathSum(int[][] grid) {
        return f1(grid, grid.length - 1, grid[0].length - 1);
    }

    // 递归方法
    // 如何来i，和 j位置？
    // 1.题目定义好了，只能向左，或者向下，
    // 2，所以 两个方向：
    //   也就是说从上面，或者从左边来到i，j位置
    //   左(i-1,j)，和上 (i,j-1)
    // 所以递归套路：
    private int f1(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) return grid[0][0]; // base case
        int left = Integer.MAX_VALUE;
        int up = Integer.MAX_VALUE;
        if (i - 1 >= 0) // 第一行，
            left = f1(grid, i - 1, j);
        if (j - 1 >= 0) // 第一列
            up = f1(grid, i, j - 1);
        return Math.min(left, up) + grid[i][j];
    }

    // 傻缓存版本
    public int minPathSum2(int[][] grid) {
        int N = grid.length;  // N row
        int M = grid[0].length; // M col
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = grid[0][0]; // 填入第一个值
        return f2(grid, grid.length - 1, grid[0].length - 1, dp);
    }

    private int f2(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) return dp[i][j]; // 拿出从缓存
        int ans = 0;
        if (i == 0 && j == 0) ans = grid[0][0];
        else {
            int left = Integer.MAX_VALUE;
            int up = Integer.MAX_VALUE;
            if (i - 1 >= 0) // 第一行，
                left = f2(grid, i - 1, j, dp);
            if (j - 1 >= 0) // 第一列
                up = f2(grid, i, j - 1, dp);
            ans = Math.min(left, up) + grid[i][j];
        }
        dp[i][j] = ans;
        return ans;
    }

    // 严格位置依赖的动态规划
    // 想象有一个二维dp表，并依次填入dp表中每一个位置。
    public int minPathSum3(int[][] grid) {
        int N = grid.length;  // N row
        int M = grid[0].length; // M col
        int[][] dp = new int[N][M];

        dp[0][0] = grid[0][0]; // 填入第一个值
        // 先填入第一行，因为第一行，只依赖左边的位置
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 先填入第一列，因为第一列，只依赖上边的位置
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 然后从 1，1位置开始到i，j位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[N - 1][M - 1];
    }

    //空间压缩
    public int minPathSum4(int[][] grid) {
        int N = grid.length;  // N row
        int M = grid[0].length; // M col
        int[] dp = new int[M];

        dp[0] = grid[0][0]; // 填入第一个值
        // 先填入第一行，因为第一行，只依赖左边的位置
        for (int i = 1; i < M; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        // 然后从 1，1位置开始到i，j位置
        for (int i = 1; i < N; i++) {
            // i = 1，dp表变成想象中二维表的第1行的数据
            // i = 2，dp表变成想象中二维表的第2行的数据
            // ...
            // i = n-1，dp表变成想象中二维表的第n-1行的数据
            dp[0] += grid[i][0];
            for (int j = 1; j < M; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[M - 1];
    }
}
