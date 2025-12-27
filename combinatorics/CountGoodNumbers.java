package combinatorics;

//LeetCode Problem 1922: Count Good Numbers

public class CountGoodNumbers {
    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        
        return countGoodNumbersLinear(n);
    }
    /**
     * Counts good numbers using a linear iteration approach.
     * This version is correct but inefficient for large n.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     */
    private int countGoodNumbersLinear(long length) {

        long remaining = length;
        long result = 1;

        // Each pair contributes 5 * 4 = 20 combinations
        while (remaining >= 2) {
            result = (result * 20) % MOD;
            remaining -= 2;
        }

        // If length is odd, one extra even-position digit (5 choices)
        if (length % 2 != 0) {
            result = (result * 5) % MOD;
        }

        return (int) result;
    }
}
