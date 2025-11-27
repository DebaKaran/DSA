package dll;

/**
 * GFG Problem Link: https://practice.geeksforgeeks.org/problems/construct-doubly-linked-list-from-array/1
 * LeetCode Link: N/A
 * 
 * Implement a function to construct a Doubly Linked List from a given array.
 * 
 * Time Complexity: O(N) where N is the number of elements in the array
 * Space Complexity: O(N) for the new doubly linked list created
 */
public class ConstructDoublyLinkedListFromArray {
    /**
     * Constructs a Doubly Linked List from the given array
     * and returns the head of the list.
     *
     * The array is assumed to have at least one element
     * as per GFG problem constraints.
     */
    Node constructDLL(int arr[]) {

        // Create the head node using the first element of the array
        Node head = new Node(arr[0]);

        // temp keeps track of the last node in the DLL constructed so far
        Node temp = head;

        // Iterate over remaining elements in the array
        for (int i = 1; i < arr.length; i++) {

            // Create a new node for the current array element
            Node nd = new Node(arr[i]);

            // Set backward link of new node to the previous node
            nd.prev = temp;

            // Set forward link of previous node to the new node
            temp.next = nd;

            // Move temp to point to the newly created node
            temp = nd;
        }

        // Return the head of the constructed doubly linked list
        return head;
    }
}
