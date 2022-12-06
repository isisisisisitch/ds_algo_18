package ca.bytetube._11_recursion;

/**
 * https://leetcode.com/problems/fibonacci-number/description/
 *
 * @author dal
 */
public class Fib {

    public int fib0(int n) {
        if (n < 0 || n > 30) {
            throw new RuntimeException("illegal input!");
        }
        if (n <= 1) return n;

        return fib0(n - 1) + fib0(n - 2);
    }


    public int fib1(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal input!");
        if (n <= 1) return n;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;
        return fib1(n, arr);

    }

    public int fib1(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = fib1(n - 1, arr) + fib1(n - 2, arr);
        }

        return arr[n];
    }


    public int fib2(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal input!");
        if (n <= 1) return n;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }


        return arr[n];
    }

    public int fib3(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal input!");
        if (n <= 1) return n;
        int[] arr = new int[2];
        arr[0] = 1;//fib(1)
        arr[1] = 1;//fib(2)

        for (int i = 3; i <= n; i++) {
            arr[i % 2] = arr[(i - 1) % 2] + arr[(i - 2) % 2];
        }


        return arr[n % 2];
    }

    public int fib4(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal input!");
        if (n <= 1) return n;
        int[] arr = new int[2];
        arr[0] = 1;//fib(1)
        arr[1] = 1;//fib(2)

        for (int i = 3; i <= n; i++) {
            arr[i & 1] = arr[(i - 1) & 1] + arr[(i - 2) & 1];
        }


        return arr[n & 1];
    }

    public  int fib(int n) {
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


}
