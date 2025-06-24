package problems;

public class Main2 {
    
    /**
     * 
     * @param arr
     * @return
     */
    public static int maxSalary(int[][] arr) {
        int n = arr.length;
        // dp0: 第i天什么都不做
        // dp1: 第i天做easy
        // dp2: 第i天做hard
        int dp0 = 0, dp1 = arr[0][0], dp2 = arr[0][1];
        for (int i = 1; i < n; i++) {
            int newDp0 = Math.max(dp0, Math.max(dp1, dp2));
            int newDp1 = newDp0 + arr[i][0];
            int newDp2 = dp0 + arr[i][1];
            dp0 = newDp0;
            dp1 = newDp1;
            dp2 = newDp2;
        }
        return Math.max(dp0, Math.max(dp1, dp2));
    }

    public static void main(String[] args) {
        // 示例用例
        int[][] arr1 = {
            {1, 2},
            {4, 10},
            {20, 21},
            {2, 23}
        };
        System.out.println(maxSalary(arr1)); // 33
    
        // 只有一天
        int[][] arr2 = {
            {3, 5}
        };
        System.out.println(maxSalary(arr2)); // 5
    
        // 两天，hard收益大
        int[][] arr3 = {
            {2, 10},
            {1, 20}
        };
        System.out.println(maxSalary(arr3)); // 20
    
        // 全部easy收益最大
        int[][] arr4 = {
            {5, 6},
            {5, 6},
            {5, 6}
        };
        System.out.println(maxSalary(arr4)); // 15
    
        // hard收益大但不能连续选
        int[][] arr5 = {
            {1, 100},
            {1, 100},
            {1, 100}
        };
        System.out.println(maxSalary(arr5)); // 201
    }
}
