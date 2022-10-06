package ca.bytetube._00_leetcode._00_array;


public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        //① Define state //② Set initial state (boundary)
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            //③ Determine the state transition equation
            //if(dp(i-1) <= 0 dp(i) = nums[i])
            //if(dp(i-1) >0 dp(i) = dp(i-1) + nums[i])
            if (dp <= 0) dp = nums[i];
            else dp = dp + nums[i];

            max = Math.max(dp, max);
        }

        return max;
    }


    public int maxSubArray3(int[] nums) {
        //① Define state
        int[] dp = new int[nums.length];
        //② Set initial state (boundary)
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            //③ Determine the state transition equation
            //if(dp(i-1) <= 0 dp(i) = nums[i])
            //if(dp(i-1) >0 dp(i) = dp(i-1) + nums[i])
            if (dp[i - 1] <= 0) dp[i] = nums[i];
            else dp[i] = dp[i - 1] + nums[i];

            max = Math.max(dp[i], max);
        }


        return max;
    }


    public int maxSubArray2(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    public int maxSubArray1(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += nums[i];
                }
                max = Math.max(sum, max);
            }
        }
        return max;
    }

}
