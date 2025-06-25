package problems;

import java.util.*;

public class Main5 {
    // 判断老鼠能否到达奶酪
    public static int canReachCheese(int[][] maze) {
        int n = maze.length, m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        if (maze[0][0] == 0) return 0; // 起点是墙
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            if (maze[x][y] == 9) return 1; // 找到奶酪
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                        !visited[nx][ny] && (maze[nx][ny] == 1 || maze[nx][ny] == 9)) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return 0;
    }

    // 测试用例
    public static void main(String[] args) {
        // 示例用例
        int[][] maze1 = {
            {1, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 9, 0, 1, 1},
            {1, 1, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };
        System.out.println(canReachCheese(maze1)); // 1

        // 边界用例1：起点就是奶酪
        int[][] maze2 = {
            {9, 1},
            {0, 1}
        };
        System.out.println(canReachCheese(maze2)); // 1

        // 边界用例2：无路可走
        int[][] maze3 = {
            {1, 0},
            {0, 9}
        };
        System.out.println(canReachCheese(maze3)); // 0

        // 边界用例3：起点是墙
        int[][] maze4 = {
            {0, 1},
            {1, 9}
        };
        System.out.println(canReachCheese(maze4)); // 0

        // 边界用例4：只有一格，且是奶酪
        int[][] maze5 = {
            {9}
        };
        System.out.println(canReachCheese(maze5)); // 1

        // 边界用例5：只有一格，且不是奶酪
        int[][] maze6 = {
            {1}
        };
        System.out.println(canReachCheese(maze6)); // 0
    }
}
