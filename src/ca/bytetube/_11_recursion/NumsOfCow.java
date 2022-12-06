package ca.bytetube._11_recursion;

/**
 * female cow = 1 --->1 y---->child female
 *
 * child female---> 4 y ---> adult female cow
 *
 *
 * n
 */
public class NumsOfCow {

    public static void main(String[] args) {

    }


    public static int numsOfCow0(int n) {

        if (n <= 4) return n;

        return numsOfCow0(n - 1) + numsOfCow0(n - 4);

    }

    public static int numsOfCow1(int n) {
        int[] arr = new int[n + 1];
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        for (int i = 5; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 4];
        }

        return arr[n];
    }

    public int numsOfCow2(int n) {
        int[] arr = new int[n + 1];
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        return numsOfCow2(n, arr);

    }

    public int numsOfCow2(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = numsOfCow2(n - 1, arr) + numsOfCow2(n - 4, arr);
        }

        return arr[n];
    }

}
