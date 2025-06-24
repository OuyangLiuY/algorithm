package problems;
import java.util.HashSet;
import java.util.Set;

public class Main3 {
    
    /**
     * 请你编写一个算法，帮助 Tim 计算：以 Tim 的家为公共点，
     * 能画出多少条不同的直线（每条直线都经过 Tim 的家和某个朋友的家）。
     * @param x0
     * @param y0
     * @param friends
     * @return
     */
    public static int countUniqueLines(int x0, int y0, int[][] friends) {
        Set<String> slopes = new HashSet<>();
        for (int[] friend : friends) {
            int dx = friend[0] - x0;
            int dy = friend[1] - y0;
            if (dx == 0 && dy == 0) continue; // 跳过和Tim家重合的点
            if (dx == 0) {
                slopes.add("0/1"); // 竖直线
            } else if (dy == 0) {
                slopes.add("1/0"); // 水平线
            } else {
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                // 统一方向
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }
                slopes.add(dx + "/" + dy);
            }
        }
        return slopes.size();
    }

    private static int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        // 示例用例
        int[][] friends1 = {
            {1, 1},
            {-1, 1},
            {2, 3}
        };
        System.out.println(countUniqueLines(0, 0, friends1)); // 3

        // 边界用例1：所有点在同一直线上
        int[][] friends2 = {
            {1, 1},
            {2, 2},
            {3, 3}
        };
        System.out.println(countUniqueLines(0, 0, friends2)); // 1

        // 边界用例2：有重合点
        int[][] friends3 = {
            {0, 0},
            {1, 0},
            {0, 1}
        };
        System.out.println(countUniqueLines(0, 0, friends3)); // 2

        // 边界用例3：竖直线和水平线
        int[][] friends4 = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };
        System.out.println(countUniqueLines(0, 0, friends4)); // 2

        // 边界用例4：只有一个朋友
        int[][] friends5 = {
            {5, 7}
        };
        System.out.println(countUniqueLines(0, 0, friends5)); // 1

        // 边界用例5：所有点都和Tim家重合
        int[][] friends6 = {
            {0, 0}, {0, 0}
        };
        System.out.println(countUniqueLines(0, 0, friends6)); // 0
    }

    /**
     * 返回字符串中最长回文子串
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private static int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
