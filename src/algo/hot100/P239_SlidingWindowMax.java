package algo.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 滑动窗口最大值
 * https://leetcode.cn/problems/sliding-window-maximum/?envType=problem-list-v2&envId=2cktkvj
 */
public class P239_SlidingWindowMax {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;

        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[index++] = max;
        }
        return result;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[r]) {
                deque.pollLast();
            }
            deque.offerLast(r);
            // 窗口左边界
            if (deque.peekFirst() == r - k) {
                deque.pollFirst();
            }
            if (r - k + 1 >= 0) {
                result[index++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
