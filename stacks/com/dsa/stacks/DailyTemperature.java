// LeetCode Problem: 739. Daily Temperatures

public class DailyTemperature {
public int[] dailyTemperatures(int[] temperatures) {
       return dailyTemperaturesUsingBruteForce(temperatures);
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
