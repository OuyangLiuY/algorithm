package problems;


public class Main6 {
    public static String removeVowels(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!isVowel(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    public static void main(String[] args) {
        // 示例用例
        System.out.println(removeVowels("haveaniceday")); // hvncdy

        // 边界用例1：全是元音
        System.out.println(removeVowels("aeiouAEIOU")); // (空字符串)

        // 边界用例2：没有元音
        System.out.println(removeVowels("bcdfgBCDFG")); // bcdfgBCDFG

        // 边界用例3：空字符串
        System.out.println(removeVowels("")); // (空字符串)

        // 边界用例4：单个元音
        System.out.println(removeVowels("a")); // (空字符串)

        // 边界用例5：单个非元音
        System.out.println(removeVowels("z")); // z

        // 边界用例6：混合大小写
        System.out.println(removeVowels("AbEcIdOfU")); // bcd f
    }
}
