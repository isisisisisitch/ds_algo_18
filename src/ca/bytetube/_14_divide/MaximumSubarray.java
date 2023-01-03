package ca.bytetube._14_divide;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * @author dal
 */
public class MaximumSubarray {


    public int maxSubArray(int[] nums) {

        return maxSubArray(nums, 0, nums.length);

    }


    private int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin ; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end ; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);

        }

        return Math.max(Math.max( maxSubArray(nums, begin, mid), maxSubArray(nums, mid, end)),leftMax + rightMax) ;

    }


    public int maxSubArray2(int[] nums) {

        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(max, sum);
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

                max = Math.max(max, sum);
            }

        }
        return max;
    }


}
