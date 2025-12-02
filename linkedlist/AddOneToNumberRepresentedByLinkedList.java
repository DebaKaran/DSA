/**
 * GFG - Add One to a Number Represented as Linked List
 * https://practice.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
 * https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
 */
public class AddOneToNumberRepresentedByLinkedList {
    public Node addOne(Node head) {
        if(head == null) {
            return new Node(1);
        }

        //return addOneToLinkedListNumber(head);
        
        // Recursively add one starting from the last node
        int carry = addOneUsingRecursion(head);

        // If carry remains after processing all nodes,
        // create a new node at the front
        if (carry > 0) {
            Node newHead = new Node(carry);
            newHead.next = head;
            head = newHead;
        }

        return head;
    }

    private int addOneUsingRecursion(Node current) {

        // Base case:
        // If we have reached past the last node,
        // return carry = 1 (this is where "+1" happens)
        if (current == null) {
            return 1;
        }

        // Recursively process the next node
        int carry = addOneUsingRecursion(current.next);

        // Add carry to current node's data
        int sum = current.data + carry;

        // Update current node's value
        current.data = sum % 10;

        // Return carry for previous node
        return sum / 10;
    }

    // Helper method to add one to the number represented by the linked list
    // The linked list is assumed to be non-empty
    // and the digits are stored in forward order
    // (most significant digit at the head)
    // Time Complexity: O(N) A: Reverse list → O(N) B: Add one → O(N) C: Reverse list again → O(N)
    // Space Complexity: O(1) No extra space used except for a few variables
    
    private Node addOneToLinkedListNumber(Node head) {
        // Step 1: Reverse the linked list
        Node reversedHead = reverseList(head);

        Node current = reversedHead;
        Node tail = null;
        int carry = 1;

        // Step 2: Add one to the reversed number
        while (current != null) {

            int sum = current.data + carry;

            // Update node value and carry
            current.data = sum % 10;
            carry = sum / 10;

            tail = current;
            current = current.next;
        }

        // Step 3: If carry remains, append a new node
        if (carry > 0) {
            tail.next = new Node(carry);
        }

        // Step 4: Reverse the list again to restore original order
        return reverseList(reversedHead);
    }

    private Node reverseList(Node head) {

        // If the list is empty or has only one node,
        // reversing is unnecessary
        if (head == null || head.next == null) {
            return head;
        }

        // 'current' will traverse the list
        Node current = head;

        // 'prev' will hold the reversed part of the list
        Node prev = null;

        // Iterate through the list and reverse links one by one
        while (current != null) {

            // Store next node before breaking the link
            Node next = current.next;

            // Reverse the current node's pointer
            current.next = prev;

            // Move prev one step forward
            prev = current;

            // Move current one step forward using saved next
            current = next;
        }

        // 'prev' will be the new head of the reversed list
        return prev;
    }
}
