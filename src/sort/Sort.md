# 排序算法

## 选择排序

选择排序思想：对于数组1～N，使用双重遍历，找到最小的一个值然后交换位置，

选择排序流程：1～N，从0开始，找到最小值，然后放到 0 位置上，然后再依次从 1 位置开始直到N-1位置。

时间复杂度： O(n^2)

代码：

```java
 public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIdx = arr[j] < arr[minIdx] ? j : minIdx;
            }
            swap(arr, i, minIdx);
        }
    }
```

## 冒泡排序：

思想：通过重复地遍历待排序的列表，比较相邻的元素并交换它们的位置来实现排序。该算法的名称来源于较小或者较大的元素会像"气泡"一样逐渐"浮"到列表的顶端。

流程：相邻两个数据的进行比较，将较大或者较小的一个进行交换，依次从0开始，0～1，1～2，.... N-2 ~ N-1。

0\~i范围上，相邻位置较大的数滚下去，最大值最终来到i位置，然后0\~i-1范围上继续。

![](../../images/bubbleSort.gif)

时间复杂度：

最好：O(n)  数组数据完全有序

最坏：O(n^2)  数组数据是逆序

平均：O(n^2)

```java
 public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int end = arr.length - 1; end > 0; end--) {
            for (int j = 0; j < end ; j++) {
                if (arr[j] > arr[j + 1]) {
                    Utils.swap(arr, j, j + 1);
                }
            }
        }
    }
```

## 插入排序：

结论： 0\~i范围上已经有序，新来的数从右到左滑到不再小的位置插入，然后继续

![](../../images/insertionSort.gif)  



* **最坏情况**：O(n²)，当列表是逆序时，每次插入都需要移动所有已排序元素。
* **最好情况**：O(n)，当列表已经有序时，只需遍历一次列表。
* **平均情况**：O(n²)。

```java
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
```



### 适用场景

* 数据量较小或基本有序的场景。
* 需要稳定排序算法的场景。
* 作为更复杂排序算法（如快速排序、归并排序）的辅助算法。
