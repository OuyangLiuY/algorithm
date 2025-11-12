package algo.hot100;

/**
 * 53. 最大子数组和
 */
public class P53_MaxSubArray {


    public int maxSubArray(int[] nums) {
        int curSum = 0;
        int maxSum = nums[0];
        for (int num : nums) {
            // 要么延续子数组，要么重新开始一个子数组
            curSum = Math.max(num, curSum + num);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    public int maxSubArray1(int[] nums) {
        int preSum = 0;
        int minPreSum = 0;
        int maxSum = nums[0];
        for (int num : nums) {
            preSum += num;
            // 当前前缀和减去最小前缀和，得到的就是以当前元素结尾的最大子数组和
            maxSum = Math.max(preSum - minPreSum, maxSum);
            // 更新最小前缀和
            minPreSum = Math.min(minPreSum, preSum);
        }
        return maxSum;
    }
}
