package code66.algo.dp;

/**
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
 *
 * 因为结果可能很大，所以返回答案需要对 10^9 + 7 取余
 *
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串
 *
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是
 *
 * 测试链接 : https://leetcode.cn/problems/distinct-subsequences-ii/
 */
public class Code08_DistinctSubsequencesII {


    // 时间复杂度O(n)，n是字符串s的长度
    public static int distinctSubseqII(String s) {
        int mod = 1000000007;
        char[] str = s.toCharArray();
        int[] cnt = new int[26];    // 代表以每个字符开始，它所能代表的个数
        int all = 1, newAdd;  // all = 1，假设空字符也算一个
        for (char x : str) {
            newAdd = (all - cnt[x - 'a'] + mod) % mod;  // all - 当前字符所代表的值，防止重复计算，
            cnt[x - 'a'] = (cnt[x - 'a'] + newAdd) % mod;   // 最终结果 当前字符位置 + 新增的，就是答案
            all = (all + newAdd) % mod;
        }
        return (all - 1 + mod) % mod;
    }
}
