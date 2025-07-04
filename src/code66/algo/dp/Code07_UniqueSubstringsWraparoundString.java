package code66.algo.dp;

import java.util.Arrays;

/**
 * // 环绕字符串中唯一的子字符串
 * // 定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串
 * // 所以 base 看起来是这样的：
 * // "..zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd.."
 * // 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现
 * // 测试链接 : https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 */
public class Code07_UniqueSubstringsWraparoundString {

    // 时间复杂度O(n)，n是字符串s的长度，字符串base长度为正无穷
    public static int findSubstringInWraproundString(String str) {
        int[] tmp = new int[str.length()];
        // a - 0
        // b - 1
        // z - 25
        for (int i = 0; i < str.length(); i++) {
            tmp[i] = str.charAt(i) - 'a';
        }
        //
        int[] dp = new int[26];
        dp[tmp[0]] = 1;
        for (int i = 1, cur, pre, len = 1; i < tmp.length; i++) {
            cur = tmp[i]; // a?
            pre = tmp[i - 1];
            // str = xyzab
            // i = 1, => y = 2
            // i = 2, => z = 3
            // i = 3, => a = 4
            if ((tmp[i] == 0 && pre == 25) || (pre + 1) == cur) len++;
            else len = 1;
            dp[cur] = Math.max(dp[cur], len);
        }
        int res = 0;
        for (int j : dp) {
            res += j;
        }
        return res;
    }
}
