package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * @author dal
 * O(n) time and O(1) space
 */
public class PalindromeLinkedList {

    /**
     *思路：
     * 1.借助快慢双指针找到list的中点
     * 2.反转右半部分
     * 3.设置2个指针，分别让2个指针向中间靠近，进行回文判断
     * 4.恢复现场（再次反转右半部分）
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        //1.借助快慢双指针找到list的中点
        ListNode midNode = getMidNode(head);
        //2.反转右半部分
        ListNode rightHead = reverseList(midNode.next);
        ListNode oldRightHead = rightHead;
        //3.设置2个指针，分别让2个指针向中间靠近，进行回文判断
        ListNode leftHead = head;
        boolean isPalindrome = true;
        while (rightHead != null){
            if (leftHead.val != rightHead.val) {
                isPalindrome = false;
                break;
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }

        //4.恢复现场（再次反转右半部分）
        reverseList(oldRightHead);

        return isPalindrome;
    }

    private ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }

        return newHead;
    }

    private ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
