package ca.bytetube._00_leetcode._02_stack;


import java.util.Stack;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> help = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if(!"+-*/".contains(s)) help.push(Integer.valueOf(s));
            else {
                Integer a = help.pop();
                Integer b = help.pop();
                Integer res = 0;

                switch (s){
                    case ("+"):
                        res = a+b;
                        break;
                    case ("-"):
                        res =b-a;
                        break;
                    case ("*"):
                        res = a*b;
                        break;
                    case ("/"):
                        if(a == 0) throw new RuntimeException();
                        else res = b/a;
                        break;
                }
                help.push(res);
            }
        }
        return help.pop();
    }
}
