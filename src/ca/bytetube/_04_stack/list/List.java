package ca.bytetube._04_stack.list;

public interface List<E> {

    public static final int ELEMENT_NOT_FOUND = -1;

    // Number of elements
    public int size();

    // Is it empty
    public boolean isEmpty();


    // Clear all the elements
    public void clear();

    // Returns the element corresponding to the index position
    public abstract E get(int index);

    // Add elements to the end
    public void add(E element);

    // Add elements to the index position
    public void add(int index, E element);


    // Set the element at the index position
    public E set(int index, E element);


    // Delete elements to the index position
    public E remove(int index);

    //Contains a certain element
    public boolean contains(E element);

    public int indexOf(E element);



}
