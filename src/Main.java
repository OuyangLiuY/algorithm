//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
       
       System.out.println(mehtod(new int[][]{{1, 2}, {3, 4}, {5, 6}}));
       int[][] arr1 = {
        {1, 2},
        {4, 10},
        {20, 21},
        {2, 23}
    };
       
       System.out.println(mehtod(arr1));
    }

    /**
     * arr[i][0] 表示第i天easy任务的薪资
     * arr[i][1] 表示第i天hard任务的薪资
     * 返回最大薪资
     */
    public static int mehtod(int[][] arr) {
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
}