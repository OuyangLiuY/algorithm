package algo.zuo.code67.dp;

/**
 * 单词搜索。
 * 不能改成动态规划的尝试，或者说没有必要改成动态规划。
 * 题目2
 * 单词搜索（无法改成动态规划）
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成
 * 其中"相邻"单元格是那些水平相邻或垂直相邻的单元格
 * 同一个单元格内的字母不允许被重复使用
 * 测试链接 : https://leetcode.cn/problems/word-search/
 */
public class Code02_WordSearch {


    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (f5(board, i, j, chars, 0)) return true; //  从word中 0 位置开始。并且从board中的i，j，也就是任意位置开始
            }
        }
        return false;
    }

    // 此时网格中来到i，j位置，
    // 并且word中来到了k位置
    private boolean f5(char[][] board, int i, int j, char[] word, int k) {
        if (word.length == k) return true; // 单词来到了chars数组长度的位置，那么说明匹配到了
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) return false; // 数据越界位置
        if (board[i][j] != word[k]) return false; // 没有越界，并且当前位置和k位置的数不同，那么结果为false
        char tmp = board[i][j];
        board[i][j] = '0';
        boolean f = f5(board, i + 1, j, word, k + 1) ||
                f5(board, i - 1, j, word, k + 1) ||
                f5(board, i, j + 1, word, k + 1) ||
                f5(board, i, j - 1, word, k + 1);
        board[i][j] = tmp; // 恢复现场，因为题目中要求，不能重复选
        return f;
    }
}
