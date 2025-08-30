// LeetCode Problem: 739. Daily Temperatures

import java.util.Stack;

public class DailyTemperature {
public int[] dailyTemperatures(int[] temperatures) {
       //return dailyTemperaturesUsingBruteForce(temperatures);

       return nextGreaterElement(temperatures);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private int[] nextGreaterElement(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        //srored index
        Stack<Integer> ngrStack = new Stack<>();

        for(int i = n - 1; i >= 0; i--) {
            int val = temperatures[i];
            while(!ngrStack.isEmpty() && val >= temperatures[ngrStack.peek()]) {
                ngrStack.pop();
            }

            if(!ngrStack.isEmpty()) {
                result[i] = ngrStack.peek() - i;
            }

            ngrStack.push(i);
        }

        return result;
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // Brute Force Approach - Time Limit Exceeded
    
    private int[] dailyTemperaturesUsingBruteForce(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++) {
            int val = temperatures[i];
            for(int j = i + 1; j < n; j++) {
                if(temperatures[j] > val) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }
}
