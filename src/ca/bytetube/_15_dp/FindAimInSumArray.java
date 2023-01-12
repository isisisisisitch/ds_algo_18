package ca.bytetube._15_dp;

public class FindAimInSumArray {
    public static void main(String[] args) {
        int[] arr = {7,3,5,2};
        System.out.println(findAimInSumArray(arr,16));
    }

    public static boolean findAimInSumArray(int[] arr, int aim) {

        boolean[][] dp = new boolean[arr.length + 1][aim + 1];


        return dp[0][0];
    }


    public static boolean findAimInSumArray1(int[] arr, int aim) {
        return findAimInSumArray(arr, 0, 0, aim);
    }

    private static boolean findAimInSumArray(int[] arr, int index, int sum, int aim) {
        if (sum == aim) return true;
        if (index == arr.length) return false;

        return findAimInSumArray(arr, index + 1, sum + arr[index], aim) ||
                findAimInSumArray(arr, index + 1, sum, aim);
    }
}
