package ca.bytetube._11_recursion;

public class TailCall {

    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(factorial(5));

    }


    public static int factorial(int n) {
        return factorial(n, 1);
    }

    public static int factorial(int n, int res) {
        if (n <= 1) return res;
        return factorial(n - 1, res * n);
    }

    public static int factorial0(int n) {
        if (n <= 1) return n;
      return  n * factorial0(n-1);
    }


    public static void test1() {
        int a = 10;
        int b = a + 20;
        test2();

        int c = a + b;
        System.out.println(c);
    }

    public static void test2() {
        int x1 = 100;
        int x2 = 200;
    }


}
