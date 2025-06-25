package problems;

import java.util.*;

public class Main7 {
    /**
     * 超市经理希望举办一个活动，向幸运顾客分发礼品篮。每个礼品篮包含一对产品。
     * 每个篮子里的产品对都不同，但所有篮子的总价值可能相同。超市有 N 种产品，每种产品有一个价格。
     * 礼品篮将奖励给那些选中一对产品且这对产品价格差等于给定整数 K 的顾客。
     * 请你编写一个算法，帮助经理计算有多少位幸运顾客会赢得礼品篮。
     * @param prices
     * @param K
     * @return
     */
    public static int countLuckyCustomers(int[] prices, int K) {
        Map<Integer, Integer> priceCount = new HashMap<>();
        int count = 0;
        for (int price : prices) {
            // 统计与当前价格差为K的对数
            count += priceCount.getOrDefault(price - K, 0);
            count += priceCount.getOrDefault(price + K, 0);
            // 记录当前价格出现次数
            priceCount.put(price, priceCount.getOrDefault(price, 0) + 1);
        }
        // 如果K==0，每对会被统计两次，需要除以2
        if (K == 0) count /= 2;
        return count;
    }

    public static void main(String[] args) {
        // 示例用例
        int[] prices1 = {10, 15, 23, 14, 2, 15};
        int K1 = 13;
        System.out.println(countLuckyCustomers(prices1, K1)); // 3

        // 边界用例1：K为0，重复价格
        int[] prices2 = {5, 5, 5, 5};
        int K2 = 0;
        System.out.println(countLuckyCustomers(prices2, K2)); // 6 (4个5，两两配对C(4,2)=6)

        // 边界用例2：没有符合条件的对
        int[] prices3 = {1, 2, 3, 4};
        int K3 = 10;
        System.out.println(countLuckyCustomers(prices3, K3)); // 0

        // 边界用例3：只有一个产品
        int[] prices4 = {100};
        int K4 = 0;
        System.out.println(countLuckyCustomers(prices4, K4)); // 0

        // 边界用例4：最大输入
        int[] prices5 = new int[100000];
        Arrays.fill(prices5, 1000000000);
        int K5 = 0;
        System.out.println(countLuckyCustomers(prices5, K5)); // 4999950000 (C(100000,2))
    }
}