
//https://leetcode.com/problems/remove-stars-from-string/description/
//2390. Remove Stars From a String

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveStarsFromString {

// 1: Time Complexity:

// Iterating through all characters: O(n)

// Building the result string: O(n)

// Total = O(2n) → O(n) (linear time)

// 2: Space Complexity:

// Stack/Deque may store up to all characters: O(n)

// StringBuilder result: O(n)

 // Total = O(n)

    public String removeStars(String s) {
        Deque<Character> deque = new ArrayDeque<>();

        for(char ch : s.toCharArray()) {
            if(ch == '*' && !deque.isEmpty()) {
                deque.pop();
            } else {
                deque.push(ch);
            }
            
        }

        // Build result in correct order
        StringBuilder sb = new StringBuilder(deque.size());
        while(!deque.isEmpty()) {
           sb.append(deque.removeLast()); // append in original order
        } 

        return sb.toString();
    }

    public String removeStars1(String s) {
        char[] arr = s.toCharArray();
        int k = 0; // write pointer

        for (char ch : arr) {
            if (ch == '*') {
                if (k > 0) k--; // undo last written character
            } else {
                arr[k++] = ch; // keep valid character
            }
        }

        return new String(arr, 0, k); // build result from valid portion
    }
}
