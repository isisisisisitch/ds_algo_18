package ca.bytetube._00_leetcode._02_stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 *
 * @author dal
 */
public class MinStack {
//    private Stack<Integer> dataStack;
//    private Stack<Integer> minStack;
//
//
//    public MinStack() {
//        dataStack = new Stack<>();
//        minStack = new Stack<>();
//    }
//
//    public void push(int val) {
//        dataStack.push(val);
//        if (minStack.isEmpty()) minStack.push(val);
//        else if (val <= minStack.peek()) minStack.push(val);
//    }
//
//    public void pop() {
//        int pop = 0;
//        if (!dataStack.isEmpty()) pop =  dataStack.pop();
//
//        if (pop == minStack.peek()) minStack.pop();
//    }
//
//    public int top() {
//        return dataStack.peek();
//    }
//
//    public int getMin() {
//        if (minStack.isEmpty()) throw  new RuntimeException("stack is empty !");
//        return minStack.peek();
//    }

    private static class Node {
        int val;
        Node next;
        int min;

        public Node(int val, Node next, int min) {
            this.val = val;
            this.next = next;
            this.min = min;
        }
    }

    Node head;

    public MinStack() {
        //dummy node
        head = new Node(0, null, Integer.MAX_VALUE);
    }

    public void push(int val) {
        head = new Node(val, head, Math.min(val, head.min));

    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}