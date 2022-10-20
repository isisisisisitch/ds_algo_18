package ca.bytetube._07_avlTree;

import ca.bytetube._07_avlTree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected Node<E> root;
    protected int size;

    public static abstract class Visitor<E> {
        boolean stop;

        public abstract boolean visit(E element);
    }


    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    public void preOrderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrderTraversalByRecursion(root, visitor);
    }

    public void preOrderTraversalByRecursion(Node<E> head, Visitor<E> visitor) {
        if (head == null || visitor.stop) return;
        visitor.stop = visitor.visit(head.element);
        preOrderTraversalByRecursion(head.left, visitor);
        preOrderTraversalByRecursion(head.right, visitor);

    }


    /**
     * 有右先压右，有左再压左
     */
    public void preOrderTraversal(Node<E> head) {
        if (head == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();

            System.out.print(pop.element + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }

    }

    public void inOrderTraversal() {
        inOrderTraversalByRecursion(root);
    }

    public void inOrderTraversalByRecursion(Node<E> head) {
        if (head == null) return;


        inOrderTraversalByRecursion(head.left);
        System.out.print(head.element + " ");
        inOrderTraversalByRecursion(head.right);

    }


    public void inOrderTraversal(Node<E> head) {
        if (head == null) return;
        Stack<Node<E>> stack = new Stack<>();

        if (head != null) {
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.element + " ");
                    head = head.right;
                }

            }

        }


    }

    public void postOrderTraversal() {
        postOrderTraversalByRecursion(root);
    }

    public void postOrderTraversalByRecursion(Node head) {
        if (head == null) return;


        postOrderTraversalByRecursion(head.left);

        postOrderTraversalByRecursion(head.right);

        System.out.print(head.element + " ");

    }


    public void postOrderTraversal(Node head) {
        if (head == null) return;
        Stack<Node<E>> stack = new Stack<>();
        Stack<Node<E>> helpStack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            helpStack.push(pop);
            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);

        }

        while (!helpStack.isEmpty()) System.out.print(helpStack.pop().element + " ");

    }

    public void levelOrderTraversal() {
        levelOrderTraversal(root);
    }

    public void levelOrderTraversal(Node<E> head) {
        if (head == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            System.out.print(poll.element + " ");
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }

    }


    // number of elements
    public int size() {
        return size;
    }

    // if it is empty or not
    public boolean isEmpty() {
        return size == 0;
    }

    // clear all elements
    public void clear() {
        root = null;
        size = 0;
    }


    public Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        //1.node.left != null
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) p = p.right;

            return p;
        }
        //2.node.left == null && node.parent != null
        while (node.parent != null && node == node.parent.left) node = node.parent;
        //3.node.left == null && node.parent == null
        return node.parent;

    }


    public Node<E> successor(Node<E> node) {
        if (node == null) return null;
        //1.node.right != null
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) p = p.left;

            return p;
        }
        //2.node.right == null && node.parent != null
        while (node.parent != null && node == node.parent.right) node = node.parent;
        //3.node.left == null && node.parent == null
        return node.parent;

    }

    public int height() {
        return height(root);
    }

    private int height1(Node<E> node) {
        if (node == null) return 0;

        return Math.max(height1(node.left), height1(node.right)) + 1;//top--->down
    }


    private int height(Node<E> node) {
        if (node == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        int height = 1;
        int levelSize = 1;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            levelSize--;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);

            if (levelSize == 0) {//意味着当前层遍历完，即将遍历下一层
                levelSize = queue.size();
                height++;

            }
        }

        return height;
    }

    public boolean isComplete() {
        return isComplete(root);
    }

    private boolean isComplete(Node<E> head) {
        if (head == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(head);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (isLeaf && !node.isLeafNode()) return false;
            //1. If node.left!=null && node.right!=null,
            // add node.left and node.left to the queue in sequence
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
            //2. If node.left==null && node.right!=null，return false
            else if (node.left == null && node.right != null) return false;

                //3.If node.left!=null && node.right==null
            else if (node.left != null && node.right == null) {
                queue.offer(node.left);
                isLeaf = true;
            }
            //4. node.left==null && node.right==null
            else isLeaf = true;

        }
        return true;
    }

//    public boolean isBalanced() {
//
//        return isBalanced(root);
//    }
//
//
//    public boolean isBalanced(Node<E> head) {
//
//        return false;
//    }



    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();

        }
        return myNode.element + "_p(" + parentString + ")";
    }


    protected static class Node<E> {
        protected E element;
        protected Node<E> left;
        protected Node<E> right;
        protected Node<E> parent;


        public Node(E val, Node parent) {
            this.element = val;
            this.parent = parent;
        }

        protected boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        protected boolean isLeafNode() {
            return this.left == null && this.right == null;
        }


        protected boolean isLeftChild() {
            return this == this.parent.left;
        }

        protected boolean isRightChild() {
            return this == this.parent.right;
        }

        @Override
        public String toString() {
            return "element=" + element + ", parent=" + parent;
        }
    }
}