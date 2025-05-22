package sort;

import utils.Utils;

import java.util.Arrays;

public class SelectSort {


    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIdx = arr[j] < arr[minIdx] ? j : minIdx;
            }
            Utils.swap(arr, i, minIdx);
        }
    }



    // for testing
    public static void main(String[] args) {
        int[] arr = Utils.generateRandomArray(10000, 99999);
        System.out.println("Unstored arr : " + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("Sorted arr : " + Arrays.toString(arr));
    }
}
