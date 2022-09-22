package ca.bytetube._00_leetcode._01_list;

import ca.bytetube._03_list.List;

public class GetFirstInsertionNode {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(10);
        head1.next = new ListNode(20);
        head1.next.next = new ListNode(30);
        head1.next.next.next = new ListNode(40);
        head1.next.next.next.next = new ListNode(50);
        head1.next.next.next.next.next = new ListNode(60);
        head1.next.next.next.next.next.next = new ListNode(70);

        ListNode head2 = new ListNode(10);
        head2.next = new ListNode(20);
        head2.next.next = new ListNode(30);
        head2.next.next.next = new ListNode(40);
        head2.next.next.next.next = head1.next.next.next.next;


        ListNode insertionNode = getFirstInsertionNode(head1, head2);
        System.out.println(insertionNode.val);

    }

    public static ListNode getFirstInsertionNode(ListNode head1, ListNode head2){

        ListNode cycleNode1 = getCycleInsertionNode(head1);
        ListNode cycleNode2 = getCycleInsertionNode(head2);
        if (cycleNode1 == null && cycleNode2 == null)   return getNoCycleNode(head1,head2);
        if (cycleNode1 != null && cycleNode2 != null)  return getTwoCycleInsertionNode(head1,cycleNode1,head2,cycleNode2);

        return null;
    }
    //todo test
    public static ListNode getTwoCycleInsertionNode(ListNode head1, ListNode cycleNode1, ListNode head2, ListNode cycleNode2) {

        ListNode cur1 = null;
        ListNode cur2 = null;


        if (cycleNode1 == cycleNode2) {//II
            int difference = 0;
            cur1 = head1;
            cur2 = head2;
            while (cur1.next != cycleNode1) {
                difference++;
                cur1 = cur1.next;
            }

            while (cur2.next != cycleNode2) {
                difference--;
                cur2 = cur2.next;
            }


            cur1 = difference > 0 ? head1 : head2;

            cur2 = cur1 == head1 ? head2 : head1;

            difference = Math.abs(difference);

            while (difference != 0) {
                difference--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;

        } else {//I/III
            cur1 = cycleNode1.next;
            while (cur1 != cycleNode1) {
                if (cur1 == cycleNode2) {//III

                    return cycleNode1;
                }
                cur1 = cur1.next;
            }
            //I
            return null;

        }

    }


    public static ListNode getNoCycleNode(ListNode head1, ListNode head2) {
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        int difference = 0;
        while (cur1.next != null) {
            difference++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            difference--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) return null;

        cur1 = difference > 0 ? head1 : head2;

        cur2 = cur1 == head1 ? head2 : head1;

        difference = Math.abs(difference);

        while (difference != 0) {
            difference--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    public static ListNode getCycleInsertionNode(ListNode head) {

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        //
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }


}
