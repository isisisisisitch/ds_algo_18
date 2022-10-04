package ca.bytetube._00_leetcode._02_stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 *
 * @author dal
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //1.When you meet the left character, push the left character into the  stack
            if (ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else {//2. When you meet the right character
                //2.1 If the stack is empty, the brackets are invalid
                if (stack.isEmpty()) return false;
                    //2.2 If the stack is not empty, pop the top character of the stack to match the right character
                else {
                    Character leftB = stack.pop();
                    if (leftB == '(' && ch != ')') return false;
                    if (leftB == '[' && ch != ']') return false;
                    if (leftB == '{' && ch != '}') return false;
                }

            }
        }
        //3. After all characters are scanned
        //The stack is empty, indicating that the brackets are valid
        //he stack is not empty, indicating that the brackets are invalid
        return stack.isEmpty();


//        while (s.contains("()") || s.contains("[]") || s.contains("{}")){
//            s.replace("()","");
//            s.replace("[]","");
//            s.replace("{}","");
//        }
//
//        return s.isEmpty();
    }


}
