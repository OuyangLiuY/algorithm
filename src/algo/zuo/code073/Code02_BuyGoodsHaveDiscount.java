package algo.zuo.code073;

import java.io.*;

public class Code02_BuyGoodsHaveDiscount {

    public static int MAX_N = 501;
    public static int MAX_X = 100001;
    public static int[] COST = new int[MAX_N];
    public static long[] VAL = new long[MAX_N];
    public static int N, IDX, X;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var in = new StreamTokenizer(br);
        var out = new PrintWriter(new OutputStreamWriter(System.out));
        long ans = 0;
        long happy = 0;
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int) in.nval;
            IDX = 1;
            in.nextToken();
            X = (int) in.nval;
            // pre: 原价，cur：现价
            for (int i = 1, pre, cur, well; i <= N; i++) {
                in.nextToken(); pre = (int) in.nval;
                in.nextToken(); cur = (int) in.nval;
                in.nextToken(); happy = (int) in.nval;
                well = pre - cur - cur; //
                if (well >= 0) {
                    X += well;
                    ans += happy;
                } else {
                    COST[IDX] = -well;
                    VAL[IDX++] = happy;
                }
            }
            ans += compute();
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }

    private static long compute() {
        long[] dp = new long[MAX_X];
        for (int i = 0; i <= IDX; i++) {
            for (int j = X; j >= COST[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - COST[i]] + VAL[i]);
            }
        }
        return dp[X];
    }
}
