import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/find-all-anagrams-in-a-string/

public class AnagramIndicesFinder {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || p == null) {
            return result;
        }

        int n = s.length();
        int m = p.length();
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < m; i++) {
            char ch = p.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(int windowStart = 0, windowEnd = 0; windowEnd < n; windowEnd++) {
            char endChar = s.charAt(windowEnd);
            if (map.containsKey(endChar)) {
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
                    result.add(windowStart);
                }
                char startChar = s.charAt(windowStart);
                if(map.containsKey(startChar)) {
                    map.put(startChar, map.get(startChar) + 1);
                }
                
                windowStart++;
            }
        }
        return result;
    }
}