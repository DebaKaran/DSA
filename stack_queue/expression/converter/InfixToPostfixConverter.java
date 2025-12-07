package stack_queue.expression.converter;

import java.util.Stack;

/**
 * GFG Problem Link: https://www.geeksforgeeks.org/infix-to-postfix-conversion/
 * Time Complexity: O(n) where n is length of expression
 * Space Complexity: O(n) for output and stack in worst case
 * 
 */
public class InfixToPostfixConverter {
    private InfixToPostfixConverter() { }

    public static String toPostfix(String expression) {
        return convert(expression);
    }

    /**
     * Core infix → postfix conversion using operator precedence rules.
     */
    private static String convert(String expr) {
        if (expr == null || expr.length() <= 1) return expr;

        Stack<Character> operatorStack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                 // Directly append operands to output
                output.append(ch);
            }
            else if (ch == '(') {
                // '(' always pushes — acts as precedence reset
                operatorStack.push(ch);
            }
            else if (ch == ')') {
                 // Pop all operators until matching '('
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    output.append(operatorStack.pop());
                }

                 // Discard the '(' if present
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // discard '('
                }
            }
            else {
                 // ch is an operator; pop higher/equal precedence operators first
                while (!operatorStack.isEmpty() && 
                ExpressionConverterUtil.shouldPop(operatorStack.peek(), ch)) {
                    output.append(operatorStack.pop());
                }
                operatorStack.push(ch);
            }
        }

        // Pop remaining operators
        while (!operatorStack.isEmpty()) {
            output.append(operatorStack.pop());
        }

        return output.toString();
    }
}
