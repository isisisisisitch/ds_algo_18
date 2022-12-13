package ca.bytetube._12_backTracking;

/**
 * https://leetcode.com/problems/n-queens-ii/
 *
 * @author dal
 */
public class NQueens {
    public static void main(String[] args) {
        new NQueens().totalNQueens(4);
    }
    //int[][] matrix
    int[] cols;//index:row  val:col  cols[4] = 5;表示在棋盘中的第5行的第6列放了一个皇后
    int ways;

    public int totalNQueens(int n) {
        cols = new int[n];
        place(0);
        return ways;
    }

    /**
     * @param row 第row行开始摆放皇后
     */
    private void place(int row) {

        if (row == cols.length) {
         ways++;
         show();
         return;

        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {

                cols[row] = col;//表示在第row行第col列上放入一个queen

                place(row + 1);//回溯

            }

        }

    }

    /**
     * 剪枝列和对角线
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(int row, int col) {
        // i 表示已经走过的行 ，因为皇后是从第0行开始向下摆放，所以需要看之前所有行的皇后的摆放位置（画出黑色区域）
        for (int i = 0; i < row; i++) {
            //列
            if (cols[i] == col) return false;

            //对角线
            if (Math.abs(col - cols[i]) == row - i) return false;

        }

        return true;
    }

    private void show(){
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) System.out.print("Q ");
                else System.out.print("B ");

            }
            System.out.println();

        }

        System.out.println("========================================");
    }


}
