package ca.bytetube._00_leetcode._00_array;

public class PrintChessBoard {
    public static void main(String[] args) {
        printChessBoard(7);
    }


    public static void printChessBoard(int input) {
        for (int i = 0; i < input; i++) {
            for (int j = 0; j < input; j++) {
                if (((i & 1) != 0 && (j & 1) != 0) || ((i & 1) == 0 &&(j & 1) == 0)) System.out.print("W ");

                else System.out.print("B ");

            }
            System.out.println();
        }

    }
}
