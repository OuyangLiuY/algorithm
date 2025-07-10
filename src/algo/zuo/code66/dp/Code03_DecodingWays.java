package algo.zuo.code66.dp;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/decode-ways/
 */
public class Code03_DecodingWays {

    public int numDecodings1(String s) {
        return f1(s.toCharArray(), 0);
    }

    // 暴力尝试，如何尝试：
    // 当来到i位置，
    // 情况1: i单独面对0，那么没法转，后续不成立，返回0
    // 情况2: i不是0，那么选择[i] 位置数，继续尝试 i+1 位置
    // 情况3: i不是0，那么尝试选择[i]和[i+1]，并且两个和小于26， 继续尝试 从 i + 2 开始
    private int f1(char[] str, int i) {
        if (i == str.length) { // 当i来到数组越界位置，代表我前面的选择有效，返回1. 转化成的为AAFF，此时从4位置开始没有数据，那么我前面的就是有效的转化
            return 1;
        }
        int ans = 0;
        if (str[i] == '0') return 0;
        else {
            ans = f1(str, i + 1);
            if ((i + 1) < str.length && ((str[i] - '0') * 10 + (str[i + 1] - '0')) <= 26) {// [i]和[i+1]  <= 26
                ans += f1(str, i + 2);
            }
        }
        return ans;
    }

    public int numDecodings2(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return f2(s.toCharArray(), 0, dp);
    }

    private int f2(char[] str, int i, int[] dp) {
        if (i == str.length) return 1;
        if (dp[i] != -1) return dp[i];
        int ans;
        if (str[i] == '0') ans = 0;
        else {
            ans = f2(str, i + 1, dp);
            if ((i + 1) < str.length && ((str[i] - '0') * 10 + (str[i + 1] - '0')) <= 26) {// [i]和[i+1]  <= 26
                ans += f2(str, i + 2, dp);
            }
        }
        dp[i] = ans;
        return ans;
    }

    // 严格位置依赖的动态规划
    public int numDecodings3(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            int ans = 0;
            if (s.charAt(i) == '0') ans = 0;
            else {
                ans = dp[i + 1];
                if ((i + 1) < s.length() && ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0')) <= 26) {// [i]和[i+1]  <= 26
                    ans += dp[i + 2];
                }
            }
            dp[i] = ans;
        }
        return dp[0];
    }

    // 严格位置依赖的动态规划 + 空间压缩
    public static int numDecodings4(String s) {
        // dp[i+1]
        int next = 1;
        // dp[i+2]
        int nextNext = 0;
        for (int i = s.length() - 1, cur; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                cur = 0;
            } else {
                cur = next;
                if (i + 1 < s.length() && ((s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0') <= 26) {
                    cur += nextNext;
                }
            }
            nextNext = next;
            next = cur;
        }
        return next;
    }
}
