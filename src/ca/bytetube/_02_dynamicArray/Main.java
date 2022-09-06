package ca.bytetube._02_dynamicArray;

public class Main {
    public static void main(String[] args) {
       ArrayList<Integer> list = new ArrayList<>();//10
        for (int i = 0; i < 5; i++) {
            list.add(10 + i);
        }
        list.add(null);
        //list.remove(4);
        int index = list.indexOf(null);
       // System.out.println(index);
        System.out.println(list.contains(null));
        System.out.println(list.set(1,100));
        //System.out.println(list.size());
//        list.add(5,500);
        //System.out.println(list.size());
       // System.out.println(list.get(3));
//        list.clear();
//        System.out.println(list.isEmpty());
       // System.out.println(list.size);
//        list.add(10);
//        list.add(11);
//        list.add(12);
//        list.add(13);
//        list.add(14);
//
//
//        ArrayList<String> list2 = new ArrayList<>();



    }
}
