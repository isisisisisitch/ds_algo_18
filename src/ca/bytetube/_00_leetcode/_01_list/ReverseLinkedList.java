package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * @author dal
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        //list = {5,4,3,2,1};
        //ListNode head = 5;
        //reverseList(4);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList(head.next);
        //4
        head.next.next = head;//4--->5
        head.next = null;//5--->null

        return newHead;
    }
}
