import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 10_000_000/2; i++) {
            sum++;
        }
        System.out.println("time = " + (System.currentTimeMillis() - start) +" ms");
        System.out.println(sum);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int[] res =  new int[m+n];
        int idx = 0;
        int l1 = 0,l2 = 0;
        while (l1 < m && l2 < n ){
            if(nums1[l1] <= nums2[l2]){
                res[idx++] = nums1[l1++];
            }else {
                res[idx++] = nums2[l2++];
            }
        }
        while (l1 < m){
            res[idx++] = nums1[l1++];
        }
        while (l2 < n){
            res[idx++] = nums2[l2++];
        }
        System.out.println(Arrays.toString(res));
    }

    public void add(int[] nums1, int m, int[] nums2, int n) {
        int[] res =   new int[m+n];
        int idx = 0;
        int l1 = 0,l2 = 0;
        while (l1 < m && l2 < n ){
            if(nums1[l1] <= nums2[l2]) res[idx++] = nums1[l1++];
            else res[idx++] = nums2[l2++];
        }
        while (l1 < m){
            res[idx++] = nums1[l1++];
        }
        while (l2 < n){
            res[idx++] = nums2[l2++];
        }
        System.out.println(Arrays.toString(res));
    }

}
