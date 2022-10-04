package ca.bytetube._00_leetcode._02_stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/description/
 *
 * @author dal
 */
public class MyStack {

    private Queue<Integer> data;
    private Queue<Integer> help;

    public MyStack() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(int x) {
        data.offer(x);
    }

    public int pop() {
        while (data.size() > 1) help.offer(data.poll());
        Integer poll = data.poll();
        swap();
        return poll;
    }

    public int top() {
        while (data.size() > 1) help.offer(data.poll());
        Integer poll = data.poll();
        help.offer(poll);
        swap();
        return poll;
    }

    public boolean empty() {
        return data.isEmpty() && help.isEmpty();
    }


    public void swap() {
        Queue<Integer> temp = help;
        help = data;
        data = temp;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.top();
        stack.pop();
    }
}
