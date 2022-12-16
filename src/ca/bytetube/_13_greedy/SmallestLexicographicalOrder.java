package ca.bytetube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class SmallestLexicographicalOrder {
    public static void main(String[] args) {
        String[] strs = new String[]{"b","ba"};
        String s = smallestLexicographicalOrder(strs);
        System.out.println(s);
    }

    public static String smallestLexicographicalOrder(String[] strs){
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        String str = "";
        for (int i = 0; i < strs.length; i++) {
            str += strs[i];
        }


        return str;
    }

}
