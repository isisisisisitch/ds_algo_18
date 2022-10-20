package ca.bytetube._07_avlTree;


import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {
    protected Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void afterAdd(Node<E> node) {
    }

    public Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    // add elements
    public void add(E element) {
        //1.对添加元素判null
        if (element == null) throw new IllegalArgumentException("element can not be null");
        //2.1添加第一个节点
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        //2.2 添加的不是第一个节点（叶节点）
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) node = node.right;
            else if (cmp < 0) node = node.left;
            else {
                node.element = element;
                return;
            }
        }
        //3.创建新的节点，并且插入到指定的位置
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) parent.right = newNode;
        else parent.left = newNode;
        size++;
        afterAdd(newNode);

    }

    private int compare(E e1, E e2) {
        if (comparator != null) return comparator.compare(e1, e2);
        return ((Comparable<E>) e1).compareTo(e2);
    }

    // remove elements
    public void remove(E element) {
        remove(node(element));
    }


    private void remove(Node<E> node) {
        size--;
        //1.Delete node-node with degree 2
        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }
        //2.Delete node-node with degree 1
        Node<E> replacement = node.left != null ? node.left : node.right;
        //双亲相认
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) root = replacement;
            if (node == node.parent.left) node.parent.left = replacement;
            else node.parent.right = replacement;
        }

        //3.Delete node-leaf node
        else if (node.parent == null) root = null;

        else {
            if (node == node.parent.left) node.parent.left = null;
            else node.parent.right = null;
        }

    }


    // does it contain an element or not
    public boolean contains(E element) {
        return node(element) != null;
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            if (cmp > 0) node = node.right;
            else if (cmp < 0) node = node.left;
            else {
                node.element = element;
                return node;
            }

        }

        return null;
    }
}
