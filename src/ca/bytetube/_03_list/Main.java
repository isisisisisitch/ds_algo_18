package ca.bytetube._03_list;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
//        list.add(11);

//        list.add(1,66);

//       SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
       while (list.size != 0){
           System.out.println(list.remove(0));
       }


//        System.out.println(list);
//       list.add(1,44);
//       list.add(4,55);
//       list.add(0,66);
        //list.remove(0);
        // System.out.println(list.indexOf(333));

    }


    public static void func(List list) {
        list.get(10);


    }
}
