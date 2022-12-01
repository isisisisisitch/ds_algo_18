package ca.bytetube._00_leetcode._02_stack;


import netscape.security.UserTarget;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate("1+1"));
    }

    public static int calculate(String s) {
        Stack<Integer> help = new Stack<>();
        int res = 0, number = 0, sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                number = 10*number + (c-'0');
            }else if(c=='+'){
                res += sign*number;
                sign = 1;
                number =0;
            }else  if(c =='-'){
                res += sign *number;
                sign = -1;
                number = 0;
            }else if(c == '('){
                help.push(res);
                help.push(sign);
                res = 0;
                sign = 1;
            }else if(c == ')'){
                res += sign * number;
                number = 0;
                sign = 1;
                res = res* help.pop() + help.pop();
            }


        }

        if(number!=0) res += sign*number;

        return res;
    }
}
