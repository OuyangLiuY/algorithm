package algo.zuo.code073;

import java.util.Arrays;

public class Knapsack {

    // dp[i][j] 前i个数，累加和 <= j, 并保证这个累加和尽量大
    private int near2(int[] stones, int t) {
        return f1(stones, t, stones.length);
    }

    private int f1(int[] stones, int t, int n) {
        if (n == 0 || t == 0) return 0;
        if (stones[n - 1] > t)
            return f1(stones, t, n - 1);
        else {
            // yao
            int ans1 = stones[n - 1] + f1(stones, t - stones[n - 1], n - 1);
            // buyao
            int ans2 = f1(stones, t, n - 1);
            return Math.max(ans1, ans2);
        }
    }

    private static int knapsackRecursive(int[] weight, int[] value, int capacity) {
        return f2(weight, value, capacity, weight.length);
    }

    private static int f2(int[] weight, int[] value, int capacity, int n) {
        if (n == 0 || capacity == 0) return 0;
        //物品太重无法选择
        if (weight[n - 1] > capacity) {
            return f2(weight, value, capacity, n - 1);
        } else {
            // 选择物品
            int ans = value[n - 1] + f2(weight, value, capacity - weight[n - 1], n - 1);
            // 不选择物品
            int ans2 = f2(weight, value, capacity, n - 1);
            return Math.max(ans, ans2);
        }
    }

    /**
     * dp实现，不考虑状态压缩
     *
     * @param weight
     * @param value
     * @param capacity
     * @return
     */
    private static int knapsackDP(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        // int[i][w] 前i个物品，容量为w的背包，能够获得的最大价值
        int[][] dp = new int[n + 1][capacity + 1];
        // 初始化：前0个物品，任何容量下的最大价值都是0
        for (int w = 0; w <= capacity; w++) {
            dp[0][w] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                int curWeight = weight[i - 1];
                int curValue = value[i - 1];
                if (curWeight > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],         // 不要当前物品
                            dp[i - 1][w - curWeight] + curValue   // 要当前物品
                    );
                }
            }
        }
        printDPTable(dp, weight, value);
        return dp[n][capacity];
    }

    public static int knapsackDPCompressed(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        int[] dp = new int[capacity + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int w = capacity; w >= 0; w--) {
                int curWeight = weights[i];
                int curValue = values[i];
                if (curWeight <= w) {
                    dp[w] = Math.max(
                            dp[w],                     // 不要当前物品
                            dp[w - curWeight] + curValue   // 要当前物品
                    );
                }
            }
        }
        return dp[capacity];
    }

    public static int knapsackDPCompressed1(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        int[] dp = new int[capacity + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int curWeight = weights[i];
            int curValue = values[i];
            for (int w = capacity; w >= curWeight; w--) {
                dp[w] = Math.max(
                        dp[w],                     // 不要当前物品
                        dp[w - curWeight] + curValue   // 要当前物品
                );
            }
        }
        return dp[capacity];
    }


    private static void printDPTable(int[][] dp, int[] weights, int[] values) {
        System.out.println("DP表:");
        System.out.print("i\\w\t");
        for (int w = 0; w < dp[0].length; w++) {
            System.out.print(w + "\t");
        }
        System.out.println();

        for (int i = 0; i < dp.length; i++) {
            if (i == 0) {
                System.out.print("0\t");
            } else {
                System.out.print(i + "(w:" + weights[i - 1] + ",v:" + values[i - 1] + ")\t");
            }

            for (int w = 0; w < dp[i].length; w++) {
                System.out.print(dp[i][w] + "\t");
            }
            System.out.println();
        }
    }



    // 测试方法
    public static void testKnapsack(String testName, int[] weights, int[] values, int capacity, int expected) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("测试用例: " + testName);
        System.out.println("物品重量: " + Arrays.toString(weights));
        System.out.println("物品价值: " + Arrays.toString(values));
        System.out.println("背包容量: " + capacity);
        System.out.println("期望结果: " + expected);

        long startTime, endTime;
        int result1, result2, result3;

        // 测试递归版本
        startTime = System.nanoTime();
        result1 = knapsackRecursive(weights, values, capacity);
        endTime = System.nanoTime();
        System.out.printf("递归版本: %d (耗时: %.3f ms)%n",
                result1, (endTime - startTime) / 1e6);

        // 测试DP版本
        startTime = System.nanoTime();
        result2 = knapsackDP(weights, values, capacity);
        endTime = System.nanoTime();
        System.out.printf("DP版本:   %d (耗时: %.3f ms)%n",
                result2, (endTime - startTime) / 1e6);

        // 测试状态压缩版本
        startTime = System.nanoTime();
        result3 = knapsackDPCompressed(weights, values, capacity);
        endTime = System.nanoTime();
        System.out.printf("压缩版本: %d (耗时: %.3f ms)%n",
                result3, (endTime - startTime) / 1e6);

        // 验证结果
        boolean allMatch = (result1 == expected && result2 == expected && result3 == expected);
        boolean consistent = (result1 == result2 && result2 == result3);

        System.out.println("结果验证: " + (allMatch ? "✓ 所有结果正确" : "✗ 结果错误"));
        System.out.println("一致性: " + (consistent ? "✓ 所有方法结果一致" : "✗ 方法间结果不一致"));
    }

    public static void main(String[] args) {
        // 测试用例1: 基础测试
        testKnapsack("基础测试",
                new int[]{2, 3, 4, 5},
                new int[]{3, 4, 5, 6},
                8, 10);

        // 测试用例2: 空背包
        testKnapsack("空背包测试",
                new int[]{2, 3, 4},
                new int[]{3, 4, 5},
                0, 0);

        // 测试用例3: 无物品
        testKnapsack("无物品测试",
                new int[]{},
                new int[]{},
                10, 0);

        // 测试用例4: 所有物品都超重
        testKnapsack("所有物品超重",
                new int[]{10, 15, 20},
                new int[]{100, 200, 300},
                5, 0);

        // 测试用例5: 刚好装满
        testKnapsack("刚好装满",
                new int[]{2, 3, 5},
                new int[]{4, 5, 8},
                5, 9);

        // 测试用例6: 大容量测试
        testKnapsack("大容量测试",
                new int[]{10, 20, 30},
                new int[]{60, 100, 120},
                50, 220);

        // 测试用例7: 经典测试用例
        testKnapsack("经典测试",
                new int[]{1, 3, 4, 5},
                new int[]{1, 4, 5, 7},
                7, 9);

        // 测试用例8: 性能测试（中等规模）
        int[] mediumWeights = {23, 31, 29, 44, 53, 38, 63, 85, 89, 82};
        int[] mediumValues = {92, 57, 49, 68, 60, 43, 67, 84, 87, 72};
        testKnapsack("中等规模测试", mediumWeights, mediumValues, 165, 309);

        // 测试用例9: 边界测试 - 单个物品
        testKnapsack("单个物品测试",
                new int[]{5},
                new int[]{10},
                5, 10);

        // 测试用例10: 边界测试 - 单个物品超重
        testKnapsack("单个物品超重",
                new int[]{10},
                new int[]{100},
                5, 0);

        // 大规模性能对比测试
        System.out.println("\n" + "=".repeat(50));
        System.out.println("性能对比测试（大规模）");
        int[] largeWeights = generateArray(20, 1, 50);
        int[] largeValues = generateArray(20, 10, 100);
        int largeCapacity = 100;

        System.out.println("物品数量: 20, 容量: 100");

        long startTime = System.nanoTime();
        int recursiveResult = knapsackRecursive(largeWeights, largeValues, largeCapacity);
        long recursiveTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int dpResult = knapsackDP(largeWeights, largeValues, largeCapacity);
        long dpTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int compressedResult = knapsackDPCompressed(largeWeights, largeValues, largeCapacity);
        long compressedTime = System.nanoTime() - startTime;

        System.out.printf("递归版本: %d (耗时: %.3f ms)%n", recursiveResult, recursiveTime / 1e6);
        System.out.printf("DP版本:   %d (耗时: %.3f ms)%n", dpResult, dpTime / 1e6);
        System.out.printf("压缩版本: %d (耗时: %.3f ms)%n", compressedResult, compressedTime / 1e6);
        System.out.println("一致性: " + (recursiveResult == dpResult && dpResult == compressedResult ? "✓" : "✗"));
    }

    // 生成随机数组用于测试
    private static int[] generateArray(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = min + (int)(Math.random() * (max - min + 1));
        }
        return arr;
    }

}
