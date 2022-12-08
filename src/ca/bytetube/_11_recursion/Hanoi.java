package ca.bytetube._11_recursion;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Hanoi {

    public static void main(String[] args) {
        hanoi0(3, "A", "C", "B");
        System.out.println("=================================");
        System.out.println(hanoi(3, "A", "C", "B"));
    }


    public static LinkedHashSet<MoveInfo> hanoi(int n, String from, String to, String help){

        LinkedHashSet<MoveInfo> set = new LinkedHashSet<>();
        hanoi(n,from,to,help,set);
       return set;


    }

    public static void hanoi(int n, String from, String to, String help,LinkedHashSet<MoveInfo> moves){
        if (n == 1) {
            moves.add(new MoveInfo(n,from,to));
            return;
        }

        //1.将前n-1个盘从A放到B上
        hanoi(n-1,from,help,to,moves);
        //2.将第n个盘从A放到C上
        moves.add(new MoveInfo(n,from,to));
        //3.将前n-1个盘从B放到C上，借助A
        hanoi(n-1,help,to,from,moves);
    }




        private static class MoveInfo{
        int index;
        String from;
        String to;

        public MoveInfo(int index, String from, String to) {
            this.index = index;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "move " + index + " from " + from  + " to " + to +"\n";
        }
    }

    public static void hanoi0(int n, String from, String to, String help) {

        if (n == 1) System.out.println("move " + 1 + " from " + from + " to " + to);

        else {
            //1.将前n-1个盘从A放到B上
            hanoi0(n - 1, from, help, to);
            //2.将第n个盘从A放到C上
            System.out.println("move " + n + " from " + from + " to " + to);
            //3.将前n-1个盘从B放到C上，借助A
            hanoi0(n - 1, help, to, from);
        }


    }


}
