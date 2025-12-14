package combinatorics;

/**
 * LeetCode 343 – Integer Break
 * Recursive solution that explores all possible splits.
 * Demonstrates the core combinatorial state definition.
 */
class IntegerBreak {

    public int integerBreak(int number) {
        // Problem guarantees number >= 2
        return maxProductRecursive(number);
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
