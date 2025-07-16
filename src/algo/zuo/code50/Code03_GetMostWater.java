package algo.zuo.code50;

/**
 * https://leetcode.cn/problems/trapping-rain-water/submissions/644474710/
 */
public class Code03_GetMostWater {


    /**
     * 思路：
     * 如何求：当来到i位置，求i位置能接的雨水量。
     * 那么需要知道i左侧最大值，和i右侧最大值，那个最小，那么就是需要最小的那个值。
     * 使用两个辅助数组，left和right获取每个位置上的最大。一个是从左往右，另外一个是从右往左
     * 来到i位置上，所能接受的雨水为
     * min(left[i-1],right[i+1]) - height[i]。有可能为负数，如果当前height[i]特别高，
     * 那么它不能接雨水，所以要和0pk，得到最总答案：
     * max( min(i,i+1) - height[i], 0)
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        int[] rightMax = new int[height.length];
        rightMax[height.length - 1] = height[height.length - 1];
        // 从左侧开始，每个位置上0～i最大值
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        // 从右侧开始，每个位置，i～n-1最大值
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int ans = 0;
        // 第一个位置 ，和最后一个位置雨水必为0
        for (int i = 1; i < height.length - 1; i++) {
            ans += Math.max(0, Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i]);
        }
        return ans;
    }

    /**
     * 双指针版本。
     * left指针，right指针。
     * leftMax，rightMax。
     * 脑海中想象过程：
     * 【5，2，1，6，7，0，9，4，5，7，9】
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int left = 1, right = height.length - 2;
        int ans = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                ans += Math.max(leftMax - height[left], 0);
                leftMax = Math.max(leftMax, height[left++]); // 更新leftMax并且left++
            } else {
                ans += Math.max(rightMax - height[right], 0);
                rightMax = Math.max(rightMax, height[right--]);
            }
        }
        return ans;
    }

}
