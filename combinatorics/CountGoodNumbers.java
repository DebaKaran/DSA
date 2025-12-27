package combinatorics;

//LeetCode Problem 1922: Count Good Numbers

public class CountGoodNumbers {
    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        
        //return countGoodNumbersLinear(n);
        //return countGoodNumbersUsingRepeatedMultiplication(n);

        return countGoodNumbersLogarithmic(n);
    }

    public int countGoodNumbersLogarithmic(long n) {

        // Base case: single even index has 5 choices
        if (n == 1) {
            return 5;
        }

        // Each (even, odd) index pair contributes 20 combinations
        long count = modularPower(20, n / 2);

        // If length is odd, one extra even index contributes 5 choices
        if (n % 2 != 0) {
            count = (count * 5) % MOD;
        }

        return (int) count;
    }

    /**
     * Computes (base ^ exponent) % MOD using fast exponentiation.
     */
    private long modularPower(int base, long exponent) {

        // Base case
        if (exponent == 0) {
            return 1;
        }

        // Recursive computation on half exponent
        long half = modularPower(base, exponent / 2) % MOD;

        // Square the half result
        half = (half * half) % MOD;

        // If exponent is odd, multiply once more by base
        if (exponent % 2 != 0) {
            half = (half * base) % MOD;
        }

        return half;
    }

    /**
     * Counts good numbers using repeated multiplication.
     * 
     * Time Complexity: O(n/2) which simplifies to O(n)
     * Space Complexity: O(1)
     * 
     */

    private int countGoodNumbersUsingRepeatedMultiplication(long n) {
        long times = n / 2;
        long total = 1;
        for(int time = 1; time <= times; time++) {
            total *= 20;
            total %= MOD;
        }

        if(n % 2 != 0) {
            total = (total * 5 % MOD);
        }

        return (int)total;
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
