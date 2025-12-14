package combinatorics;

/**
 * LeetCode 343 – Integer Break
 * Recursive solution that explores all possible splits.
 * Demonstrates the core combinatorial state definition.
 */
class IntegerBreak {

    public int integerBreak(int number) {
        // Problem guarantees number >= 2
        //return maxProductRecursive(number);
        // dp[i] stores the maximum product obtainable from integer i
        /**int[] dp = new int[number + 1];

        // Base case
        dp[1] = 1;

        return maxProductMemoized(number, dp); */
        return maxProductBottomUpDP(number);
    }

    // Bottom-Up Dynamic Programming approach
    //Time Complexity: O(n^2)
    //Space Complexity: O(n)
    
    private int maxProductBottomUpDP(int number) {

        // dp[x] stores the maximum product obtainable by breaking integer x
        int[] dp = new int[number + 1];

        // Base case
        dp[1] = 1;

        // Build the solution for all values from 2 to number
        for (int current = 2; current <= number; current++) {

            int maxProduct = 0;

            // Try all possible first splits: current = firstPart + (current - firstPart)
            for (int firstPart = 1; firstPart < current; firstPart++) {

                // Case 1: stop breaking the remaining part
                int productWithoutFurtherSplit =
                        firstPart * (current - firstPart);

                // Case 2: continue breaking the remaining part
                int productWithFurtherSplit =
                        firstPart * dp[current - firstPart];

                // Best product for this particular split
                int bestForSplit = Math.max(
                        productWithoutFurtherSplit,
                        productWithFurtherSplit
                );

                // Track the maximum product for current
                if (bestForSplit > maxProduct) {
                    maxProduct = bestForSplit;
                }
            }

            // Store the best product for current
            dp[current] = maxProduct;
        }

        return dp[number];
    }

    /**
     * f(x): maximum product obtainable by breaking integer x
     * Time Complexity: O(n^2) – for each integer up to n, we try all splits
     * Space Complexity: O(n) – for the memoization array
     */
    private int maxProductMemoized(int current, int[] dp) {

        // If result already computed, reuse it
        if (dp[current] != 0) {
            return dp[current];
        }

        int maxProduct = 0;

        // Try all possible first splits: current = i + (current - i)
        for (int i = 1; i < current; i++) {

            // Case 1: stop breaking the remaining part
            int productWithoutFurtherSplit = i * (current - i);

            // Case 2: continue breaking the remaining part
            int productWithFurtherSplit =
                    i * maxProductMemoized(current - i, dp);

            // Best product for this split
            int bestForSplit = Math.max(
                    productWithoutFurtherSplit,
                    productWithFurtherSplit
            );

            // Track the global maximum
            if (bestForSplit > maxProduct) {
                maxProduct = bestForSplit;
            }
        }

        // Store and return the result for this state
        dp[current] = maxProduct;
        return maxProduct;
    }

    /**
     * f(x): maximum product obtainable by breaking integer x
     * Time Complexity: O(2^n) – exponential due to exploring all splits
     * Space Complexity: O(n) – recursion stack depth
     * Time Limit Exceeded on large inputs ()
     */
    private int maxProductRecursive(int number) {

        // Base case:
        // f(1) = 1 (multiplicative identity)
        if (number == 1) {
            return 1;
        }

        int maxProduct = 0;

        // Try all possible first splits: number = i + (number - i)
        for (int i = 1; i < number; i++) {

            // Case 1: stop breaking further
            int productWithoutFurtherSplit = i * (number - i);

            // Case 2: continue breaking the remaining part
            int productWithFurtherSplit =
                    i * maxProductRecursive(number - i);

            // Choose the better option for this split
            int bestForSplit = Math.max(
                    productWithoutFurtherSplit,
                    productWithFurtherSplit
            );

            // Track global maximum
            if (bestForSplit > maxProduct) {
                maxProduct = bestForSplit;
            }
        }

        return maxProduct;
    }
}
