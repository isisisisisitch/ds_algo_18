package ca.bytetube._09_hash;


public class Main {
    public static void main(String[] args) {

        float f = 10.6f;
        // System.out.println(Float.floatToIntBits(f));
        String s = "jack";
        System.out.println(s.hashCode());//3254239
        System.out.println(hash_code(s));
    }


    public static int hash_code(String s) {
        int hashCode = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            hashCode = (hashCode << 5) - hashCode + ch;
        }

        return hashCode;

    }
}
