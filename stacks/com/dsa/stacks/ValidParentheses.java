import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/valid-parentheses/description/
//20. Valid Parentheses

public class ValidParentheses {

    //Time Complexity

// You iterate once over the string of length n.

// Each character is pushed or popped at most once from the stack.

// Therefore, O(n) time complexity.

// Space Complexity

// Worst case: all characters are opening brackets, so the stack size can grow to O(n).

// Therefore, O(n) space complexity.
public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()) {
            if(isOpeningBracket(ch)) {
                stack.push(ch);
            } else {
                if(stack.isEmpty()) return false;

                boolean isCorrectOrder = isClosingBracketCorrectOrder(stack.pop(), ch);

                if(!isCorrectOrder) return isCorrectOrder;
            }
        }
        return stack.isEmpty();
    }

    private boolean isClosingBracketCorrectOrder(char openingBracket, char closingBracket) {
        switch(openingBracket) {
            case '(': return closingBracket == ')';
            case '{': return closingBracket == '}';
            case '[': return closingBracket == ']';
            default: return false;
        }
    }

    private boolean isOpeningBracket(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

     public boolean isValid1(String s) {
        Map<Character, Character> bracketMap = Map.of(
            ')', '(', 
            '}', '{', 
            ']', '['
        );
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char ch : s.toCharArray()) {
            if (bracketMap.containsValue(ch)) {
                stack.push(ch);
            } else if (bracketMap.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != bracketMap.get(ch)) {
                    return false;
                }
            } else {
                return false; // Optional safeguard
            }
        }
        
        return stack.isEmpty();
    }
}
