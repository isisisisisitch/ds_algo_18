package ca.bytetube._06_binarytree;


import ca.bytetube._06_binarytree.file.Files;
import ca.bytetube._06_binarytree.printer.BinaryTrees;

public class Main {

    public static void main(String[] args) {

//        test1();

//        test4();
        // test5();
        test6();

    }

    public static void test6() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {7, 4, 9, 2, 11, 1, 3, 10, 12};
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        BinaryTrees.println(bst);
        bst.preOrderTraversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                int cur = element * 10 + 5;
                System.out.print(cur + " ");
                return cur == 115 ? true : false;
            }
        });

    }


    public static void test5() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {7, 4, 9, 2, 11, 1, 3, 10, 12};
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        bst.levelOrderTraversal();
    }


    public static void test4() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {7, 4, 9, 2, 11, 1, 3};
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        String s = BinaryTrees.printString(bst);


        Files.writeToFile("/Users/dalluo/Desktop/test18.txt", s, true);

    }


    public static void test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {7, 4, 9, 2, 11, 1, 3, 10, 12};
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        BinaryTrees.println(bst);
        System.out.println("=========================================");

        bst.remove(7);

        BinaryTrees.println(bst);
    }


    public static void test2() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] arr = {7, 4, 9, 2, 11, 1, 3, 10, 12};
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        BinaryTrees.println(bst);


    }


    public static void test1() {
        BinarySearchTree<Person> bst = new BinarySearchTree<>();
        Person p1 = new Person(20, 1.75);
        Person p3 = new Person(40, 1.55);
        Person p4 = new Person(10, 1.65);
        Person p2 = new Person(30, 1.85);

        bst.add(p1);
        bst.add(p2);
        bst.add(p3);
        bst.add(p4);
        System.out.println(bst);


    }
}
