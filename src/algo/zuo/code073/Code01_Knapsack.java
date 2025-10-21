package algo.zuo.code073;

import java.io.*;
public class Code01_Knapsack {


    public static int M = 101;
    public static int T, N;
    public static int[] COST = new int[M];
    public static int[] VAL = new int[M];


    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var in = new StreamTokenizer(br);
        var out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            T = (int) in.nval;
            in.nextToken();
            N = (int) in.nval;
            for (int i = 1; i <= N; i++) {
                in.nextToken();
                COST[i] = (int) in.nval;
                in.nextToken();
                VAL[i] = (int) in.nval;
            }
            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int compute1() {
        int[][] dp = new int[N + 1][T + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= T; j++) {
                dp[i][j] = dp[i - 1][j]; // 不要i
                if (j - COST[i] >= 0)
                    dp[i][j] = Math.max(dp[i][j], (dp[i - 1][j - COST[i]] + VAL[i])); //要
            }
        }
        return dp[N][T];
    }

    // 状态压缩
    private static int compute2() {
        int[] dp = new int[T + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = T; j >= COST[i]; j--) {
                dp[j] = Math.max(dp[j], (dp[j - COST[i]] + VAL[i]));
            }
        }
        return dp[T];
    }

}
