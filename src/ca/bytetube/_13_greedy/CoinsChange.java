package ca.bytetube._13_greedy;

import java.util.Arrays;

public class CoinsChange {
    public static void main(String[] args) {
        System.out.println(coinsChange(new int[]{25,20,5,1},41));
    }

    /**
     * money = 39
     * faces ={1,5,10,25}
     * Greed strategy: give priority to the coin with the largest denomination every time
     * 1.face = 25, money = 14
     * 2.face = 10, money = 4
     * 3.face = 5, money = 4
     * 4.face = 1, money = 0
     */
    public static int coinsChange(int[] faces, int money) {
        Arrays.sort(faces);
        int coins = 0;
        for (int i = faces.length - 1; i >= 0; i--) {
            if (money < faces[i]) continue;
            money -= faces[i];
            coins++;
            i++;
        }

        return coins;

    }

}
