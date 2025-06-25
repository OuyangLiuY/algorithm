package problems.string;

public class LargestPalindrome {

    public static void main(String[] args) {

        System.out.println(largestPalindrome("ababa"));
        System.out.println(largestPalindrome("abba"));
    }

    /**
     * 使用中心扩散法，求去最长回文字符串。
     * 如果最长回文为奇数： ababa -> ababa
     * 如果最长回文为偶数： abba  -> abba
     *
     * @param str
     * @return String
     */
    public static String largestPalindrome(String str) {

        String res = "";
        for (int i = 0; i < str.length(); i++) {
            String odd = center(str, i, i);
            String even = center(str, i, i + 1);
            String legest = "";
            if (odd.length() > even.length()) {
                legest = odd;
            }
            if (even.length() > odd.length()) {
                legest = even;
            }
            if (legest.length() > res.length()) {
                res = legest;
            }
        }
        return res;
    }

    private static String center(String str, int L, int R) {

        while (L >= 0 && R < str.length() && str.charAt(L) == str.charAt(R)) {
            L--;
            R++;
        }
        return str.substring(L + 1, R);
    }

}
