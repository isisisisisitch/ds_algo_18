package ca.bytetube._00_leetcode._00_array;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * @author dal
 */
public class MergeSortedArray {

    /**
     *三指针的解法
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int cur = nums1.length - 1;
        while (i2 >= 0) {
            if (i1 >= 0 && nums1[i1] > nums2[i2]) nums1[cur--] = nums1[i1--];
            else nums1[cur--] = nums2[i2--];
        }

    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = nums1.length-1;
        while (i >-1  && j > -1) {
            if (nums1[i] < nums2[j]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }
        while (i > -1) {
            nums1[k--] = nums1[i--];
        }
        while (j > -1) {
            nums1[k--] = nums2[j--];
        }

    }

}
