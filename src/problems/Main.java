package problems;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    /**
     * 最小电缆长度
     * @param args
     */
    public static void main(String[] args) {
        int[] systemState = {1, 0, 0, 1, 0};
        int[] dist = {1, 2, 3, 4, 5};
        System.out.println(minCableLength(systemState, dist)); // 输出最小电缆长度
    }
    public static int minCableLength(int[] systemState, int[] dist) {
        int n = systemState.length;
        List<Integer> onIdx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (systemState[i] == 1) {
                onIdx.add(i);
            }
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (systemState[i] == 0) {
                // 二分查找最近的ON系统
                int l = 0, r = onIdx.size() - 1;
                int minDist = Integer.MAX_VALUE;
                while (l <= r) {
                    int m = (l + r) / 2;
                    int idx = onIdx.get(m);
                    int d = Math.abs(dist[i] - dist[idx]);
                    minDist = Math.min(minDist, d);
                    if (dist[idx] < dist[i]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
                total += minDist;
            }
        }
        return total;
    }
}
