
//GFG Problem: https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1

import java.util.ArrayList;

public class NextGreaterElementFinder {
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        
        //Using BruteForce
        return nextLargerElementUsingBruteForce(arr);
        
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
