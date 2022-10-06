package ca.bytetube._00_leetcode._02_stack;

import java.util.Deque;
import java.util.LinkedList;


/**
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * @author dal
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;
        int[] maxes = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()])  deque.pollLast();
            deque.addLast(i);

            int w = i - k + 1;

            if (w < 0) continue;
            //此时一定产生了一个合法窗口
            //队头元素过期
            if (w > deque.peekFirst())  deque.removeFirst();

            maxes[w] = nums[deque.peekFirst()];


        }

        return maxes;
    }
}
