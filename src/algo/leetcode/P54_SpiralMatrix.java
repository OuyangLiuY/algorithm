package algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 54. Spiral Matrix
 */
public class P54_SpiralMatrix {
    public static void main(String[] args) {
        P54_SpiralMatrix sm = new P54_SpiralMatrix();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2, 3, 4,},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[][] matrix4 = {
                {1},
                {5},
                {9}
        };
        int[][] matrix3 = {
                {1, 2, 3, 4}
        };
        sm.spiralOrder1(matrix);
        sm.spiralOrder(matrix2);
        sm.spiralOrder(matrix4);
        sm.spiralOrder(matrix3);

    }

    // 从第一行开始：从左到右，然后第一列从上到下，最后一行从左到右，最后一列从下到上，依次类推
    public List<Integer> spiralOrder1(int[][] matrix) {
        var res = new ArrayList<Integer>();
        int N = matrix.length;
        int M = matrix[0].length;
        int left = 0;
        int right = M - 1;
        int top = 0;
        int bottom = N - 1;
        while (left <= right && top <= bottom) {
            // right -> left
            for (int i = right; i >= left; i--) {
                res.add(matrix[top][i]);
            }
            top++;
            // top -> bottom
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][left]);
            }
            left++;

            // left -> right
            for (int i = left; i <= right; i++) {
                res.add(matrix[bottom][i]);
            }
            bottom--;

            // bottom -> top
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][right]);
            }
            right--;
        }
        System.out.println(res);
        return res;
    }

    // 从第一行开始：从左到右，然后最后一列从上到下，最后一行从右到左，第一列从下到上，依次类推
    public List<Integer> spiralOrder(int[][] matrix) {
        var res = new ArrayList<Integer>();
        int N = matrix.length;
        int M = matrix[0].length;
        int left = 0;
        int right = M - 1;
        int top = 0;
        int bottom = N - 1;
        while (left <= right && top <= bottom) {
            // left -> right
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            // 边界条件：只有一列的时候
            if(top <= bottom) {
                // top -> bottom
                for (int i = top; i <= bottom; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
                // right -> left
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }
            // 边界条件：只有一行的时候
            if (left <= right) {
                // bottom -> top
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        System.out.println(res);
        return res;
    }
}
