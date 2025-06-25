package problems;

import java.util.*;

public class PizzaOrderDisplay {
    public static List<Integer> firstMeatPizzaInWindow(int[] orders, int k) {
        List<Integer> result = new ArrayList<>();
        if (orders == null || orders.length == 0 || k == 0) return result;

        Deque<Integer> meatQueue = new ArrayDeque<>();
        int n = orders.length;

        // 初始化第一个窗口
        for (int i = 0; i < k && i < n; i++) {
            if (orders[i] < 0) meatQueue.offer(i);
        }
        if (n >= k) {
            result.add(meatQueue.isEmpty() ? 0 : orders[meatQueue.peek()]);
        }

        // 滑动窗口
        for (int i = k; i < n; i++) {
            // 移除窗口外的肉类订单
            while (!meatQueue.isEmpty() && meatQueue.peek() <= i - k) {
                meatQueue.poll();
            }
            // 新进窗口的订单
            if (orders[i] < 0) meatQueue.offer(i);
            result.add(meatQueue.isEmpty() ? 0 : orders[meatQueue.peek()]);
        }
        return result;
    }

    public static void main(String[] args) {
        // 示例用例
        int[] orders1 = {-11, -2, 19, 37, 64, -18};
        int k1 = 3;
        System.out.println(firstMeatPizzaInWindow(orders1, k1)); // [-11, -2, 0, -18]

        // 边界用例1：全是素食
        int[] orders2 = {1, 2, 3, 4, 5};
        int k2 = 2;
        System.out.println(firstMeatPizzaInWindow(orders2, k2)); // [0, 0, 0, 0]

        // 边界用例2：全是肉类
        int[] orders3 = {-1, -2, -3, -4};
        int k3 = 2;
        System.out.println(firstMeatPizzaInWindow(orders3, k3)); // [-1, -2, -3]

        // 边界用例3：K=1
        int[] orders4 = {-5, 6, -7, 8};
        int k4 = 1;
        System.out.println(firstMeatPizzaInWindow(orders4, k4)); // [-5, 0, -7, 0]

        // 边界用例4：K=0
        int[] orders5 = {1, -1, 2, -2};
        int k5 = 0;
        System.out.println(firstMeatPizzaInWindow(orders5, k5)); // []

        // 边界用例5：K大于订单数
        int[] orders6 = {-1, 2};
        int k6 = 5;
        System.out.println(firstMeatPizzaInWindow(orders6, k6)); // []
    }
}