package heap;

import static utils.Utils.swap;

public class Heap {


    // 当一个值，对于这个数组来说是新增的值，那么只能先heapInsert 操作来维持大根堆。
    // 新进来的数据，现在停在了index位置，现在依次往上移动，
    // 移动到0位置，或者干不掉自己的父亲，就停。
    public static void heapInsert(int[] arr, int index) {
        // 当前位置，比父亲位置大，
        // if index = 0， 那么父节点也是自己，自己肯定不大于自己。所以while循环结束。
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 当一个在数组中值从最后一个位置，来到了0位置，那么进行下沉操作。
    // 下沉
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 拿到左右孩子节点中较大的一个
            int largest = ((left + 1) < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
            // 较大值和当前index位置比较，如何较大的值比index位置的的值大，则拿到index
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {  // 说明当前位置正确，不再需要下沉。
                break;
            }
            swap(arr, index, largest); // index和较大的值进行交换
            index = largest;
            left = index * 2 + 1; //继续获取左孩子的节点。
        }
    }


}
