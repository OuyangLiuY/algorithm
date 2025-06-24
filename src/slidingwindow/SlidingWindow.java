package slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return null;
        }
        int N = nums.length;
        int[] res = new int[N - k + 1];

        // 队列中存储的是下标，队首是当前窗口的最大值的下标
        Deque<Integer> qmax = new ArrayDeque<>();
        int idx = 0;
        for (int R = 0; R < N; R++) {
            // 维持队列最大情况,因为不知道这个值有多大，所以需要一个一个比较
            while (!qmax.isEmpty() && nums[qmax.peekLast()] <= nums[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            // 维持窗口左边界，如果队首的下标小于当前窗口的左边界，则将其移除
            // R - K 是当前窗口的左边界下标
            if (qmax.peekFirst() == R - k) {
                qmax.pollFirst();
            }
            if (R >= k - 1) {
                res[idx++] = nums[qmax.peekFirst()];
            }
        }
        return res;
    }
}
