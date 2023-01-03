package ca.bytetube._15_dp;

public class MinPathSum {
    public static void main(String[] args) {
        int[][] matrix = {{3, 1, 0, 2}, {4, 3, 2, 1}, {5, 2, 1, 0}};
        System.out.println(minPathSum(matrix));
    }

    //计算从（0，0）出发，到达（i，j）点的最小路径和
    public static int minPathSum(int[][] grid) {
        //1.Define state
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        //2.Set initial state (boundary)
        dp[0][0] = grid[0][0];
        //first row
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        //first col
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        //common
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //3.Determine the state transition equation
                dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j]) + grid[i][j];
            }
        }

        return dp[row - 1][col - 1];


    }


    public static int minPathSum1(int[][] grid) {
        return minPathSum(grid, 0, 0);
    }

    //计算从（i，j）点出发，到达右下角点的最小路径和
    private static int minPathSum(int[][] grid, int i, int j) {
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        //1.last row
        if (i == grid.length - 1) return grid[i][j] + minPathSum(grid, i, j + 1);

        //2.last col
        if (j == grid[0].length - 1) return grid[i][j] + minPathSum(grid, i + 1, j);
        //3.common
        int right = minPathSum(grid, i, j + 1);
        int down = minPathSum(grid, i + 1, j);
        return grid[i][j] + Math.min(right, down);

    }
}
