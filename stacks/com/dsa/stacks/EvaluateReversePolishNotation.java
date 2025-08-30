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
    switch (token) {
        case "+": return top1 + top2;
        case "-": return top1 - top2;
        case "*": return top1 * top2;
        default: return top1 / top2;
    }
}


    private boolean isOperand(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}
