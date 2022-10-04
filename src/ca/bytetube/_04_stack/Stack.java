package ca.bytetube._04_stack;

import ca.bytetube._04_stack.list._single.ArrayList;

public class Stack<E> {
    private ArrayList<E> list = new ArrayList<>();

    public int size() {
        return list.size();
    }


    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Is stack empty
    public void push(E element) {
        list.add(element);
    }


    public E pop() {
        return list.remove(list.size() - 1);
    }


    //Get the top element of the stack
    public E top() {
        return list.get(list.size() - 1);
    }


    // Clear all elements in stack
    public void clear() {
        list.clear();
    }



}
