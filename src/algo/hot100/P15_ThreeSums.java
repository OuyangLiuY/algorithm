package algo.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15_ThreeSums {

    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 1. 首先对数组进行排序，这样可以方便地使用双指针方法。
     * 2. 遍历数组，对于每个元素 nums[i]，使用双指针 left 和 right 分别指向 i+1 和数组的最后一个元素。
     * 3. 计算三数之和 sum = nums[i] + nums[left] + nums[right]。
     * 4. 如果 sum == 0，则找到一个三元组，将其添加到结果列表中，并移动 left 和 right 指针，同时跳过重复的元素。
     * 5. 如果 sum < 0，则移动 left 指针向右，增加 sum 的值。
     * 6. 如果 sum > 0，则移动 right 指针向左，减少 sum 的值。
     * @param nums
     * @return
     */

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) left++;
                else right--;
            }
        }
        return res;
    }
}
