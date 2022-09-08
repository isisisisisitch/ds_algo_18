package ca.bytetube._00_leetcode._01_list;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * @author dal
 */
public class DeleteNodeInALinkedList {

    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
