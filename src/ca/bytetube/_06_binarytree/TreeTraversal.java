package ca.bytetube._06_binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {

    public static void main(String[] args) {
        Node root = new Node(7, null, null);
        root.left = new Node(4, null, null);
        root.right = new Node(9, null, null);
        root.left.left = new Node(2, null, null);
        root.left.right = new Node(5, null, null);
        root.right.left = new Node(8, null, null);
        root.right.right = new Node(11, null, null);
//        preOrderTraversalByRecursion(root);
//        System.out.println();
//        preOrderTraversal(root);
//        postOrderTraversalByRecursion(root);
//        System.out.println();
//        postOrderTraversal(root);
        levelOrderTraversal(root);

    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void preOrderTraversalByRecursion(Node head) {
        if (head == null) return;
        System.out.print(head.val + " ");
        preOrderTraversalByRecursion(head.left);
        preOrderTraversalByRecursion(head.right);

    }


    /**
     * 有右先压右，有左再压左
     */
    public static void preOrderTraversal(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();

            System.out.print(pop.val + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }

    }

    public static void inOrderTraversalByRecursion(Node head) {
        if (head == null) return;


        inOrderTraversalByRecursion(head.left);
        System.out.print(head.val + " ");
        inOrderTraversalByRecursion(head.right);

    }


    public static void inOrderTraversal(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();

        if (head != null) {
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }

            }

        }


    }


    public static void postOrderTraversalByRecursion(Node head) {
        if (head == null) return;


        postOrderTraversalByRecursion(head.left);

        postOrderTraversalByRecursion(head.right);

        System.out.print(head.val + " ");

    }


    public static void postOrderTraversal(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        Stack<Node> helpStack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            helpStack.push(pop);
            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);

        }

        while (!helpStack.isEmpty()) System.out.print(helpStack.pop().val + " ");

    }



    public static void levelOrderTraversal(Node head) {
        if (head == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print(poll.val+ " ");
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }


    }




}
