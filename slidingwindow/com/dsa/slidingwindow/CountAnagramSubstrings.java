// User function Template for Java

//https://www.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
package com.dsa.slidingwindow;

import java.util.HashMap;
import java.util.Map;

class CountAnagramSubstrings  {
    
	// Function to count the occurrences of anagrams of a pattern in a given text
	// pat: pattern string, txt: text string
	// Returns the count of occurrences of anagrams of pat in txt	
	//time complexity is O(n)
	//space complexity is O(m)
    int search(String pat, String txt) {
    if(txt == null|| pat == null) return 1;
	
	int n = txt.length();
	int m = pat.length();
	
	if(m > n) return 0;
	
	Map<Character, Integer> map = new HashMap<>();
	for(int i = 0; i < m; i++){
		char ch = pat.charAt(i);
		map.put(ch, map.getOrDefault(ch, 0) + 1);
	}
	
	int count = 0;
	
	for(int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++){

	   char endChar = txt.charAt(windowEnd);
	   if(map.containsKey(endChar)) {
	       map.put(endChar, map.get(endChar) - 1);
	   }
	   
	   // If window size is m
	   if(windowEnd - windowStart + 1 == m) {
	       // Check match
	       boolean match = true;
	       for(int val : map.values()) {
	           if(val != 0) {
	               match = false;
	               break;
	           }
	       }
	       
	       if(match) {
	           count++;
	       }
	       
	       // Remove left char before sliding
	       char startChar = txt.charAt(windowStart);
	       if(map.containsKey(startChar)) {
	           map.put(startChar, map.get(startChar) + 1);
	       }
	       
	       windowStart++;
	   }
	} 
	
	return count;
        
    }
	// This approach is O(n * m)
    /** int search(String pat, String txt) {
    if(txt == null|| pat == null) return 1;
	
	int n = txt.length();
	int m = pat.length();
	
	if(m > n) return 0;
	
	Map<Character, Integer> map = new HashMap<>();
	for(int i = 0; i < m; i++){
		char ch = pat.charAt(i);
		map.put(ch, map.getOrDefault(ch, 0) + 1);
	}
	
	int count = 0;
	int windowEnd = 0;
	
	for(int windowStart = 0; windowStart <= n - m; windowStart++){
// 		if(windowStart == 1) {
// 			windowEnd = windowStart + m - 1;
// 		}
		
		//iterating in side the window
		while(windowEnd < (windowStart + m)) {
			char ch = txt.charAt(windowEnd);
			if(map.containsKey(ch)) {
				map.put(ch, map.get(ch) - 1);
			}
			windowEnd++;
		}
		
		//check whether map has all the value zero or not
		boolean isAnagram = true;
		for(char ch: map.keySet()) {
			if(map.get(ch) != 0) {
			   isAnagram = false;
				break;
			}
		}
		
		if(isAnagram) {
			count++;
		}
		
		//now we need to check whether start of window is part of map or not, if it is then we have to increase corresponding map value
		
		if(map.containsKey(txt.charAt(windowStart))) {
			map.put(txt.charAt(windowStart), map.get(txt.charAt(windowStart)) + 1);
		}
	} 
	
	return count;
        
    } */
}