package stack_queue;

import java.util.Stack;

/**
 * LeetCode 2104 — Sum of Subarray Ranges
 *
 * Brute-force approach:
 * For each subarray [i..j], compute its minimum and maximum,
 * accumulate (max - min) into the result.
 */
public final class SubarrayRangeSumCalculator {

    private SubarrayRangeSumCalculator() { /* utility class */ }

    /** Public API */
    public long subArrayRanges(int[] nums) {
        //return subArrayRangesBruteForce(nums);
        return computeSubarrayRanges(nums);
    }

    /** Compute total = sum(max of subarray) - sum(min of subarray). */
    private long computeSubarrayRanges(int[] nums) {
        if (nums == null || nums.length == 0) return 0L;

        long minSum = sumOfSubarrayMins(nums);
        long maxSum = sumOfSubarrayMaxs(nums);
        return maxSum - minSum;
    }

    /* ---------------------- Sum of subarray maximums ---------------------- */

    /**
     * Contribution method for maxima:
     * For each i, find previous index with value > nums[i] (prevGreaterStrict)
     * and next index with value >= nums[i] (nextGreaterOrEqual). The number of
     * subarrays where nums[i] is the maximum = (i - prev) * (next - i).
     */
    private long sumOfSubarrayMaxs(int[] nums) {
        final int n = nums.length;
        int[] prevGreater = previousGreaterStrict(nums);      // index of prev > nums[i], or -1
        int[] nextGreaterOrEqual = nextGreaterOrEqualToRight(nums); // index of next >= nums[i], or n

        long total = 0L;
        for (int i = 0; i < n; i++) {
            long leftSpan = i - prevGreater[i];
            long rightSpan = nextGreaterOrEqual[i] - i;
            total += leftSpan * rightSpan * (long) nums[i];
        }
        return total;
    }

    private int[] previousGreaterStrict(int[] nums) {
        int n = nums.length;
        int[] prev = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            // pop indices whose value <= current to leave strictly greater at top (if any)
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }
            prev[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return prev;
    }

    private int[] nextGreaterOrEqualToRight(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            // pop indices whose value < current to stop at next >= current
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            next[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return next;
    }

    /* ---------------------- Sum of subarray minimums ---------------------- */

    /**
     * Contribution method for minima:
     * For each i, find previous index with value < nums[i] (prevSmallerStrict)
     * and next index with value <= nums[i] (nextSmallerOrEqual). The number of
     * subarrays where nums[i] is the minimum = (i - prev) * (next - i).
     */
    private long sumOfSubarrayMins(int[] nums) {
        final int n = nums.length;
        int[] prevSmaller = previousSmallerStrict(nums);         // index of prev < nums[i], or -1
        int[] nextSmallerOrEqual = nextSmallerOrEqualToRight(nums); // index of next <= nums[i], or n

        long total = 0L;
        for (int i = 0; i < n; i++) {
            long leftSpan = i - prevSmaller[i];
            long rightSpan = nextSmallerOrEqual[i] - i;
            total += leftSpan * rightSpan * (long) nums[i];
        }
        return total;
    }

    private int[] previousSmallerStrict(int[] nums) {
        int n = nums.length;
        int[] prev = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            // pop indices whose value >= current to leave strictly smaller at top (if any)
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            prev[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return prev;
    }

    private int[] nextSmallerOrEqualToRight(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            // pop indices whose value > current to stop at next <= current
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
                st.pop();
            }
            next[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return next;
    }

    /**
     * Brute-force implementation.
     *
     * Time:  O(n^2)
     * Space: O(1)
     */
    private long subArrayRangesBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        long total = 0L;
        final int n = nums.length;

        for (int start = 0; start < n; start++) {
            int minValue = nums[start];
            int maxValue = nums[start];

            // Expand subarray [start..end]
            for (int end = start; end < n; end++) {
                int value = nums[end];

                // Update running min and max
                minValue = Math.min(minValue, value);
                maxValue = Math.max(maxValue, value);

                total += (maxValue - minValue);
            }
        }

        return total;
    }
}

