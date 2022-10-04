package ca.bytetube._05_queue.list._single;


import ca.bytetube._05_queue.list.AbstractList;

/**
 * @author dal
 */
public class ArrayList<E> extends AbstractList<E> {

    private E[] elements;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;


    public ArrayList() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }


    public ArrayList(int capacity) {//capacity修饰的是容器的大小
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements = (E[]) new Object[capacity];
    }


    // Clear all the elements
    public void clear() {
        size = 0;
    }

    // Returns the element corresponding to the index position
    public E get(int index) {
        //size - 1
        rangeCheck(index);

        return elements[index];//NullPointerException
    }


    // Add elements to the index position
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);

        //head,current
        for (int i = size; i > index; i--) elements[i] = elements[i - 1];

        //head,current/ tail
        elements[index] = element;

        size++;

    }


    // Set the element at the index position
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }


    // Delete elements to the index position
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        return oldElement;
    }


    // Return the index of the element
    public int indexOf(E element) {//element = NULL
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;

            }

        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;

            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        //todo
        return "todo";
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        //扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //值的迁移
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;

    }


}
