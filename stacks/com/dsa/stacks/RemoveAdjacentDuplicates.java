import java.sql.Time;
import java.util.Stack;

public class RemoveAdjacentDuplicates {
    public String removeDuplicates(String s) {
        //Using Stack
        return removeDuplicateUsingStack(s);
    }

//Time Complexity

//You iterate through each character in the string once → O(n).

//Each character is either pushed or popped from the stack at most once → O(n) operations total.

//Building the result (while(!stack.isEmpty()) sb.append(stack.pop())) is also O(n).

//Reversing the string (sb.reverse()) is O(n).

//Total = O(n) + O(n) + O(n) = O(n).

// Space Complexity

//The stack can hold up to n characters in the worst case (if no adjacent duplicates). → O(n).

//The StringBuilder used at the end also holds up to n characters. → O(n).

//Total = O(n) auxiliary space.
    private String removeDuplicateUsingStack(String s) {
        //Using Stack
        char[] charArry = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while(i < charArry.length) {
            char ch = charArry[i];
            if(!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
            i++;
        }

        if(stack.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        sb.reverse();

        return sb.toString();
    }
}
