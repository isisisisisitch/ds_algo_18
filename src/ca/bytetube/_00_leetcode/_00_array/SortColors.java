package ca.bytetube._00_leetcode._00_array;

/**
 * https://leetcode.com/problems/sort-colors/
 *
 * @author dal
 */
public class SortColors {

    public static void main(String[] args) {
        int[] arr = new int[]{10,4,77,97,-5,23,45,6,2};
       new SortColors().divideArray(arr,13);
    }
    public void sortColors(int[] nums) {
        divideArray(nums,1);

    }

    public void divideArray(int[] nums, int pivot) {

        divideArray(nums, 0, nums.length - 1, pivot);
    }

    private void divideArray(int[] nums, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;

        while (l < more) {
            if (nums[l] < pivot) swap(nums, ++less, l++);


            else if (nums[l] > pivot) swap(nums, --more, l);

            else l++;
        }
    }


    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;

    }
}
