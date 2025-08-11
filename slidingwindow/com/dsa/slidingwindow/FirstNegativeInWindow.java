package com.dsa.slidingwindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//Link: https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
//Problem: First negative in every window of size k
//Time Complexity: O(n)
//Space Complexity:O(min(n, k)), which is effectively O(k) for large arrays with few negatives.


public class FirstNegativeInWindow {

    static List<Integer> firstNegInt(int arr[], int k) {
        //find the list of all negative numbers
        int n = arr.length;
        List<Integer> result = new ArrayList<>();
        Deque<Integer> negatives = new LinkedList<>(); 
        
        for(int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++) {
             // If current element is negative, add its index
            if(arr[windowEnd] < 0) {
                negatives.addLast(windowEnd);
            }
            
            if(windowEnd - windowStart + 1 == k) {
                // If queue is not empty, front is first negative in window
                if(!negatives.isEmpty() && negatives.peekFirst() >= windowStart) {
                    result.add(arr[negatives.peekFirst()]);
                } else {
                    result.add(0);
                }
                
                // Slide the window: remove indices out of new window
                if (!negatives.isEmpty() && negatives.peekFirst() == windowStart) {
                    negatives.pollFirst();
                }
                
                windowStart++;
            }
        }
        
        return result;
        
       
    }
}
