package ca.bytetube._02_dynamicArray;

/**
 * @author dal
 */
public class ArrayList<E> {
    private int size;//有效元素的个数
    private E[] elements;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }


    public ArrayList(int capacity) {//capacity修饰的是容器的大小
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements = (E[]) new Object[capacity];
    }

    // Number of elements
    public int size() {
        return size;
    }


    // Is it empty
    public boolean isEmpty() {
        return size == 0;
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


    // Add elements to the end
    public void add(E element) {
        add(size, element);
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


    //Contains a certain element
    public boolean contains(E element) {

        return indexOf(element) != ELEMENT_NOT_FOUND;
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

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index:" + index + "," + "size:" + size);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("index:" + index + "," + "size:" + size);

    }

}
