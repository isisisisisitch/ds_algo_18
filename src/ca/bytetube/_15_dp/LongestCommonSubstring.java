package ca.bytetube._15_dp;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("ABCBA", "BABCA"));

    }


    public static int longestCommonSubstring(String text1, String text2) {
        char[] chs1 = text1.toCharArray();
        char[] chs2 = text2.toCharArray();

        char[] rowNums = chs1, colNums = chs2;
        if (chs1.length < chs2.length) {
            colNums = chs1;
            rowNums = chs2;
        }
        int max = 0;
        int[] dp = new int[colNums.length + 1];
        for (int i = 1; i <= rowNums.length; i++) {
            for (int j = colNums.length; j >= 1; j--) {
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    max = Math.max(max, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return max;
    }

    public static int longestCommonSubstring2(String text1, String text2) {
        char[] chs1 = text1.toCharArray();
        char[] chs2 = text2.toCharArray();

        char[] rowNums = chs1, colNums = chs2;
        if (chs1.length < chs2.length) {
            colNums = chs1;
            rowNums = chs2;
        }
        int max = 0;
        int[] dp = new int[colNums.length + 1];
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] = leftTop + 1;
                    max = Math.max(max, dp[j]);
                } else {

                    dp[j] = 0;
                }
            }
        }
        return max;

    }


    public static int longestCommonSubstring1(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        if (chars1.length == 0) return 0;
        char[] chars2 = str2.toCharArray();
        if (chars2.length == 0) return 0;

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] != chars2[j - 1]) continue;

                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(dp[i][j], max);

            }
        }

        return max;
    }

}
