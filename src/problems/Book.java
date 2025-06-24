package problems;

import java.util.*;

public class Book {
    public static List<Integer> optimalOrder(
            int booksNum, int[] avail,
            int studentNum, int[][] reqBooks,
            int[][] issuedBooks) {
        int[] books = Arrays.copyOf(avail, booksNum);
        // 先把已发放的书扣掉
        for (int i = 0; i < studentNum; i++) {
            for (int j = 0; j < booksNum; j++) {
                books[j] -= issuedBooks[i][j];
            }
        }
        // 标记学生是否已完成
        boolean[] finished = new boolean[studentNum];
        List<Integer> order = new ArrayList<>();
        int finishedCount = 0;
        while (finishedCount < studentNum) {
            boolean progress = false;
            for (int i = 0; i < studentNum; i++) {
                if (finished[i]) continue;
                // 检查是否能借到所有需要的书
                boolean canBorrow = true;
                for (int j = 0; j < booksNum; j++) {
                    int need = reqBooks[i][j] - issuedBooks[i][j];
                    if (need > books[j]) {
                        canBorrow = false;
                        break;
                    }
                }
                if (canBorrow) {
                    // 借书
                    for (int j = 0; j < booksNum; j++) {
                        int need = reqBooks[i][j] - issuedBooks[i][j];
                        books[j] -= need;
                    }
                    // 归还书
                    for (int j = 0; j < booksNum; j++) {
                        books[j] += reqBooks[i][j];
                    }
                    finished[i] = true;
                    order.add(i);
                    finishedCount++;
                    progress = true;
                }
            }
            if (!progress) {
                // 一轮下来没有任何进展，说明死锁
                return Collections.singletonList(-1);
            }
        }
        return order;
    }

    // 测试用例
    public static void main(String[] args) {
        // 用例1：每人都能顺利完成
        int booksNum1 = 2;
        int[] avail1 = {1, 2};
        int studentNum1 = 2;
        int[][] reqBooks1 = {{1, 1}, {0, 1}};
        int[][] issuedBooks1 = {{0, 0}, {0, 0}};
        System.out.println(optimalOrder(booksNum1, avail1, studentNum1, 
        reqBooks1, issuedBooks1)); // [0, 1] 或 [1, 0]

        // 用例2：有学生已借书
        int booksNum2 = 2;
        int[] avail2 = {1, 1};
        int studentNum2 = 2;
        int[][] reqBooks2 = {{1, 1}, {1, 1}};
        int[][] issuedBooks2 = {{1, 0}, {0, 1}};
        System.out.println(optimalOrder(booksNum2, avail2, studentNum2,
         reqBooks2, issuedBooks2)); // [0, 1] 或 [1, 0]

        // 用例3：无法完成
        int booksNum3 = 2;
        int[] avail3 = {1, 0};
        int studentNum3 = 2;
        int[][] reqBooks3 = {{1, 1}, {1, 1}};
        int[][] issuedBooks3 = {{0, 0}, {0, 0}};
        System.out.println(optimalOrder(booksNum3, avail3, studentNum3,
        reqBooks3, issuedBooks3)); // [-1]

        // 用例4：只有一个学生
        int booksNum4 = 3;
        int[] avail4 = {1, 1, 1};
        int studentNum4 = 1;
        int[][] reqBooks4 = {{1, 1, 1}};
        int[][] issuedBooks4 = {{0, 0, 0}};
        System.out.println(optimalOrder(booksNum4, avail4, studentNum4,
         reqBooks4, issuedBooks4)); // [0]

        // 用例5：所有书都已发放
        int booksNum5 = 2;
        int[] avail5 = {0, 0};
        int studentNum5 = 2;
        int[][] reqBooks5 = {{1, 0}, {0, 1}};
        int[][] issuedBooks5 = {{1, 0}, {0, 1}};
        System.out.println(optimalOrder(booksNum5, avail5, studentNum5,
         reqBooks5, issuedBooks5)); // [0, 1]
    }
}
