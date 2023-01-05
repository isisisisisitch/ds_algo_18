package ca.bytetube._15_dp;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * @author dal
 */
public class MaximumSubarray {



    public int maxSubArray(int[] nums) {
        //① Define state  //② Set initial state
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            //1.if dp(i – 1) ≤ 0, dp(i) = nums[i]
            if (dp <= 0) dp = nums[i];
                //2.if dp(i – 1) > 0, dp(i) = dp(i – 1) + nums[i]
            else dp = dp + nums[i];// //③ Determine the state transition equation
            max = Math.max(dp,max);
        }

        return max;
    }

    public int maxSubArray1(int[] nums) {
        //① Define state
        int[] dp = new int[nums.length];
        //② Set initial state
        dp[0] = nums[0];

        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            //1.if dp(i – 1) ≤ 0, dp(i) = nums[i]
            if (dp[i - 1] <= 0) dp[i] = nums[i];

                //2.if dp(i – 1) > 0, dp(i) = dp(i – 1) + nums[i]
            else dp[i] = dp[i - 1] + nums[i];// //③ Determine the state transition equation
            max = Math.max(dp[i],max);
        }


        return max;



    }


}
