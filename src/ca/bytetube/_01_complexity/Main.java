package ca.bytetube._01_complexity;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello");
    }


    public static void test1(int n){//11

        if (n > 10) {
            System.out.println("n > 10");
        }
       else if (n > 5) {
            System.out.println("n > 5");
        }
        else if (n > 2) {
            System.out.println("n > 2");
        }
        else System.out.println("n < 2");
        //1
        // 1 + 4 + 4 + 4
        for (int i = 0; i < 4; i++) {
            System.out.println("test1");
        }

        //14 ---> O(1)

    }

    public static void test2(int n){
        //1 + n + n + n = 3n + 1 ---> O(n)
        for (int i = 0; i < n; i++) {
            System.out.println("test2");
        }
    }

    public static void test3(int n){
        //1 + n + n(3n + 1) + n =  3n^2 + 3n + 1 ---> O(n^2)
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.println("test3");
            }
        }

    }


    public static void test4(int n){

        /*
        log2n
        n = 16
        i = 1
        i = 2
        i = 4
        i = 8

        i = 16

        */
        //1 + log2n + (3n+ 1)log2n + log2n = (3n + 3)log2n + 1 ---> O(nlogn)
        for (int i = 1; i < n; i = i * 2) {

            for (int j = 0; j < n; j++) {

                System.out.println("test4");
            }
        }
    }


    public static void test5(int n){

        while ((n = n / 2) != 0){
            System.out.println("test5");//log2n--->logn
        }
    }
}
