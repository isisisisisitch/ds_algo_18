package ca.bytetube._05_queue.list._double;


import ca.bytetube._05_queue.list.AbstractList;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;//null

    private Node<E> last;//null


    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;


        public Node(E element, Node<E> prev, Node<E> next) {
            this.value = element;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        last = null;
        first = null;
        size = 0;

    }


    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {//index在前半部分
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {//index在后半部分
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }

    }

    @Override
    public E get(int index) {
        return node(index).value;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {//tail
            Node<E> oldLast = last;//null
            Node<E> newLast = new Node<>(element, oldLast, null);
            last = newLast;
            //空链表
            if (size == 0) first = newLast;
            else oldLast.next = newLast;

        } else {//普通位置/ head
            Node<E> next = node(index);//index = 0
            Node<E> prev = next.prev;//null

            Node<E> newNode = new Node<>(element, prev, next);
            next.prev = newNode;
            if (index == 0) first = newNode;

            else prev.next = newNode;

        }

        size++;

    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        Node<E> node = node(index);
        E oldVal = node.value;

        node.value = element;

        return oldVal;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        //current/tail/head
        Node<E> node = node(index);
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        if (index == 0) first = nextNode;

        else prevNode.next = nextNode;

        if (index == size - 1) last = prevNode;
        else nextNode.prev = prevNode;
        size--;
        return node.value;
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

}
