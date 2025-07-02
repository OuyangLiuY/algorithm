package code66.algo.dp;

import java.util.Arrays;

public class Code03_DecodingWays2 {
    public static long mod = 1000000007;
    public int numDecodings(String s) {
        return (int)f1(s.toCharArray(), 0);
    }

    // 暴力递归：
    // 如何递归，仍然有三种大情况，
    // 情况1: i单独面对0，那么没法转，后续不成立，返回0
    // 情况2: 选择[i] 位置数，继续尝试 i+1 位置
    //      2.1: 当i的值为*，那么就 * 可以变成（1-9），那么总共的可能性就是 9*f(i+1)
    //      2.2: 当i的值为数值，总可能性 f(i+1)
    // 情况3: 尝试选择[i]和[i+1]，并且两个和小于26， 继续尝试 从 i + 2 开始
    //      3.0: i为数值，i+1 也为数值。那就是就是之前讨论的结果：f(i+2)
    //      3.1: i的值为数值，i+1为*
    //          3.1.1: i为1，那么i+1为*，并且组成和<=26，那么*只能变成1-9， 总可能性：9*f(i+2)
    //          3.1.2: i为2，那么i+1为*，那么*只能变成1-6，总可能性：6*f(i+2)
    //          3.1.3：i为3，那么i+1 为*，*不能变成任何数，总可能性为0
    //      3.2: i的值为*，i+1为数值
    //          3.2.1: i+1 为1-6，* 只能变成 1和2，总可能性：2*f(i+2)
    //          3.2.2: i+1的数值大于6，那么*只能变成1，总可能性：f(i+2)
    //      3.3: i的值为*，i+1的值为*
    //          3.3.1: 第一个*可以变成1，第二个*可以变成1-9，
    //          3.3.2: 第一个*变成2，那么第二个*只能是1-6.
    //          3.3.3: 第一个*不能变成大于3的值，因此当都为*的时候，总共就只有15*f(i+2)
    private long f1(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') return 0;
        // i的位置为*，想单独转
        long ans = f1(str, i + 1) * (str[i] == '*' ? 9 : 1); // i 位置为*，可以转成1-9，如果不是，那么就是只有f1
        if ((i + 1) < str.length) {// [i]和[i+1]  <= 26
            if (str[i] != '*') {
                if (str[i + 1] != '*') {
                    // num,num
                    if ((str[i] - '0') * 10 + (str[i + 1] - '0') <= 26) {
                        ans += f1(str, i + 2);
                    }
                } else {
                    // num  *
                    if (str[i] == '1') {
                        ans += 9 * f1(str, i + 2);
                    }
                    if (str[i] == '2') {
                        ans += 6 * f1(str, i + 2);
                    }
                }
            } else {
                // *
                if (str[i + 1] != '*') {
                    // * num
                    if (str[i + 1] <= '6') {
                        ans += 2 * f1(str, i + 2);
                    } else {
                        ans += f1(str, i + 2);
                    }
                } else {
                    // * *
                    ans += 15 * f1(str, i + 2);
                }
            }
        }
        ans %= mod;
        return ans;
    }

    public int numDecodings2(String s) {
        long[] dp = new long[s.length()];
        Arrays.fill(dp, -1);
        return (int)f2(s.toCharArray(), 0, dp);
    }

    // dp
    private long f2(char[] str, int i, long[] dp) {
        if (i == str.length) {
            return 1;
        }
        if (dp[i] != -1) return dp[i];
        if (str[i] == '0') return 0;
        // i的位置为*，想单独转
        long ans = f2(str, i + 1, dp) * (str[i] == '*' ? 9 : 1); // i 位置为*，可以转成1-9，如果不是，那么就是只有f1
        if ((i + 1) < str.length) {// [i]和[i+1]  <= 26
            if (str[i] != '*') {
                if (str[i + 1] != '*') {
                    // num,num
                    if ((str[i] - '0') * 10 + (str[i + 1] - '0') <= 26) {
                        ans += f2(str, i + 2, dp);
                    }
                } else {
                    // num  *
                    if (str[i] == '1') {
                        ans += 9 * f2(str, i + 2, dp);
                    }
                    if (str[i] == '2') {
                        ans += 6 * f2(str, i + 2, dp);
                    }
                }
            } else {
                // *
                if (str[i + 1] != '*') {
                    // * num
                    if (str[i + 1] - '0' <= 6) {
                        ans += 2 * f2(str, i + 2, dp);
                    } else {
                        ans += f2(str, i + 2, dp);
                    }
                } else {
                    // * *
                    ans += 15 * f2(str, i + 2, dp);
                }
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }

    public static int numDecodings3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                dp[i] = (s[i] == '*' ? 9 : 1) * dp[i + 1];
                if (i + 1 < n) {
                    if (s[i] != '*') {
                        if (s[i + 1] != '*') {
                            if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                                dp[i] += dp[i + 2];
                            }
                        } else {
                            if (s[i] == '1') {
                                dp[i] += dp[i + 2] * 9;
                            }
                            if (s[i] == '2') {
                                dp[i] += dp[i + 2] * 6;
                            }
                        }
                    } else {
                        if (s[i + 1] != '*') {
                            if (s[i + 1] <= '6') {
                                dp[i] += dp[i + 2] * 2;
                            } else {
                                dp[i] += dp[i + 2];
                            }
                        } else {
                            dp[i] += dp[i + 2] * 15;
                        }
                    }
                }
                dp[i] %= mod;
            }
        }
        return (int) dp[0];
    }

    public static int numDecodings4(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long cur = 0, next = 1, nextNext = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                cur = (s[i] == '*' ? 9 : 1) * next;
                if (i + 1 < n) {
                    if (s[i] != '*') {
                        if (s[i + 1] != '*') {
                            if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                                cur += nextNext;
                            }
                        } else {
                            if (s[i] == '1') {
                                cur += nextNext * 9;
                            }
                            if (s[i] == '2') {
                                cur += nextNext * 6;
                            }
                        }
                    } else {
                        if (s[i + 1] != '*') {
                            if (s[i + 1] <= '6') {
                                cur += nextNext * 2;
                            } else {
                                cur += nextNext;
                            }
                        } else {
                            cur += nextNext * 15;
                        }
                    }
                }
                cur %= mod;
            }
            nextNext = next;
            next = cur;
            cur = 0;
        }
        return (int) next;
    }
}
