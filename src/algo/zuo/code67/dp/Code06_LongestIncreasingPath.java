package algo.zuo.code67.dp;


// 矩阵中的最长递增路径
// 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度
// 对于每个单元格，你可以往上，下，左，右四个方向移动
// 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）
// 测试链接 : https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
public class Code06_LongestIncreasingPath {


    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, f1(matrix, i, j));
            }
        }
        return ans;
    }

    // 边界条件：
    // cur 要小于下一个位置的数，
    // 只要不越界就行，那么需要判断四个位置，上下，左右，都不能越界
    private int f1(int[][] matrix, int i, int j) {
        int l = 0, r, u = 0, d = 0;
        int cur = matrix[i][j];
        int ans = 0;
        if (i - 1 >= 0 && cur < matrix[i - 1][j]) {
            l = f1(matrix, i - 1, j);
            ans = Math.max(ans, l);
        }
        if (i + 1 < matrix.length && cur < matrix[i + 1][j]) {
            r = f1(matrix, i + 1, j);
            ans = Math.max(ans, r);
        }
        if (j - 1 >= 0 && cur < matrix[i][j - 1]) {
            u = f1(matrix, i, j - 1);
            ans = Math.max(ans, u);
        }
        if (j + 1 < matrix[0].length && cur < matrix[i][j + 1]) {
            d = f1(matrix, i, j + 1);
            ans = Math.max(ans, d);
        }
        return ans + 1;
    }

    public int longestIncreasingPath2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, f2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    private int f2(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j]; // 从缓存中拿出来
        int cur = matrix[i][j];
        int ans = 0;
        if (i - 1 >= 0 && cur < matrix[i - 1][j]) {
            ans = Math.max(ans, f2(matrix, i - 1, j, dp));
        }
        if (i + 1 < matrix.length && cur < matrix[i + 1][j]) {
            ans = Math.max(ans, f2(matrix, i + 1, j, dp));
        }
        if (j - 1 >= 0 && cur < matrix[i][j - 1]) {
            ans = Math.max(ans, f2(matrix, i, j - 1, dp));
        }
        if (j + 1 < matrix[0].length && cur < matrix[i][j + 1]) {
            ans = Math.max(ans, f2(matrix, i, j + 1, dp));
        }
        dp[i][j] = ans + 1;
        return ans + 1;
    }
}
