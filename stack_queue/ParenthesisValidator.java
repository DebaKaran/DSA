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

        // Map each closing bracket to its corresponding opening bracket
        Map<Character, Character> closingToOpening = new HashMap<>();
        closingToOpening.put(')', '(');
        closingToOpening.put(']', '[');
        closingToOpening.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If it's an opening bracket → push it
            if (isOpening(ch)) {
                stack.push(ch);
            } else {
                // If encountering a closing bracket with no opening match
                if (stack.isEmpty()) return false;

                char top = stack.pop();

                // Mismatch → invalid
                if (closingToOpening.get(ch) != top) return false;
            }
        }

        // Valid only if all openings are matched
        return stack.isEmpty();
    }

    private boolean isOpening(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }
}
