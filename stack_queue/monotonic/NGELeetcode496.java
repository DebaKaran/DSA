package stack_queue.monotonic;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 496 — Next Greater Element I
 *
 * For each element in nums1, return the next greater element from nums2.
 * nums2 contains all nums1 elements and is the "reference" array.
 */
public class NGELeetcode496 {

    /**
     * Public API — returns next greater elements for nums1, based on nums2.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        return nge(nums1, nums2);
    }

    private int[] nge(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = computeNextGreater(nums2);
        int n1 = nums1.length;
        int[] result = new int[n1];

        for (int i = 0; i < n1; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }

        return result;
    }
    /**
     * Computes next greater element for every value in nums2 using monotonic stack.
     *
     * Stack stores a decreasing sequence (top is nearest candidate).
     * Map stores: value → next greater value.
     *
     * Time:  O(n)
     * Space: O(n)
     */
    private Map<Integer, Integer> computeNextGreater(int[] nums2) {
        Map<Integer, Integer> result = new HashMap<>();

        if (nums2 == null || nums2.length == 0) return result;

        Stack<Integer> stack = new Stack<>();
        int n = nums2.length;

        // Scan right-to-left to build next-greater mapping
        for (int i = n - 1; i >= 0; i--) {
            int current = nums2[i];

            // Pop all smaller/equal elements — they can't be next greater
            while (!stack.isEmpty() && current >= stack.peek()) {
                stack.pop();
            }

            // If stack still has elements, top is next greater
            result.put(current, stack.isEmpty() ? -1 : stack.peek());

            // Push current as candidate for elements to the left
            stack.push(current);
        }

        return result;
    }
}
