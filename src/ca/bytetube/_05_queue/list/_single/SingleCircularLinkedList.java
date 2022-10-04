package ca.bytetube._05_queue.list._single;


import ca.bytetube._05_queue.list.AbstractList;

public class SingleCircularLinkedList<E> extends AbstractList<E> {
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

            Node<E> newFirst = new Node<>(element, first);
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            //错误代码！如果是空链表插入第一个节点时
//            Node<E> last = node(size - 1);
            last.next = newFirst;
            first = newFirst;

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
    public E remove(int index) {
        rangeCheck(index);
        //head
        Node<E> removed = first;
        if (index == 0) {
            if (size == 1) first = null;
            else {//{44,55,66}
                Node<E> last = node(size - 1);//66
                Node<E> newFirst = first.next;//55
                first = newFirst;
                last.next = newFirst;
            }

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
