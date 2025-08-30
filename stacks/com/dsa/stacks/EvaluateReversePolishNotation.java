import java.util.Stack;

//Leetcode Problem 150: Evaluate Reverse Polish Notation
//Time Complexity: O(n)

public class EvaluateReversePolishNotation {
     public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(String token: tokens) {
            if(isOperand(token)) {
                Integer top2 = stack.pop();
                Integer top1 = stack.pop();

                stack.push(getResult(top1, top2, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private int getResult(int top1, int top2, String token) {
        if(token.equals("+")) {
            return top1 + top2;
        }

        else if(token.equals("-")) {
            return top1 - top2;
        }

        else if(token.equals("*")) {
            return top1 * top2;
        }

        else  {
            return top1 / top2;
        }
    }

    private boolean isOperand(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}
