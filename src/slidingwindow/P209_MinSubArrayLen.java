package slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class P209_MinSubArrayLen {

    public static void main(String[] args) {

        System.out.println(new P209_MinSubArrayLen().minSubArrayLen(7,new int[]{2,3,1,2,4,3}));
    }

    /**
     * 思想：
     * 1.当num大于target的时候，说明需要收集答案，那么从队列中直接获取，
     * 2.从队列的头部弹出一个元素，计算sum，如何sum再次大于target那么说明还需要收集答案，否则继续向下一个位置遍历。
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            stack.offer(i);
            sum += nums[i];
            while (!stack.isEmpty() && sum >= target) {
                ans = Math.min(ans, stack.peekLast() - stack.peekFirst());
                sum -= nums[stack.peekFirst()];
                stack.pop();
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans + 1;
    }
}
