package ca.bytetube._05_queue;


public class Main {
    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        Queue<Integer> queue = new Queue<>();
        for (int i = 10; i < 20; i++) {
            queue.enQueue(i);
        }

        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }


    public static void test2(){
        Dequeue<Integer> queue = new Dequeue<>();
        for (int i = 10; i < 20; i++) {
            queue.enQueueFront(i);
        }

        while (!queue.isEmpty()){
            System.out.println(queue.deQueueRear());
        }
    }
}
