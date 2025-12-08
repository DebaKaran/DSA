package stack_queue;

import java.util.Stack;

/**
 * LeetCode 907 — Sum of Subarray Minimums
 *
 * Brute-force O(n^2) approach:
 * For each starting index i, iterate j=i..n-1,
 * maintain the running minimum, and accumulate the sum.
 */
public final class SubarrayMinimumSummer {

    private static final int MOD = 1_000_000_007;

    private SubarrayMinimumSummer() { /* utility class */ }

    /** Public API */
    public int sumSubarrayMins(int[] arr) {
        //return sumSubarrayMinsBruteForce(arr); 
        return sumSubarrayMinsUsingStacks(arr);
    }

    /**
     * Compute sum of subarray minimums using previous-smaller / next-smaller arrays.
     *
     * Time: O(n) — each index pushed/popped at most once.
     * Space: O(n) — arrays + stack.
     */
    private int sumSubarrayMinsUsingStacks(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        final int n = nums.length;
        int[] prevLess = previousSmallerStrict(nums); // index of prev strictly smaller, or -1
        int[] nextLessOrEqual = nextSmallerOrEqualToRight(nums); // index of next smaller-or-equal, or n

        long total = 0L;

        for (int i = 0; i < n; i++) {
            int leftSpan = i - prevLess[i];        // choices on the left
            int rightSpan = nextLessOrEqual[i] - i; // choices on the right

            // Cast to long before multiplication to avoid intermediate int overflow
            long contribution = (long) leftSpan * rightSpan * nums[i];

            total = (total + contribution) % MOD;
        }

        return (int) total;
    }

    /**
     * previousSmallerStrict:
     * For each i, returns index of previous element < nums[i]; -1 if none.
     * Pop condition uses >= so the remaining top (if any) is strictly < nums[i].
     */
    private int[] previousSmallerStrict(int[] nums) {
        int n = nums.length;
        int[] prev = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return prev;
    }

    /**
     * nextSmallerOrEqualToRight:
     * For each i, returns index of next element <= nums[i]; n if none.
     * Pop condition uses > so we stop at elements <= nums[i].
     */
    private int[] nextSmallerOrEqualToRight(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            next[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return next;
    }
    /**
     * Brute-force solution.
     *
     * Time:  O(n^2)
     * Space: O(1)
     */
    private int sumSubarrayMinsBruteForce(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int n = arr.length;
        long total = 0;  // use long before applying modulo

        for (int start = 0; start < n; start++) {
            int currentMin = arr[start];

            // Extend subarray [start..end]
            for (int end = start; end < n; end++) {
                currentMin = Math.min(currentMin, arr[end]);
                total += currentMin;
                total %= MOD;
            }
        }

        return (int) total;
    }
}
