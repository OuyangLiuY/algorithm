package sort;

import utils.Utils;

import java.util.Arrays;

public class InsertionSort {

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if(arr[j] > arr[j+ 1])  // 从j开始依次交换位置，直到将这个值放到正确位置
                    Utils.swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int testTime = 1;
        int maxSize = 10000;
        int maxValue = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] a1 = Utils.generateRandomArray(maxSize, maxValue);
            int[] a2 = Utils.copyArray(a1);

            Arrays.sort(a1);
            insertSort(a2);

            boolean e1 = Utils.isEqual(a1, a2);
            if (!e1 ) {
                System.out.println("Oop,Fuck!");
                System.out.println(Arrays.toString(a1));
                System.out.println(Arrays.toString(a2));

                break;
            }
            System.out.println("Oh,Luck,Finish!");
        }
    }
}
