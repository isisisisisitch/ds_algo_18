package ca.bytetube._07_avlTree;

import ca.bytetube._07_avlTree.printer.BinaryTrees;

public class Main {

    public static void main(String[] args) {
        test5();
    }


    public static void test5() {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] arr = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < arr.length; i++) {
            tree.add(arr[i]);
        }
        BinaryTrees.println(tree);
    }

}
