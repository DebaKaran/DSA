public class SubsetSumSolver {
    static Boolean isSubsetSum(int arr[], int sum) {
    int n = arr.length;
    return isSubsetSumRecursion(arr, sum, n - 1);
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
