

//GFG Problem: https://practice.geeksforgeeks.org/problems/reverse-a-stack/1

import java.util.Stack;

public class ReverseStack {
    static void reverse(Stack<Integer> St) {
        reverseStackUsingRecursion(St);
        
    }
    
    // 1: Time Complexity:
    // O(n^2) - due to insertAtBottom function which takes O(n)
    // 2: Space Complexity:
    // O(n) - due to recursion stack
    
    private static void reverseStackUsingRecursion(Stack<Integer> st) {
        if(st.isEmpty()) {
            return;
        }
        
        Integer topElement = st.pop();
        
        reverseStackUsingRecursion(st);
        
        insertAtBottom(st, topElement);
    }
    
    private static void insertAtBottom(Stack<Integer> st, Integer topElement) {
        
        Stack<Integer> tempSt = new Stack<>();
        
        while(!st.isEmpty()) {
            tempSt.push(st.pop());
        }
        
        st.push(topElement);
        
        while(!tempSt.isEmpty()) {
            st.push(tempSt.pop());
        }
    }
}
