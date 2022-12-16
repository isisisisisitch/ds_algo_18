package ca.bytetube._13_greedy;

import java.util.Arrays;

public class Pirates {
    public static void main(String[] args) {

        System.out.println(loadAntiques(30,new int[]{3, 5, 4, 10, 7, 14, 2, 11}));

    }

    //Greedy strategy: Every time, give priority to the lightest antique
    public static int loadAntiques(int capacity, int[] weights){

        Arrays.sort(weights);

        int weight = 0;
        int count = 0;
        for (int i = 0; i < weights.length && weight < capacity; i++) {
          int newWeight = weights[i] + weight;
            if (newWeight <= capacity) {
                weight = newWeight;
                count++;
                System.out.println(weights[i]);
            }
        }

        return count;
    }
}
