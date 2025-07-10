package algo.zuo.code67.dp;

import java.util.Arrays;

/**
 * 最长公共子序列
 * https://leetcode.cn/problems/longest-common-subsequence/
 */
public class Code03_LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        return f1(s1, s2, s1.length - 1, s2.length - 1);
    }

    // 以i1，i2结尾最长子序列
    private int f1(char[] s1, char[] s2, int i1, int i2) {
        if (i1 < 0 || i2 < 0) return 0;
        int p1 = f1(s1, s2, i1 - 1, i2 - 1);    // s1，s2，都不要最后一个字符的情况
        int p2 = f1(s1, s2, i1 - 1, i2);          // s1，不要最后一个字符，s2要最后一个字符
        int p3 = f1(s1, s2, i1, i2 - 1);          // s1 要最后一个字符，s2不要
        int p4 = s1[i1] == s2[i2] ? p1 + 1 : 0;     // 当最后一个字符想等情况下，结果+1，并且继续走p1的情况
        return Math.max(Math.max(p1, p2), Math.max(p4, p3));
    }


    public int longestCommonSubsequence2(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        return f2(s1, s2, s1.length, s2.length);
    }

    // 以长度从len1，和len2为基准的情况下，求最长子序列
    private int f2(char[] s1, char[] s2, int len1, int len2) {
        if (len1 == 0 || len2 == 0) return 0;
        int ans = 0;
        // s1和s2最后一个字符想等情况下
        if (s1[len1 - 1] == s2[len2 - 1]) ans = f2(s1, s2, len1 - 1, len2 - 1) + 1;
            // 最后一个字符不想等，那么求len1 -1 的情况下和len2 - 1的情况哪个最大
        else ans = Math.max(f2(s1, s2, len1 - 1, len2), f2(s1, s2, len1, len2 - 1));
        return ans;
    }

    // 以长度为基准的情况下，使用缓存版本
    public int longestCommonSubsequence3(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int N = s1.length;
        int M = s2.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f3(s1, s2, N, M, dp);
    }

    // 以长度从len1，和len2为基准的情况下，求最长子序列
    private int f3(char[] s1, char[] s2, int len1, int len2, int[][] dp) {
        if (len1 == 0 || len2 == 0) return 0;
        if (dp[len1][len2] != -1) return dp[len1][len2];
        int ans = 0;
        // s1和s2最后一个字符想等情况下
        if (s1[len1 - 1] == s2[len2 - 1]) ans = f3(s1, s2, len1 - 1, len2 - 1, dp) + 1;
            // 最后一个字符不想等，那么求len1 -1 的情况下和len2 - 1的情况哪个最大
        else ans = Math.max(f3(s1, s2, len1 - 1, len2, dp), f3(s1, s2, len1, len2 - 1, dp));
        dp[len1][len2] = ans;
        return ans;
    }

    // 严格位置动态规划
    public int longestCommonSubsequence4(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int N = s1.length + 1;
        int M = s2.length + 1;
        int[][] dp = new int[N][M];
        // 怎么依耐的，就是来到i，j位置的时候，它依耐，左上，上面，i-1和j-1的位置。
        // 从左往右，依次填入, 最开始0位置都是0，因为当任意一个长度为0的时候，最长公共子序列就是0
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[N - 1][M - 1];
    }


    // 严格位置依赖的动态规划 + 空间压缩
    public static int longestCommonSubsequence5(String str1, String str2) {
        char[] s1, s2;
        if (str1.length() >= str2.length()) {
            s1 = str1.toCharArray();
            s2 = str2.toCharArray();
        } else {
            s1 = str2.toCharArray();
            s2 = str1.toCharArray();
        }
        int n = s1.length;
        int m = s2.length;
        int[] dp = new int[m + 1];
        for (int len1 = 1; len1 <= n; len1++) {
            int leftUp = 0, backup;
            for (int len2 = 1; len2 <= m; len2++) {
                backup = dp[len2];
                if (s1[len1 - 1] == s2[len2 - 1]) {
                    dp[len2] = 1 + leftUp;
                } else {
                    dp[len2] = Math.max(dp[len2], dp[len2 - 1]);
                }
                leftUp = backup;
            }
        }
        return dp[m];
    }

}
