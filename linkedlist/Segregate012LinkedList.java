/**
 * GFG: Segregate 0s, 1s and 2s in a Linked List
 * https://practice.geeksforgeeks.org/problems/segregate-0s-1s-and-2s-in-a-linked-list/1
 * 
 */
public class Segregate012LinkedList {
    public Node segregate(Node head) {
        return segregateByCounting(head);
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
