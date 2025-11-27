public class LinkedListInsertAtEnd {
    
    // GFG Practice Link: https://practice.geeksforgeeks.org/problems/insert-a-node-at-the-end-of-the-linked-list/1
    // LeetCode Link: N/A
    // Implement a function to insert a new node at the end of a singly linked list.
    // Time Complexity: O(N) where N is the number of nodes in the linked list
    // Space Complexity: O(1) as we are using constant extra space
    public Node insertAtEnd(Node head, int x) {

        // Create a new node with the given value
        Node node = new Node(x);

        // If the list is empty, the new node becomes the head
        if (head == null) {
            return node;
        }

        // Temporary pointer to traverse the list
        Node temp = head;

        // Traverse until the last node (whose next is null)
        while (temp.next != null) {
            temp = temp.next;
        }

        // Attach the new node at the end
        temp.next = node;

        // Head remains unchanged, return it
        return head;
    }
}
