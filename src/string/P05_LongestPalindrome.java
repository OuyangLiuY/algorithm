package string;

/**
 * 最长回文
 */
public class P05_LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(new P05_LongestPalindrome().longestPalindrome("banana"));
        System.out.println(new P05_LongestPalindrome().longestPalindrome("cbbd"));
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
}
