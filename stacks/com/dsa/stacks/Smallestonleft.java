
//GFG problem: https://www.geeksforgeeks.org/problems/smaller-on-left20360700/1

import java.util.Arrays;

public class Smallestonleft {
 // Function for finding maximum and value pair
    public static int[] findSmallestonleft(int arr[], int n) {
        // Using Brute Force
        return findSmallestonleftUsingBruteForce(arr, n);
    }
    //Time complexity: O(n^2) due to nested loops.
    // Space complexity: O(n) for storing results.
    
    private static int[] findSmallestonleftUsingBruteForce(int arr[], int n) {
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        for(int i = 1; i < n; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i]) {
                    if(result[i] < arr[j]) {
                        result[i] = arr[j];
                    }
                }
            }
        }
        return result;
    }
}
