package ca.bytetube._00_leetcode._00_array;

public class MatrixPrint {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        matrixPrint(matrix);
    }

    public static void matrixPrint(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        //1.设置a，b指针
        int aR = 0;
        int aC = 0;
        int bR = 0;
        int bC = 0;
        boolean fromUp = false;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;

        while (aR != endR + 1) {
            printLevel(matrix, aR, aC, bR, bC, fromUp);
            aR = aC == endC ? aR + 1 : aR;
            aC = aC == endC ? aC : aC + 1;
            bC = bR == endR ? bC + 1 : bC;
            bR = bR == endR ? bR : bR + 1;
            fromUp = !fromUp;
        }

    }

    private static void printLevel(int[][] matrix, int tR, int tC, int dR, int dC, boolean fromUp) {
        //从上向下打
        if (fromUp) {
            while (tR != dR + 1) System.out.print(matrix[tR++][tC--] + " ");
        } else {//从下向上打
            while (dR != tR - 1) System.out.print(matrix[dR--][dC++] + " ");
        }
    }

}
