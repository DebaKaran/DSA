import java.util.ArrayList;
import java.util.List;

//gfg problem: https://practice.geeksforgeeks.org/problems/subset-sums2234/1

public class SubsetSums {

    public ArrayList<Integer> subsetSums(int[] arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        List<Integer> ds = new ArrayList<Integer>();
        //subsetSums(arr, 0, ds, result);
        subsetSumsImproved(arr, 0, 0, result);
        return result;
    }
    
    // Improved approach without using extra list to store current subset
    // 1: Time Complexity:  
    // O(2^n) - where n is the number of elements in the input array.
    // Explanation: There are 2^n possible subsets of an array with n elements.
    // For each subset, we calculate the sum in constant time O(1) by maintaining a running sum.
    // Therefore, the overall time complexity is O(2^n).
    // 2: Space Complexity:
    // O(n) - for the recursion stack. The depth of the recursion tree can go up to n in the worst case.
    
    private void subsetSumsImproved(int[] arr, int idx, int sum, ArrayList<Integer> result) {
        if(idx >= arr.length ) {
            result.add(sum);
            return;
        }
        
        //picked
        subsetSumsImproved(arr, idx + 1, sum + arr[idx], result);
        
        //not-picked
        subsetSumsImproved(arr, idx + 1, sum, result);
    }

    // 1: Time Complexity:
    // O(2^n * n) - where n is the number of elements in the input array.
    // Explanation: There are 2^n possible subsets of an array with n elements.
    // For each subset, we calculate the sum which takes O(n) time in the worst case (when the subset includes all elements).
    // Therefore, the overall time complexity is O(2^n * n).
    // 2: Space Complexity:
    // O(n) - for the recursion stack and the temporary list used to store the current
    private void subsetSums(int[] arr, int idx, List<Integer> ds, ArrayList<Integer> result) {
        if(idx >= arr.length ) {
            int sum = 0;
            for(int i = 0; i < ds.size(); i++) {
                sum += ds.get(i);
            }
            result.add(sum);
            return;
        }
        
        // Pick current element
        ds.add(arr[idx]);
        subsetSums(arr, idx + 1, ds, result);
        
        // Not pick current element
        ds.remove(ds.size() - 1);
        subsetSums(arr, idx + 1, ds, result);
    }
}
