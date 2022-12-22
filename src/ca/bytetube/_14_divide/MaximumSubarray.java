package ca.bytetube._14_divide;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * @author dal
 */
public class MaximumSubarray {


    public int maxSubArray(int[] nums) {

        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += nums[i];
                }

                max = Math.max(max, sum);
            }

        }
        return max;
    }


}
