package algo.hot100;

/**
 * 60. Unique Paths
 */
public class P60_UniquePaths {

    public int uniquePaths(int m, int n) {

        return p(0, 0, m, n);
    }

    private int p(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (i >= m || j >= n) {
            return 0;
        }
        return p(i + 1, j, m, n) + p(i, j + 1, m, n);
    }

    public int uniquePaths1(int m, int n) {
        int[][] dp = new int[m][n];
        return p1(dp, 0, 0, m, n);
    }

    private int p1(int[][] dp, int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (i >= m || j >= n) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        dp[i][j] = p1(dp, i + 1, j, m, n) + p1(dp, i, j + 1, m, n);
        return dp[i][j];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];

        return dp[0][0];
    }
}
