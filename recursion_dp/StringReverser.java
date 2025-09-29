public class StringReverser {

     public void reverseString(char[] s) {
        reverseStringUsingRecursion(s, 0);
    }

    // Time: O(n) → each character is swapped once.
    // Space: O(n) → recursion stack space.
    
    private void reverseStringUsingRecursion(char[] s, int index) {
        if (index >= (s.length / 2)) {
            return;
        }

        char temp = s[index];
        s[index] = s[s.length - index - 1];
        s[s.length - index - 1] = temp;

        reverseStringUsingRecursion(s, index + 1);
    }
}
