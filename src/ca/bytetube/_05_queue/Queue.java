package ca.bytetube._05_queue;

import ca.bytetube._05_queue.list._double.LinkedList;

public class Queue<E> {
    private LinkedList<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }
    //Get the head element of  the queue

    public E front() {
        return list.get(0);
    }
}
