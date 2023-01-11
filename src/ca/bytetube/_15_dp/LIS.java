package ca.bytetube._15_dp;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * @author dal
 */
public class LIS {

    /**
     * Traversal j ∈ [0, i)
     * if nums[i] > nums[ j]
     * nums[i] can be followed by nums[j] to form a longer increasing subsequence than dp(j), with length dp(j) + 1
     * dp(i) = max { dp(i), dp( j) + 1 }
     * if nums[i] ≤ nums[ j]
     * nums[i] cannot be followed by nums[j].
     * Initial value of the status
     * dp(0) = 1
     * each dp(i) default value is 1
     */
    public int lengthOfLIS(int[] nums) {
        //define state
        int[] dp = new int[nums.length];
        //init dp[0]
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //if nums[i] ≤ nums[ j] continue
                if (nums[i] <= nums[j]) continue;
                //if nums[i] > nums[ j], dp(i) = max { dp(i), dp( j) + 1 }
                dp[i] = Math.max(dp[i], dp[j] + 1);

            }

           max = Math.max(dp[i],max);
        }


        return max;


    }

}
