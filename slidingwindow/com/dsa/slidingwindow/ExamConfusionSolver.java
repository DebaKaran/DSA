package com.dsa.slidingwindow;

//LeetCode Problem: 2024. https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/

public class ExamConfusionSolver {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return maxConsecutiveAnswersUsingRecursion(answerKey.toCharArray(), k, 0);
    }

    // Recursive function to find the maximum consecutive answers
    // time complexity is O(2^n) in the worst case due to recursion
    // space complexity is O(n) for the recursion stack

    // This function uses recursion to explore all possible combinations of changing 'T' to 'F' and vice versa
    // and returns the maximum length of consecutive answers that can be achieved with at most k changes
    // Note: This is not an efficient solution for large inputs, but it demonstrates the recursive approach
    // to solve the problem.

    private int maxConsecutiveAnswersUsingRecursion(char[] answerKey, int k, int index) {
        //Base case
        if(k == 0 ||index >= answerKey.length) {
            int n = answerKey.length;
            int consutiveT = 0;
            int consutiveF = 0;
           
            if(answerKey[0] == 'T') {
                consutiveT++;
            } else {
                consutiveF++;
            }
            int max = 1;

            for(int i = 1; i < n; i++) {
                if(answerKey[i] == 'T') {
                    consutiveT++;
                    max = Math.max(max, consutiveT);
                    consutiveF = 0;
                } else {
                    consutiveF++;
                    max = Math.max(max, consutiveF);
                    consutiveT = 0;
                }
            }

            return max;
        }

        int excluded = maxConsecutiveAnswersUsingRecursion(answerKey, k, index + 1);

        int included = 0;

        if(answerKey[index] == 'T') {
            answerKey[index] = 'F';
        } else {
            answerKey[index] = 'T';
        }

        included = maxConsecutiveAnswersUsingRecursion(answerKey, k - 1, index + 1);

        // backtrack: undo the change
        if(answerKey[index] == 'T') {
            answerKey[index] = 'F';
        } else {
            answerKey[index] = 'T';
        }

        return Math.max(excluded, included);
    }
}
