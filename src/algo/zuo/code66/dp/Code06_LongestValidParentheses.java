package algo.zuo.code66.dp;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 测试链接 : https://leetcode.cn/problems/longest-valid-parentheses/
 */
public class Code06_LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(new Code06_LongestValidParentheses().longestValidParentheses("(()()"));
        System.out.println(new Code06_LongestValidParentheses().longestValidParentheses(")()())"));
        System.out.println(new Code06_LongestValidParentheses().longestValidParentheses("())"));
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        dp[0] = 0;
        //1. 必须以i位置结尾，往前推多远，是一个有效的括号。
        //2. 如果当前位置是左括号（，那么不管往前怎么推，都不能是有效的
        //3. 如果当前i位置是右括号，），那么有如下情况：
        //   - 根据dp[i-1] 的答案向左计算。
        //   - 如果当前 [p] 位置的数据是 ，右括号），说明没法扩，那么dp【i】= 0；
        //   - 如果当前p位置的是左括号（，那么dp[i] = dp[i-1] + 2 + dp[p-1]
        for (int i = 1; i < str.length; i++) {
            if (str[i] == '(') dp[i] = 0;
            else {
                int p = i - dp[i - 1] - 1;
                if (p < 0 || str[p] == ')') dp[i] = 0;
                else {
                    dp[i] = dp[i - 1] + 2 + (p > 1 ? dp[p - 1] : 0);
                }
            }
        }
        int max = 0;
        for (int j : dp) {
            max = Math.max(max, j);
        }
        return max;
    }
}
