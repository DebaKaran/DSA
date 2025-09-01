//Coding Ninjas
//https://www.codingninjas.com/codestudio/problems/print-subsequences
//https://www.naukri.com/code360/problems/subsequences-of-string_985087?leftPanelTabValue=PROBLEM

import java.util.ArrayList;

public class SubsequencesSolution {

    public static ArrayList<String> subsequences(String str) {
      ArrayList<String> soln = new ArrayList<>();
     
      StringBuilder sb = new StringBuilder();
      subsequences(str, soln, 0, sb);
      return soln;

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
