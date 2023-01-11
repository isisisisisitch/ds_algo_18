package ca.bytetube._15_dp;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * @author dal
 */
public class LCS {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 9, 10};
        int[] nums2 = {1, 4, 9, 10};
        System.out.println(LCS(nums1, nums2));
    }


    public int longestCommonSubsequence(String text1, String text2) {
        char[] chs1 = text1.toCharArray();
        char[] chs2 = text2.toCharArray();

        char[] rowNums = chs1, colNums = chs2;
        if (chs1.length < chs2.length) {
            colNums = chs1;
            rowNums = chs2;
        }

        int[] dp = new int[colNums.length + 1];
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] =leftTop + 1;
                } else {
                    // dp[j] top, dp[j-1] left
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colNums.length];

    }



    public static int LCS(int[] nums1, int[] nums2) {

        int[] rowNums = nums1, colNums = nums2;
        if (nums1.length < nums2.length) {
            colNums = nums1;
            rowNums = nums2;
        }

        int[] dp = new int[colNums.length + 1];
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colNums[j - 1]) {
                    dp[j] =leftTop + 1;
                } else {
                    // dp[j] top, dp[j-1] left
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colNums.length];
    }



    public static int LCS4(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    // dp[j] top, dp[j-1] left
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[nums2.length];
    }


    public static int LCS3(int[] nums1, int[] nums2) {
        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int row = i & 1;
            int prevRow = (i - 1) & 1;
            for (int j = 1; j <= nums2.length; j++) {
                //if nums1[i – 1] = nums2[ j – 1]，dp(i, j) = dp(i – 1, j – 1) + 1
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                } else {  //if nums1[i – 1] ≠ nums2[ j – 1]，
                    // dp(i, j) = max { dp(i – 1, j), dp(i, j – 1) }
                    dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);
                }
            }
        }
        return dp[nums1.length & 1][nums2.length];
    }

    public static int LCS2(int[] nums1, int[] nums2) {
        // //dp(nums1.length,nums2.length)
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                //if nums1[i – 1] = nums2[ j – 1]，dp(i, j) = dp(i – 1, j – 1) + 1
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {  //if nums1[i – 1] ≠ nums2[ j – 1]，
                    // dp(i, j) = max { dp(i – 1, j), dp(i, j – 1) }
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[nums1.length][nums2.length];
    }


    public static int LCS1(int[] nums1, int[] nums2) {
        //dp(nums1.length,nums2.length)
        return LCS1(nums1, nums1.length, nums2, nums2.length);
    }

    private static int LCS1(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) return LCS1(nums1, i - 1, nums2, j - 1) + 1;

        return Math.max(LCS1(nums1, i - 1, nums2, j), LCS1(nums1, i, nums2, j - 1));
    }
}
