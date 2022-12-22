package ca.bytetube._14_divide;

public class GetMaxFromArray {

    public static int getMaxFromArray(int[] arr) {

        if (arr == null || arr.length == 0) throw new RuntimeException("error data");
        if (arr.length == 1) return arr[0];
        return getMaxFromArray(arr, 0, arr.length - 1);
    }

    private static int getMaxFromArray(int[] arr, int left, int right) {
        if (left == right) return arr[left];
        int mid = (left + right) >> 1;
        int leftMax = getMaxFromArray(arr, left, mid);

        int rightMax = getMaxFromArray(arr, mid + 1, right);

        return Math.max(leftMax, rightMax);

    }
}
