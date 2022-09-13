package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 * @author dal
 * dummy node
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null  && fast.next != null){//2.无环
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }


        return false;
    }
}
