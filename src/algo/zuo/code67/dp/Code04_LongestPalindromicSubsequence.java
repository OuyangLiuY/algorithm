package algo.zuo.code67.dp;


import java.util.Arrays;

// 最长回文子序列
// 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度
// 测试链接 : https://leetcode.cn/problems/longest-palindromic-subsequence/
public class Code04_LongestPalindromicSubsequence {


    public static int longestPalindromeSubseq1(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        return f1(s, 0, n - 1);
    }

    // 回文，【l】 = 【r】，那么要 长度+2，+ （l+1， r-1）
    // 不想等，那么求（l+1，r），或者（l，r-1）的最大
    private static int f1(char[] s, int l, int r) {
        if (l == r) return 1;
        if (l + 1 == r) return s[l] == s[r] ? 2 : 1; // 相邻两个字符想等 bb，
        if (s[l] == s[r]) return 2 + f1(s, l + 1, r - 1); // b …… b
        else return Math.max(f1(s, l + 1, r), f1(s, l, r - 1)); // b …… a
    }

    public static int longestPalindromeSubseq2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f2(s, 0, n - 1, dp);
    }

    // 回文，【l】 = 【r】，那么要 长度+2，+ （l+1， r-1）
    // 不想等，那么求（l+1，r），或者（l，r-1）的最大
    private static int f2(char[] s, int l, int r, int[][] dp) {
        if (l == r) return 1;
        if (l + 1 == r) return s[l] == s[r] ? 2 : 1; // 相邻两个字符想等 bb，
        if (dp[l][r] != -1) return dp[l][r];
        int ans = 0;
        if (s[l] == s[r]) ans = 2 + f2(s, l + 1, r - 1, dp); // b …… b
        else ans = Math.max(f2(s, l + 1, r, dp), f2(s, l, r - 1, dp)); // b …… a
        dp[l][r] = ans;
        return ans;
    }

    public static int longestPalindromeSubseq3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int l = n - 1; l >= 0; l--) {
            dp[l][l] = 1;
            if (l + 1 < n) {
                dp[l][l + 1] = s[l] == s[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                if (s[l] == s[r]) {
                    dp[l][r] = 2 + dp[l + 1][r - 1];
                } else {
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static int longestPalindromeSubseq4(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n];
        for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {
            // dp[l] : 想象中的dp[l][l]
            dp[l] = 1;
            if (l + 1 < n) {
                leftDown = dp[l + 1];
                // dp[l+1] : 想象中的dp[l][l+1]
                dp[l + 1] = s[l] == s[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                backup = dp[r];
                if (s[l] == s[r]) {
                    dp[r] = 2 + leftDown;
                } else {
                    dp[r] = Math.max(dp[r], dp[r - 1]);
                }
                leftDown = backup;
            }
        }
        return dp[n - 1];
    }
}
