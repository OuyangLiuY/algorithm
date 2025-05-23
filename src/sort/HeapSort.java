package sort;

import heap.Heap;
import utils.Utils;

import java.util.Arrays;

import static utils.Utils.swap;

public class HeapSort {


    // 时间复杂度为 N * log N
    public static void heapSort1(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        for (int i = 0; i < arr.length; i++) {
            Heap.heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            Heap.heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 时间复杂度为log（N）
    public static void heapSort2(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        for (int i = arr.length - 1; i >= 0; i--) {
            Heap.heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            Heap.heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int testTime = 100;
        int maxSize = 100000;
        int maxValue = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] a1 = Utils.generateRandomArray(maxSize, maxValue);
            int[] a2 = Utils.copyArray(a1);
            int[] a3 = Utils.copyArray(a1);

            Arrays.sort(a1);
            heapSort1(a2);
            heapSort2(a3);

            boolean e1 = !Utils.isEqual(a1, a2) || !Utils.isEqual(a1, a3);
            if (e1) {
                System.out.println("Oop,Fuck!");
                System.out.println(Arrays.toString(a1));
                System.out.println(Arrays.toString(a2));
                System.out.println(Arrays.toString(a3));
                break;
            }
        }
        System.out.println("Oh,Luck,Finish!");
    }
}
