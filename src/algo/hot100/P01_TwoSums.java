package algo.hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class P01_TwoSums {
    public static void main(String[] args) {
        int[] num = {3, 2, 4};
        System.out.println(Arrays.toString(new P01_TwoSums().twoSum(num, 6)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> set = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = target - nums[i];
            if (set.containsKey(cur)) {
                return new int[]{set.get(cur),i};
            }
            set.put(nums[i], i);
        }
        return new int[]{};
    }
}
