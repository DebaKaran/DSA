package com.dsa.slidingwindow;

//Leetcode Problem: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/

import java.util.HashSet;
import java.util.Set;

public class MaxVowelsInSubstring {
   public int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int n = s.length();

        int result = 0;
        int count = 0;

        for(int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++) {
            if(vowels.contains(s.charAt(windowEnd))) {
                count++;
            }

            if(windowEnd - windowStart + 1 == k) {
                result = Math.max(result, count);
                 // Slide the window: remove indices out of new window
                if(vowels.contains(s.charAt(windowStart))) {
                    count--;
                }
                windowStart++;
            }

           
        }
        return result;
    }
}