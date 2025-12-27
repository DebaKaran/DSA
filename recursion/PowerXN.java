package recursion;

//LeetCode Problem 50: Pow(x, n)
//Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

public class PowerXN {
    public double myPow(double x, int n) {
        if(n == 0) return 1.0;
        if(n < 0) {
            x = 1 /x;
            n = -n;
        }
        //return myPowRecursively(x, n);

        return helper(x, n);
    }

    // Optimized recursive approach
    // Time Complexity: O(log n)
    // Space Complexity: O(log n) due to recursion stack

    private double helper(double x, int n) {
        if(n == 0) return 1;
        
        double half = helper(x, n / 2);

        half *= half;

        return n % 2 == 0 ? half : x * half;
    }

    // Simple recursive approach
    // Time Complexity: O(n log n)
    // Space Complexity: O(n) due to recursion stack
    private double myPowRecursively(double x, int n) {
        if(n == 0) return 1;
        if(n % 2 == 0) {
            return myPowRecursively(x * x, n / 2);
        }
        return x * myPowRecursively(x * x, n / 2);
    }
}
