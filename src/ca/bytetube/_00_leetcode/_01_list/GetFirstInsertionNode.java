package ca.bytetube._00_leetcode._01_list;

public class GetFirstInsertionNode {
    public static void main(String[] args) {

    }

    public static ListNode getCycleNode(ListNode head){

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast){
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        //
        fast = head;
        while (slow != fast){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }


}
