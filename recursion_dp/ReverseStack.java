

//GFG Problem: https://practice.geeksforgeeks.org/problems/reverse-a-stack/1

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReverseStack {
    static void reverse(Stack<Integer> St) {
        //reverseStackUsingRecursion(St);
        //reverseStackUsingRecursionWithoutExtraStack(St);

        //Using Tail Recursion
        List<Integer> helper = new ArrayList<>();
        reverseUsingTailRecursion(St, helper);
        
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

    // 1: Time Complexity:
    // O(n^2) - due to insertAtBottom function which takes O(n) 
    // 2: Space Complexity:
    // O(n) - due to recursion stack
    
    private static void reverseStackUsingRecursionWithoutExtraStack(Stack<Integer> st) {
        if(st.isEmpty()) {
            return;
        }
        
        Integer topElement = st.pop();
        
        reverseStackUsingRecursionWithoutExtraStack(st);
        
        insertAtBottom(st, topElement);
    }
    
    private static void insertAtBottomWithoutExtraStack(Stack<Integer> st, Integer topElement) {
        
        if(st.isEmpty()) {
            st.push(topElement);
            return;
        }
        
        insertAtBottomWithoutExtraStack(st, st.pop());
        
        
        st.push(topElement);
    }
    // 1: Time Complexity:
    // O(n) - due to single traversal of stack  
    // 2: Space Complexity:
    // O(n) - due to helper list    
    
    private static void reverseUsingTailRecursion(Stack<Integer> st, List<Integer> helper) {
         if(st.isEmpty()) {
            for(int i = 0; i < helper.size(); i++) {
                st.push(helper.get(i));
            }
            return;
         }
         
         helper.add(st.pop());
         reverseUsingTailRecursion(st, helper);
     }
}
