package ca.bytetube._08_heap;

public interface Heap<E> {

    int size(); // the number of elements

    boolean isEmpty(); // is it empty

    void clear(); // clear elements

    void add(E element); // add elements

    E get(); // get the top element of the heap

    E remove(); // delete the top element of the heap

    E replace(E element); // insert a new element while deleting the top element of the heap
}
