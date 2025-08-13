
//GFG Problem: https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class NextGreaterElementFinder {
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        
        //Using BruteForce
        //return nextLargerElementUsingBruteForce(arr); // Using Stack
        return nextLargerElementUsingStack(arr);
        
    }
    //Time complexity:
    //A: Outer loop — runs n times → O(n).
   // B: Inner while loop — each element is pushed once and popped once in total over the entire algorithm.
   // This means the total work done in the while loop is O(n), not O(n²).

   //Space complexity: O(n) for the stack and result list.
    //Overall time complexity: O(n) + O(n) = O(n)
    private ArrayList<Integer> nextLargerElementUsingStack(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<Integer>(Collections.nCopies(n, -1));
        Stack<Integer> stack = new Stack<>();
        
        for(int i = n - 1; i >= 0; i--) {
            int val = arr[i];
            while(!stack.isEmpty() && stack.peek() <= val) {
                stack.pop();
            } 
            
            if(!stack.isEmpty()) {
                result.set(i, stack.peek());
                
            } 
            stack.push(val);
        }
        return result;
    }
    
    private ArrayList<Integer> nextLargerElementUsingBruteForce(int[] arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = arr.length;
        
        for(int i = 0; i < n; i++) {
            int val = arr[i];
            boolean isGreaterAvailable = false;
            for(int j = i + 1; j < n; j++) {
                if(arr[j] > val) {
                    isGreaterAvailable = true;
                    result.add(arr[j]);
                    break;
                }
            }
            if(!isGreaterAvailable) {
                result.add(-1);
            }
        }
        
        return result;
    }
}
