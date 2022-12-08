package ca.bytetube._11_recursion;

public class PrintSubString {

    public static void main(String[] args) {
        printSubString("abc");
    }

    public static void printSubString(String s) {
        if (s == null) return;
        printSubString(s,0,new String());

    }

    public static void printSubString(String s, int index, String res) {
        if (index == s.length()) {
            System.out.println(res);
            return;
        }

        printSubString(s,index + 1,res);
        printSubString(s,index + 1,res + s.charAt(index));

    }

}
