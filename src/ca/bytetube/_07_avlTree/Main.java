package ca.bytetube._07_avlTree;

import ca.bytetube._07_avlTree.printer.BinaryTrees;

public class Main {

    public static void main(String[] args) {
        test5();
    }


    public static void test5() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.add(11);
        tree.add(6);
        tree.add(15);
        tree.add(4);
        tree.add(8);
        tree.add(16);
        tree.add(9);


        BinaryTrees.println(tree);

        System.out.println("=====================================================");
        tree.remove(16);
        BinaryTrees.println(tree);
//        tree.remove(16);
//        System.out.println("=====================================================");
//        BinaryTrees.println(tree);
//        tree.remove(8);
//        System.out.println("=====================================================");
//        BinaryTrees.println(tree);
//        tree.remove(10);
//        System.out.println("=====================================================");
//        BinaryTrees.println(tree);
    }

}
