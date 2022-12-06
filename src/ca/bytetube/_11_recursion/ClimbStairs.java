package ca.bytetube._11_recursion;

public class ClimbStairs {
    public int climbStairs(int n) {
        if (n <= 2) return n;

        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
