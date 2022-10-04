package ca.bytetube._00_leetcode._03_queue;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * @author dal
 */
public class MyQueue {
    Stack<Integer> inStack;

    Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (inStack.isEmpty() && outStack.isEmpty()) throw new RuntimeException("queue is empty!");
        else if (outStack.isEmpty()) while (!inStack.isEmpty()) outStack.push(inStack.pop());
        return outStack.pop();

    }

    public int peek() {
        if (inStack.isEmpty() && outStack.isEmpty()) throw new RuntimeException("queue is empty!");
        else if (outStack.isEmpty()) while (!inStack.isEmpty()) outStack.push(inStack.pop());
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
