package string;

import java.util.*;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 无重复的最长子串。
 */
public class P03_LengthOfLongestSubstring {
    public static void main(String[] args) {
//        System.out.println(new P03_LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new P03_LengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew"));
    }

    // 暴力解：当来到I位置，往左最远能扩的距离，使用Set来检查是否还有重复字符
    public int lengthOfLongestSubstring(String s) {
        Set<Character> map = new HashSet<>();
        // 来到i位置，往左看最大能扩的长度，用map来记录字符串。
        int max = 0;
        for (int cur, i = 0; i < s.length(); i++) {
            cur = i;
            while (cur >= 0) {
                char curChar = s.charAt(cur);
                if (map.contains(curChar)) {
                    break;
                }
                map.add(curChar);
                cur--;
            }
            max = Math.max(max, map.size());
            map.clear();
        }
        return max;
    }

    // 基于滑动窗口思想，
    // 当来到I位置，那么如何一个小的窗口内没有重复值，那么计算大小，
    // 如果当来I位置，有重复值，那么L，左窗口位置来到重复字符+1位置，此时的窗口也是一个不重复的，有效的
    // 依次类推，直到N-1位置，复杂度为O(N)
    public int lengthOfLongestSubstring1(String s) {
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int L = 0;
        // adbc abc bb
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                L = Math.max(L, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - L + 1);
        }
        return max;
    }
}
