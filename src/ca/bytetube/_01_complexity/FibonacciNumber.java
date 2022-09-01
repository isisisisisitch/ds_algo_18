package ca.bytetube._01_complexity;

/**
 * https://leetcode.com/problems/fibonacci-number/
 *
 * @author dal
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        Times.test("fib0", () -> {
            fib0(45);
        });

        Times.test("fib1", () -> {
            fib1(45);
        });
//        System.out.println(fib0(30));
//        System.out.println(fib1(30));
    }

    //0 <= n <= 30
    public static int fib0(int n) {
//        if (n < 0 || n > 30) {
//            throw new RuntimeException("illegal input!");
//        }
        if (n <= 1) return n;

        return fib0(n - 1) + fib0(n - 2);
    }

    public static int fib1(int n) {
//        if (n < 0 || n > 30) {
//            throw new RuntimeException("illegal input!");
//        }

        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;


        return fib1(n, arr);
    }

    public static int fib1(int n, int[] arr) {//arr[n]
        if (arr[n] == 0) {
            arr[n] = fib1(n - 1, arr) + fib1(n - 2, arr);
        }

        return arr[n];

    }

    public static int fib2(int n) {
        if (n < 0 || n > 30) {
            throw new RuntimeException("illegal input!");
        }
        if (n <= 1) return n;

        int first = 0;
        int second = 1;

        //loop
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;

        }

        return second;
    }


    public static int fib3(int n) {
        if (n <= 1) return n;
        return fib3(n, 0, 1);
    }

    public static int fib3(int n, int first, int second) {
        if (n <= 1) return second;
        return fib3(n - 1, second, first + second);//tail recursion
    }


}
