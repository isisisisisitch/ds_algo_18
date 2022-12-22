package ca.bytetube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LessMoney {
    public static void main(String[] args) {
        int[] arr = {20,10,30,40,50};
        System.out.println(lessMoney1(arr));
        System.out.println(lessMoney(arr));

    }


    public static int lessMoney(int[] arr){
        if (arr == null || arr.length == 0)  throw new RuntimeException("data error");
        //1.将数据小根堆化
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int data : arr)  queue.offer(data);
        //2.每次从堆中弹2次堆顶，相加的和再次放回到堆中，供下次求和使用，相加的最终的和即是最小代价
        int cost = 0;
        while (queue.size() > 1){
            int subCost = queue.poll() + queue.poll();
            cost += subCost;
            queue.offer(subCost);
        }
        return cost;
    }


    public static int lessMoney1(int[] arr){
        Arrays.sort(arr);//{10,20,30}

        int count = arr.length;
        int cost = 0;
        for (int i = count - 1; i > 0 ; i--) {
            int subCost = sum(arr, i);
            cost += subCost;
        }


        return cost;

    }

    private static int sum(int[] arr , int length){
        int res = 0;
        for (int i = length; i >= 0 ; i--) {
            res += arr[i];
        }

        return res;
    }
}
