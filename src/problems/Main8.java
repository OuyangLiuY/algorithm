package problems;

import java.util.*;

public class Main8 {
    /**
     * 给定一个长度为 N 的整数列表。请编写一个算法，将列表的前 K 个元素（list[0] 到 list[K-1]）
     * 按升序排序，剩余的元素（list[K] 到 list[N-1]）按降序排序。
     * 输入：
     * 第一行输入一个整数，表示列表的元素个数 N。
     * 第二行输入 N 个用空格分隔的整数，表示列表中的元素。
     * 最后一行输入一个整数 num，表示需要按升序排序的元素个数 K。
     * 输出：
     * 输出 N 个用空格分隔的整数，表示排序后的列表
     * @param arr
     * @param k
     */
    public static void partialSort(int[] arr, int k) {
        int n = arr.length;
        // 边界处理
        if (k < 0) k = 0;
        if (k > n) k = n;

        // 前K个升序
        Arrays.sort(arr, 0, k);
        // 剩余降序
        Arrays.sort(arr, k, n);
        for (int i = k, j = n - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        // 示例用例
        int[] arr1 = {5, 2, 9, 1, 7, 6};
        int k1 = 3;
        partialSort(arr1, k1);
        System.out.println(Arrays.toString(arr1)); // [2, 5, 9, 7, 6, 1]

        // 边界用例1：K=0（全部降序）
        int[] arr2 = {3, 1, 4, 2};
        int k2 = 0;
        partialSort(arr2, k2);
        System.out.println(Arrays.toString(arr2)); // [4, 3, 2, 1]

        // 边界用例2：K=N（全部升序）
        int[] arr3 = {3, 1, 4, 2};
        int k3 = 4;
        partialSort(arr3, k3);
        System.out.println(Arrays.toString(arr3)); // [1, 2, 3, 4]

        // 边界用例3：K>n（等同于全部升序）
        int[] arr4 = {5, 3, 2};
        int k4 = 10;
        partialSort(arr4, k4);
        System.out.println(Arrays.toString(arr4)); // [2, 3, 5]

        // 边界用例4：K<0（等同于全部降序）
        int[] arr5 = {1, 2, 3};
        int k5 = -2;
        partialSort(arr5, k5);
        System.out.println(Arrays.toString(arr5)); // [3, 2, 1]

        // 边界用例5：空数组
        int[] arr6 = {};
        int k6 = 2;
        partialSort(arr6, k6);
        System.out.println(Arrays.toString(arr6)); // []
    }
}
