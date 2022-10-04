package ca.bytetube._04_stack.list._single;


import ca.bytetube._04_stack.list.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;//null


    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return node(index).value;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        //head
        if (index == 0) {
            first = new Node<>(element, first);
        } else { // current/tail
            Node<E> preNode = node(index - 1);
            //"="
            // 1.赋值：从右向左看  int i = 10;
            // 2.指向：从左向右看
            preNode.next = new Node<>(element, preNode.next);
        }

        size++;


    }

    @Override
    public E set(int index, E element) {
        Node<E> oldNode = node(index);
        E oldVal = oldNode.value;
        oldNode.value = element;

        return oldVal;
    }

    @Override
    public E remove(int index) {//index ---> n
        rangeCheck(index);
        //head
        Node<E> removed = first;
        if (index == 0) {
            first = first.next;
        } else { // current/tail
            Node<E> preNode = node(index - 1);
            removed = preNode.next;
            preNode.next = removed.next;
        }

        size--;

        return removed.value;
    }

    @Override
    public int indexOf(E element) {

        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {

                if (node.value == null) return i;
                node = node.next;

            }

        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.value)) return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size: ").append(size).append(",{");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            sb.append(node.value);
            node = node.next;
            if (i != size - 1) {
                sb.append(",");
            }

        }
        sb.append("}");
        return sb.toString();
    }

    private static class Node<E> {
        E value;
        Node<E> next;


        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }


    }
}
