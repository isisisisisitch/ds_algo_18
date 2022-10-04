package ca.bytetube._05_queue.list;


import ca.bytetube._05_queue.list._double.DoubleCircularLinkedList;
import ca.bytetube._05_queue.list._single.SingleCircularLinkedList;

public class Main {
    public static void main(String[] args) {
        test5();
//        test4();
//          test3();
//        test2();
//        test1();
//        LinkedList<Person> list = new LinkedList<>();
//        list.add(11);

//        list.add(1,66);

//       SingleLinkedList<Integer> list = new SingleLinkedList<>();
//        list.add(new Person("A", 10));
//        list.add(new Person("B", 11));
//        list.add(new Person("C", 12));
//        list.add(new Person("D", 13));
//        list.add(new Person("E", 14));
//        System.out.println(list);

        //list.set(1,66);
//       while (list.size != 0){
//           System.out.println(list.remove(0));
//       }


//        System.out.println(list);
//       list.add(1,44);
//       list.add(4,55);
//       list.add(0,66);
        //list.remove(0);
        // System.out.println(list.indexOf(333));

    }

    public static void test5(){
        DoubleCircularLinkedList<Integer> list = new DoubleCircularLinkedList<>();
        for (int i = 1; i <= 8 ; i++) {
            list.add(i);
        }

        //reset
        list.reset();

        while (list.size > 1){
            next(list,2);
            System.out.println(list.remove());
        }

    }

    public static void next(DoubleCircularLinkedList<Integer> list, int count){
        for (int i = 0; i < count; i++) {
            list.next();
        }
    }

    public static void test4() {
        DoubleCircularLinkedList<Integer> list = new DoubleCircularLinkedList<>();
        list.add(0, 44);
        list.add(1, 55);
        list.add(2, 66);
//        System.out.println(list.remove(3));
//        list.remove(0);
        while (list.size > 0){
            list.remove(0);
        }
        System.out.println(list);

    }



    public static void test3() {
        DoubleCircularLinkedList<Integer> list = new DoubleCircularLinkedList<>();
        list.add(0, 44);
        list.add(1, 55);
        list.add(2, 66);
//        list.remove(0);
//        while (list.size > 0){
//            list.remove(0);
//        }
//        System.out.println(list);

    }

    public static void test2() {
        SingleCircularLinkedList<Integer> list = new SingleCircularLinkedList<>();
        list.add(0, 44);
        list.add(1, 55);
        list.add(2, 66);
//        list.remove(0);
        while (list.size > 0){
            list.remove(0);
        }
        System.out.println(list);

    }


    public static void test1() {
        SingleCircularLinkedList<Integer> list = new SingleCircularLinkedList<>();
        list.add(0, 66);
        list.add(1, 44);
        list.add(2, 55);
        System.out.println(list);

    }


    public static void func(List list) {
        list.get(10);


    }
}
