package recursion;

public class StringToIntegerAtoi {
    
    /**
     * LeetCode 8: String to Integer (atoi)
     * Converts a string to a 32-bit signed integer.
     * 
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(1)
     * 
     */
    public int stringToInt(String s) {

        int index = 0;
        int length = s.length();

        // Step 1: Skip leading whitespace
        while (index < length && s.charAt(index) == ' ') {
            index++;
        }

        // Step 2: Handle optional sign
        int sign = 1;
        if (index < length && s.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (index < length && s.charAt(index) == '+') {
            index++;
        }

        // Step 3: Parse digits
        long value = 0;
        while (index < length && Character.isDigit(s.charAt(index))) {
            value = value * 10 + (s.charAt(index) - '0');
            index++;

            // Step 4: Clamp on overflow
            if (value * sign >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (value * sign <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        // Step 5: Apply sign and return
        return (int) (value * sign);
    }
}
