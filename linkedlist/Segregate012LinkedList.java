/**
 * GFG: Segregate 0s, 1s and 2s in a Linked List
 * https://practice.geeksforgeeks.org/problems/segregate-0s-1s-and-2s-in-a-linked-list/1
 * 
 */
public class Segregate012LinkedList {
    public Node segregate(Node head) {
        //return segregateByCounting(head);
        
        return segregateByPointerManipulation(head);
    }
    
    // Approach: Pointer Manipulation to Create Separate Lists
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    
    private Node segregateByPointerManipulation(Node head) {
        // Tails for the three lists
        Node zeros = new Node(-1);
        Node ones = new Node(-1);
        Node twos = new Node(-1);
        
        Node zerosHead = zeros;
        Node onesHead = ones;
        Node twosHead = twos;
        
        
        Node curr = head;
        
        // Distribute nodes into 0-, 1-, and 2-lists
        while(curr != null) {
            int val = curr.data;
            if(val == 0) {
                zeros.next = curr;
                zeros = zeros.next;
            } else if(val == 1) {
                ones.next = curr;
                ones = curr;
            } else if(val == 2) {
                twos.next = curr;
                twos = curr;
            }
            curr = curr.next;
        }
        
        
        // Terminate the 2-list
        twos.next = null;
        
        // Connect 1-list to 2-list
        ones.next = twosHead.next;
        
        // Connect 0-list to either 1-list (if exists) or 2-list
        zeros.next = onesHead.next != null ? onesHead.next : twosHead.next;
        
        return zerosHead.next;
    }

    // Approach: Counting and Overwriting Node Values
    // Time Complexity: O(N) two times traversal
    // Space Complexity: O(1)

    public Node segregateByCounting(Node head) {

        // Counters to store number of 0s, 1s and 2s
        int zeros = 0;
        int ones = 0;
        int twos = 0;

        // Pointer to traverse the linked list for counting
        Node curr = head;

        // First pass: count occurrences of 0, 1 and 2
        while (curr != null) {
            if (curr.data == 0) {
                zeros++;
            } else if (curr.data == 1) {
                ones++;
            } else if (curr.data == 2) {
                twos++;
            }

            // Move to next node
            curr = curr.next;
        }

        // Reset pointer to head for rewriting values
        curr = head;

        // Second pass: overwrite node values with 0s
        while (zeros > 0) {
            curr.data = 0;
            curr = curr.next;
            zeros--;
        }

        // Overwrite next nodes with 1s
        while (ones > 0) {
            curr.data = 1;
            curr = curr.next;
            ones--;
        }

        // Overwrite remaining nodes with 2s
        while (twos > 0) {
            curr.data = 2;
            curr = curr.next;
            twos--;
        }

        // Return the head of the modified list
        return head;
    }
}
