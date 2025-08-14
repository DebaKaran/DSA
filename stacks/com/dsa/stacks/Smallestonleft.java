
//GFG problem: https://www.geeksforgeeks.org/problems/smaller-on-left20360700/1

import java.util.Arrays;
import java.util.Stack;
import java.util.TreeSet;

public class Smallestonleft {
 // Function for finding maximum and value pair
    public static int[] findSmallestonleft(int arr[], int n) {
        // Using Brute Force
        // return findSmallestonleftUsingBruteForce(arr, n);

         //Using Two Stacks
        //return findSmallestonleftUsingTwoStocks(arr, n);

        return findSmallestonleftUsingTreeSet(arr, n);
    }

    //Using TreeSet
    // Time: O(n log n) → n iterations × log n per TreeSet operation.

    // Space: O(n) → In the worst case, all elements get stored in the TreeSet.

       private static int[] findSmallestonleftUsingTreeSet(int arr[], int n) {
        int[] result = new int[n];
        Arrays.fill(result, -1);
        TreeSet<Integer> set = new TreeSet<>();
        set.add(arr[0]);
        result[0] = -1;
        for(int i = 1; i < n; i++) {
            int val = arr[i];
            Integer smaller = set.lower(val);
            if(smaller != null) {
                result[i] = smaller;
            }
            set.add(val);
        }
        
        return result;
    }
    
    //
    //Using Two Stacks
    // Time Complexity

    //A: Outer loop runs n times → O(n).

    //B: Inside the loop:

    // Each element is moved between stack1 and stack2 at most once in each direction across the entire execution (not per iteration).

    //That means O(n) total stack operations.

    //So total time complexity = O(n) amortized.
    //  Worst case could look like O(n²) if we count naïvely per loop iteration, but since each push/pop is bounded across the whole run, it's O(n) amortized.
    
    //Space Complexity

    //Stacks: stack1 + stack2 together hold at most n elements. So auxiliary space = O(n).
    // Result array = O(n).

    //Overall space = O(n).
    
    private static int[] findSmallestonleftUsingTwoStocks(int arr[], int n) {
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        
        result[0] = -1;
        stack1.push(arr[0]);
        
        for(int i = 1; i < n; i++) {
            int val = arr[i];
            
            while(!stack1.isEmpty() && val <= stack1.peek()) {
                stack2.push(stack1.pop());
            }
            
            if(!stack1.isEmpty()) {
                result[i] = stack1.peek();
            } else {
                result[i] = -1;
            }
            
            stack1.push(val);
            
            while(!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        return result;
        
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
