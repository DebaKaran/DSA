package stack_queue;

import java.util.Stack;

/**
 * LeetCode 402 — Remove K Digits
 *
 * Greedy monotonic stack approach:
 * Remove digits from left to right whenever a smaller digit appears
 * after a larger one. This ensures the smallest possible resulting number.
 */
public final class RemoveKDigits {

    private RemoveKDigits() { /* utility class */ }

    /** Public API */
    public String removeKdigits(String num, int k) {
        return removeDigits(num, k);
    }

    /**
     * Build the smallest number possible after removing k digits.
     *
     * Time:  O(n)  — each digit is pushed/popped at most once
     * Space: O(n)  — stack + result buffer
     */
    private String removeDigits(String num, int k) {
        if (num == null || num.length() == 0 || k >= num.length()) {
            return "0";
        }

        int removals = k;
        Stack<Integer> stack = new Stack<>();

        // Greedy: keep digits in increasing order; remove larger ones when possible
        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';

            // Remove previous larger digits while we still have quota
            while (!stack.isEmpty() && removals > 0 && digit < stack.peek()) {
                stack.pop();
                removals--;
            }

            stack.push(digit);
        }

        // If removals left after scanning: remove from the end (largest significance)
        while (removals > 0 && !stack.isEmpty()) {
            stack.pop();
            removals--;
        }

        // Build the number in correct order
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) ('0' + stack.pop())); // popped in reverse order
        }
        sb.reverse();

        // Strip leading zeros
        int idx = 0;
        while (idx < sb.length() && sb.charAt(idx) == '0') idx++;
        String result = sb.substring(idx);

        return result.isEmpty() ? "0" : result;
    }
}
