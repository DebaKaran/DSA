//Coding Ninjas
//https://www.codingninjas.com/codestudio/problems/print-subsequences
//https://www.naukri.com/code360/problems/subsequences-of-string_985087?leftPanelTabValue=PROBLEM

import java.util.ArrayList;

public class SubsequencesSolution {

    public static ArrayList<String> subsequences(String str) {
      ArrayList<String> soln = new ArrayList<>();
     
      /**StringBuilder sb = new StringBuilder();
      subsequences(str, soln, 0, sb); */
      subsequencesUsingPowerSet(str, soln);
      return soln;

    }

    // Using Bit Manipulation - Power Set
    // Total Subsequences = 2^n - 1 (excluding empty subsequence)
    // For each character we have 2 choices - either include or exclude
    // So, total subsequences = 2 * 2 * 2 ... n times = 2^n
    // We can represent these choices using bits of a number from 1 to 2^n - 1
    // If the jth bit of the number is set, we include the jth character    
    // else we exclude it.
    // Example: str = "abc"
    // Subsequences: a, b, c, ab, ac, bc, abc
    // Numbers:     1, 2, 4, 3, 5, 6, 7
    // Binary:      001,    010,100,011,101,110,111
    // Index:       0  1  2
    // If we take number 5 (101 in binary), it means we include characters at   
    // index 0 and 2 (a and c) and exclude character at index 1 (b), so we get "ac"
    // We start from 1 to exclude the empty subsequence (000 in binary)
    // We go up to 2^n - 1 to include all possible subsequences
    // We use (1 << n) to calculate 2^n using bitwise left shift
    // (1 << j) is used to create a mask with only the jth bit
    // We use (mask & (1 << j)) to check if the jth bit is set in the mask
    // If it is set, we include the character at index j in the current subsequence 
    // being formed
    // This way we generate all possible subsequences of the string
    // and add them to the solution list.   
    // This approach is efficient and avoids the overhead of recursion.
    // It directly generates all subsequences using bit manipulation.
    // This method is particularly useful for generating power sets or
    // all combinations of a set.
    // It is a common technique used in combinatorial problems.
    // It is also known as the "bitmasking" technique.
    // It is a powerful tool in competitive programming and algorithm design.

    //Time Complexity: O(n * 2^n) - n is the length of the string
    //Space Complexity: O(n) - for StringBuilder in worst case
    private static void subsequencesUsingPowerSet(String str, 
    ArrayList<String> soln) {
        int n = str.length();
        int total = (1 << n);

        for(int mask = 1; mask < total; mask++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if((mask & ( 1 << j)) != 0) {
                    sb.append(str.charAt(j));
                }
            }
            soln.add(sb.toString());
        }
    }

    // 1: Time Complexity:
    // O(n * 2^n) - n is the length of the string   
    // 2: Space Complexity:
    // O(n) - recursive stack space + O(n) for StringBuilder in worst case

   private static void subsequences(String str, ArrayList<String> soln, int index, StringBuilder sb){
       if(index >= str.length()) {
           if(sb.length() > 0) {// only add non-empty subsequences
               soln.add(sb.toString());
           }
           
           return;
       }
    
      //picking
      sb.append(str.charAt(index));
      subsequences(str, soln, index + 1, sb);

      //backtracking
      //sb.deleteCharAt(sb.length() - 1);
      sb.setLength(sb.length() - 1);  // O(1) improvement
      subsequences(str, soln, index + 1, sb);

   }
}
