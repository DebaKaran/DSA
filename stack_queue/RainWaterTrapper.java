package stack_queue;

/**
 * LeetCode 42 — Trapping Rain Water
 *
 * Prefix-max + suffix-max approach.
 * For each index i, trapped water = min(leftMax[i], rightMax[i]) − height[i].
 */
public final class RainWaterTrapper {

    private RainWaterTrapper() { /* utility class */ }

    /** Public API */
    public int trap(int[] height) {
        return trapUsingPrefixSuffixMax(height);
    }

    /**
     * Computes total trapped water using prefix and suffix maximum arrays.
     *
     * Time:  O(n)
     * Space: O(n)
     */
    private int trapUsingPrefixSuffixMax(int[] height) {
        if (height == null || height.length == 0) return 0;

        int[] leftMax  = computePrefixMax(height);
        int[] rightMax = computeSuffixMax(height);
        int n = height.length;
        int total = 0;

        // For each index, trapped water is determined by the lower of the two boundaries
        for (int i = 0; i < n; i++) {
            int boundary = Math.min(leftMax[i], rightMax[i]);
            if (boundary > height[i]) {
                total += boundary - height[i];
            }
        }
        return total;
    }

    /** Builds prefix maximums: leftMax[i] = max(height[0..i]). */
    private int[] computePrefixMax(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        return leftMax;
    }

    /** Builds suffix maximums: rightMax[i] = max(height[i..n-1]). */
    private int[] computeSuffixMax(int[] height) {
        int n = height.length;
        int[] rightMax = new int[n];

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        return rightMax;
    }
}

