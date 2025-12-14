package combinatorics;

/**
 * LeetCode 509 – Fibonacci Number
 * Computes the nth Fibonacci number using space-optimized DP.
 * Difficulty: Easy
 * Tags: Recursion, Dynamic Programming, Memoization, Math
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class FibonacciNumber {

    public int fib(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }

        int prev = 1;      // F(n-1)
        int prevPrev = 0;  // F(n-2)

        for (int i = 2; i <= n; i++) {
            int current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }

        return prev;
    }
}

