//Leetcode Problem: Trapping Rain Water
// Problem Link: https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {
     public int trap(int[] height) {
        //return calculateTrappedWater(height);
        return calculateTrappedWaterUsingTwoPointers(height);
    }

    private int calculateTrappedWaterUsingTwoPointers(int[] height) {
        int n = height.length;       // Number of bars in the elevation map
        int total = 0;               // Total trapped water
        int left = 0;                 // Pointer starting from left side
        int right = n - 1;            // Pointer starting from right side
        int lMax = 0;                 // Highest bar seen so far from the left
        int rMax = 0;                 // Highest bar seen so far from the right

        // Process until the two pointers meet
        while (left < right) {
            // Update left max and right max heights
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);

            // Decide which side to process
            if (lMax <= rMax) {
                // If left's max is smaller, trapped water is limited by lMax
                // Water trapped at 'left' = lMax - height[left]
                total += (lMax - height[left]);
                left++; // Move left pointer inward
            } else {
                // If right's max is smaller, trapped water is limited by rMax
                // Water trapped at 'right' = rMax - height[right]
                total += (rMax - height[right]);
                right--; // Move right pointer inward
            }
         }

        return total; // Final accumulated trapped water
    }

    // Time complexity: O(n) due to single pass through the array.
    // Space complexity: O(n) for the prefix and suffix max arrays.
    // This approach uses two auxiliary arrays to store the maximum heights
    // to the left and right of each element, allowing us to calculate the  
    // trapped water at each index efficiently.
    // This is a two-pointer approach that optimizes space usage.
    // However, we can also solve this problem using a stack-based approach.
    
    private int calculateTrappedWater(int[] height) {
        int n = height.length;
        int[] prefixMax = findPrefixMax(height);
        int[] suffixMax = findSuffixMax(height);
        int total = 0;

        for (int i = 0; i < n; i++) {
            int minAmongPrefixAndSuffix = Math.min(prefixMax[i], suffixMax[i]);
            total += (minAmongPrefixAndSuffix - height[i]);
        }
        return total;
    }

    private int[] findPrefixMax(int[] height) {
        int n = height.length;
        int[] prefixMax = new int[n];
        prefixMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], height[i]);
        }
        return prefixMax;
    }

    private int[] findSuffixMax(int[] height) {
        int n = height.length;
        int[] suffixMax = new int[n];
        suffixMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], height[i]);
        }
        return suffixMax;
    }
}
