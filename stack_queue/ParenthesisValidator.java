package stack_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Leetcode Problem Link: https://leetcode.com/problems/valid-parentheses/
 * Time Complexity: O(n)
 * Space Complexity: O(n) all characters are opening brackets → stack grows to n.
 */
class ParenthesisValidator {

    public boolean isValidParentheses(String s) {

        if (s == null || s.length() == 0) return true;

        int n = s.length();
        // Quick reject: odd length cannot be balanced
        if ((n & 1) == 1) return false;

        // Map each closing bracket to its corresponding opening bracket
        Map<Character, Character> closingToOpening = new HashMap<>();
        closingToOpening.put(')', '(');
        closingToOpening.put(']', '[');
        closingToOpening.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // If opening bracket, push to stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                continue;
            }

            // For closing bracket, ensure there's a matching opening bracket
            if (stack.isEmpty()) return false;

            char top = stack.pop();
            switch (ch) {
                case ')':
                    if (top != '(') return false;
                    break;
                case ']':
                    if (top != '[') return false;
                    break;
                case '}':
                    if (top != '{') return false;
                    break;
                default:
                    // If there are other characters (problem statement usually forbids),
                    // treat as invalid. Remove this if input can contain other chars.
                    return false;
            }
        }

        // All openings must be matched
        return stack.isEmpty();
    }

   
}
