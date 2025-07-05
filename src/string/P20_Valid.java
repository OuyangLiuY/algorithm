package string;

import java.util.*;

/**
 * 有效的括号
 * https://leetcode.cn/problems/valid-parentheses/description/
 */
public class P20_Valid {

    public static void main(String[] args) {

    }

    // 栈结构，可以找到最近位置匹配的括号
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty() || stack.peek() != pairs.get(chars[i])) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
