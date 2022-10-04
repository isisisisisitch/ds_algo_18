package ca.bytetube._05_queue;

import ca.bytetube._05_queue.list._double.LinkedList;

public class Dequeue<E> {
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

    public void enQueueRear(E element) {
        list.add(element);
    }

    public E deQueueFront() {
        return list.remove(0);
    }

    public void enQueueFront(E element) {
        list.add(0,element);
    }

    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }
    //Get the head element of  the queue

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(list.size() - 1);
    }

}
