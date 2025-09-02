import java.util.Arrays;

public class SubsetSumSolver {
    static Boolean isSubsetSum(int arr[], int sum) {
    //return isSubsetSumRecursion(arr, sum, n - 1);
        
        int[][] memos = new int[n][sum + 1];
        for(int[] memo : memos) {
            Arrays.fill(memo, -1);
        }
        
        int result = isSubsetSumUsingMemoziation(arr, sum, n - 1, memos);
        
        return result == 1;
    }
    
    //Time Complexity: O(n * sum)
    //Space Complexity: O(n * sum) for the memoization table + O(n)
    // Using Memoization
    private static int isSubsetSumUsingMemoziation(int arr[], int sum, int idx, int[][] memos) {
        // If sum becomes 0 → subset found
        if(sum == 0) return 1;
        
        // If no elements left and sum is not 0 → not possible
        if(idx < 0) {
            return 0;
        }
        
        if(memos[idx][sum] != -1) {
            return memos[idx][sum];
        }
        
        // If current element > sum, skip it
        if(arr[idx] > sum) {
            return isSubsetSumUsingMemoziation(arr, sum, idx - 1, memos);
        }
        
        // Either include or exclude the current element
        int included = isSubsetSumUsingMemoziation(arr, sum - arr[idx], idx - 1, memos);
        if(included == 1) {
            return memos[idx][sum] = 1;
        }
        
        int excluded = isSubsetSumUsingMemoziation(arr, sum, idx - 1, memos);
        
        if(excluded == 1) {
            return memos[idx][sum] = 1;
        }
        
        return memos[idx][sum] = 0;
       
    }    

// Using Recursion
// Time Complexity: O(2^n) in worst case
// Space Complexity: O(n) for the recursion stack
//TLE for large inputs

private static boolean isSubsetSumRecursion(int arr[], int sum, int idx) {
    // If sum becomes 0 → subset found
    if (sum == 0) return true;

    // If no elements left and sum is not 0 → not possible
    if (idx < 0) return false;

    // If current element > sum, skip it
    if (arr[idx] > sum) {
        return isSubsetSumRecursion(arr, sum, idx - 1);
    }

    // Either include or exclude the current element
    return isSubsetSumRecursion(arr, sum, idx - 1) || 
           isSubsetSumRecursion(arr, sum - arr[idx], idx - 1);
    }

}
