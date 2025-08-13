package algo.hot100;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 两个数组的中位数
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/?envType=problem-list-v2&envId=2cktkvj
 */
public class P04_Median {

    // 时间复杂度 O(m + n)，空间复杂度 O(m + n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = merge(nums1, nums1.length, nums2, nums2.length);
        int n = merged.length;
        if(n % 2 == 1){
            return merged[n / 2];
        } else {
            return (merged[n / 2 - 1] + merged[n / 2]) / 2.0;
        }
    }
    private int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m + n];
        int idx = 0;
        int l1 = 0, l2 = 0;
        while (l1 < m && l2 < n) {
            if (nums1[l1] <= nums2[l2]) {
                res[idx++] = nums1[l1++];
            } else {
                res[idx++] = nums2[l2++];
            }
        }
        while (l1 < m) {
            res[idx++] = nums1[l1++];
        }
        while (l2 < n) {
            res[idx++] = nums2[l2++];
        }
        return res;
    }

    // 时间复杂度 O(log(min(m, n)))，空间复杂度 O(1)
    // 在两个有序数组里找一个切口，让左边和右边一样大（或差一个），
    // 并且左边的最大值不比右边的最小值大。
    // 这样中位数就能直接从左右边界算出来，而不必真的去合并整个数组。
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 确保 nums1 是短的那个
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        int halfLen = (m + n + 1) / 2;

        while (left <= right) {
            int i = (left + right) / 2;
            int j = halfLen - i;

            int maxLeftA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRightA = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeftB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRightB = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftA, maxLeftB);
                } else {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                }
            } else if (maxLeftA > minRightB) {
                right = i - 1; // A 切分多了，往左缩
            } else {
                left = i + 1;  // A 切分少了，往右扩
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted");
    }
}
