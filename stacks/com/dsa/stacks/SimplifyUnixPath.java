// https://leetcode.com/problems/simplify-path/description/
// 71. Simplify Path

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyUnixPath {

    public String simplifyPath(String path) {
       //return simplifyPathUsingRegEx(path);

       return simplifyPathWithoutRegEx(path);
         
    }

    // 1: Time Complexity: 
    // Splitting path: O(n)
    // Iterating parts: O(n)
    // Building result: O(n)
    // Total = O(3n) → O(n) (linear time)
    // 2: Space Complexity:
    // Stack may store up to all parts: O(n)   
    // Result StringBuilder: O(n)
    // Total = O(2n) → O(n)
    // This approach does not use regex for splitting
    
    private String simplifyPathWithoutRegEx(final String path) {
         String[] parts = path.split("/");
          Deque<String> stack = new ArrayDeque<>();

          for(String part : parts) {

             if(part.equals(".") || part.equals("")) {
                // Skip empty and current directory
                continue;
            }

            if(part.equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            } 

            stack.push(part);
          }

          StringBuilder sb = new StringBuilder();
          
           // Build simplified path
          while(!stack.isEmpty()) {
            String last = stack.removeLast();
            sb.append("/").append(last);
          }

          return sb.length() > 0 ? sb.toString() : "/";
    }

    // 1: Time Complexity: 
    // Splitting path: O(n)
    // Iterating parts: O(n)
    // Building result: O(n)
    // Total = O(3n) → O(n) (linear time)

    // 2: Space Complexity:
    // Stack may store up to all parts: O(n)
    // Result StringBuilder: O(n)
    // Total = O(2n) → O(n)
    private String simplifyPathUsingRegEx(final String path) {
         String[] parts = path.split("/+");
          Deque<String> stack = new ArrayDeque<>();

          for(String part : parts) {

            if(part.equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            } else if(part.equals(".") || part.equals("")) {
                // Skip empty and current directory
                continue;
            }
            stack.push(part);
          }

          StringBuilder sb = new StringBuilder();
          
           // Build simplified path
          while(!stack.isEmpty()) {
            String last = stack.removeLast();
            sb.append("/").append(last);
          }

          return sb.length() > 0 ? sb.toString() : "/";
    }
    
}
