package algo.hot100;

/**
 * https://leetcode.cn/problems/container-with-most-water/description/?envType=problem-list-v2&envId=2cktkvj
 */
public class P11_MostWater {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new P11_MostWater().maxArea(arr));
    }

    // 思路：以i位置开始，往右走，直到最后一个位置，求最大值
    // 暴力解法，超时
    public int maxArea(int[] height) {

        int ans = 0;
        int j;
        for (int i = 0; i < height.length; i++) {
            j = i;
            while (j < height.length) {
                int min = Math.min(height[i], height[j]);
                ans = Math.max(ans, min * (j - i));
                j++;
            }
        }
        return ans;
    }

    // 因为要面积最大，因此从两个指针圈起来的
    // area = min(height[i], height[j]) * (j - i)
    // 使用两个指针，值小的指针向内移动，这样就减小了搜索空间
    //因为面积取决于指针的距离与值小的值乘积，如果值大的值向内移动，
    // 距离一定减小，而求面积的另外一个乘数一定小于等于值小的值，
    // 因此面积一定减小，而我们要求最大的面积，
    // 因此值大的指针不动，而值小的指针向内移动遍历
    public int maxArea2(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max(max, height[left] * (right - left));
                left++;
            } else {
                max = Math.max(max, height[right] * (right - left));
                right--;
            }
        }
        return max;
    }

}
