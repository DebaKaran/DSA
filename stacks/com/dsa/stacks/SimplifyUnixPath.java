// https://leetcode.com/problems/simplify-path/description/
// 71. Simplify Path

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyUnixPath {


    // 1: Time Complexity: 
    // Splitting path: O(n)
    // Iterating parts: O(n)
    // Building result: O(n)
    // Total = O(3n) → O(n) (linear time)

    // 2: Space Complexity:
    // Stack may store up to all parts: O(n)
    // Result StringBuilder: O(n)
    // Total = O(2n) → O(n)
    
    public String simplifyPath(String path) {
       
          String[] parts = path.split("/+");
          Deque<String> stack = new ArrayDeque<>();

          for(String part : parts) {
            if(part.equals("") || part.equals(".")) {
                continue;
            } else if(part.equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }  
            stack.push(part);
          }

          StringBuilder result = new StringBuilder();
          
           // Build simplified path
          while(!stack.isEmpty()) {
            String last = stack.removeLast();
            result.append("/").append(last);
          }

          return result.length() > 0 ? result.toString() : "/";
    }
}
