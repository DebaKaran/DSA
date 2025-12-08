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
        //return trapUsingPrefixAndSuffixMax(height);
        //return trapWithoutUsingExtraSpace(height);

        return trapTwoPointers(height);
    }

    /** Two-pointer implementation (O(n) time, O(1) space). */
    private int trapTwoPointers(int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;   // max seen so far from the left
        int rightMax = 0;  // max seen so far from the right

        int total = 0;

        // Process until pointers cross
        while (left <= right) {
            if (height[left] <= height[right]) {
                // Left side is the limiting side (leftMax <= rightMax)
                if (height[left] >= leftMax) {
                    // update left boundary
                    leftMax = height[left];
                } else {
                    // water trapped = leftMax - height[left]
                    total += (leftMax - height[left]);
                }
                left++;
            } else {
                // Right side is the limiting side (rightMax < leftMax)
                if (height[right] >= rightMax) {
                    // update right boundary
                    rightMax = height[right];
                } else {
                    // water trapped = rightMax - height[right]
                    total += (rightMax - height[right]);
                }
                right--;
            }
        }

        return total;
    }

    /**
     * Computes trapped rainwater by splitting at the global maximum height.
     *
     * Time:  O(n)
     * Space: O(1)
        * O(1)-space approach using the fact that the global maximum bar splits
        * the problem into two independent monotonic scans:
        * - Left → maxIndex
        * - Right → maxIndex
        */

    private int trapWithoutExtraSpace(int[] height) {
        if (height == null || height.length == 0) return 0;

        int n = height.length;
        int maxIndex = findMaxIndex(height);
        int total = 0;

        // Left part: process using running leftMax
        int leftMax = 0;
        for (int i = 0; i < maxIndex; i++) {
            leftMax = Math.max(leftMax, height[i]);
            total += leftMax - height[i];
        }

        // Right part: process using running rightMax
        int rightMax = 0;
        for (int i = n - 1; i > maxIndex; i--) {
            rightMax = Math.max(rightMax, height[i]);
            total += rightMax - height[i];
        }

        return total;
    }

    /** Returns index of the highest bar in the array. */
    private int findMaxIndex(int[] height) {
        int maxIndex = 0;
        int max = height[0];

        for (int i = 1; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }
        return maxIndex;
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

