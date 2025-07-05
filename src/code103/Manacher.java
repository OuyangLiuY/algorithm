package code103;

/**
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 */
public class Manacher {
    public static void main(String[] args) {
        System.out.println(new Manacher().longestPalindrome("banana"));
        System.out.println(new Manacher().longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        String ans = "";
        String larg;
        for (int i = 0; i < s.length(); i++) {
            String largest = midAround(s, i, i);
            String largestOld = midAround(s, i, i + 1);
            if (largest.length() > largestOld.length()) larg = largest;
            else larg = largestOld;
            if (larg.length() > ans.length()) {
                ans = larg;
            }
        }
        return ans;
    }

    private String midAround(String s, int L, int R) {

        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return s.substring(L + 1, R);
    }

    private String enhance(String s) {
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char aChar : chars) {
            result.append(aChar).append("#");
        }
        result.append("#");
        return result.toString();
    }
}
