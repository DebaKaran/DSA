
/**
 * GFG Practice Link: https://practice.geeksforgeeks.org/problems/insert-a-node-at-the-front-of-a-linked-list/1
 * LeetCode Link: N/A
 * Implement a function to insert a new node at the front of a singly linked list.
 * 
 */
public class LinkedListInsertAtFront {
    public Node insertAtFront(Node head, int x) {

        return insertAtFrontBasic(head, x);
    }

    //Time Complexity: O(1)
    //Space Complexity: O(1)
    // Basic Approach
    // Create a new node and make it the new head
    // Update the next of the new node to point to the old head
    // Return the new head
    //1110 test cases passed out of 1115
    private Node insertAtFrontBasic(Node head, int x) {
        // Create a new node with the given value
        Node node = new Node(x);

        // Make the new node point to the current head
        node.next = head;

        // Update head to point to the new node
        head = node;

        // Return the updated head of the list
        return head;
    }
}
