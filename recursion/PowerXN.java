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
        return myPowRecursively(x, n);
        
    }


    private double myPowRecursively(double x, int n) {
        if(n == 0) return 1;
        if(n % 2 == 0) {
            return myPowRecursively(x * x, n / 2);
        }
        return x * myPowRecursively(x * x, n / 2);
    }
}
