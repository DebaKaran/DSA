import java.util.Stack;

//LeetCode 224: Basic Calculator
//https://leetcode.com/problems/basic-calculator/description/

public class BasicCalculator {
    public int calculate(String s) {
     return calculateUsingStack(s);
    }

    //Time Complexity

//You scan through the string once (O(n) where n = length of s).

// For each character:

// Digits are processed in a loop, but each digit is consumed exactly once across the whole run → O(n).

// Each ( and ) leads to at most one push + one pop on the stack → O(1) each.

// No nested reprocessing (you never re-scan the same substring).

//Time Complexity = O(n)

// Space Complexity

//You use a stack to store previous results and signs when encountering parentheses.

// In the worst case (like "((((((1))))))"), you’ll push for each (.

//Each push stores 2 integers → O(2n) = O(n).

//Space Complexity = O(n)
    private int calculateUsingStack(String s) {
        int sign = 1;
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0; 
        while(i < s.length()) {
            char ch = s.charAt(i);

            //ch is a digit
            if(Character.isDigit(ch)) {
                int val = 0;

                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = (val * 10) + (s.charAt(i) - '0');
                    i++;
                }
                i--;// step back since outer loop also increments
                val = val * sign;
                sum += val;
            } else if(ch == '(') {
                stack.push(sum);
                stack.push(sign);
                sum = 0; 
                sign = 1;
            } else if(ch == ')') {
              int prevSign = stack.pop();
                int prevSum = stack.pop();
                sum = prevSum + prevSign * sum;

            } else if(ch == '-') {
                sign = -1;
            }else if(ch == '+') {
                sign = 1;
            }

            i++;
        }

        return sum;
    }
}