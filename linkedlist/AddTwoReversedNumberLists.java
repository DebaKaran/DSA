/**
 * LeetCode Problem: Add Two Numbers 
 * https://leetcode.com/problems/add-two-numbers/description/
 * 
 */
public class AddTwoReversedNumberLists {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //without dummy node
        return addTwoNumbersWithoutDummyNode(l1, l2);
    }

     // Adds two numbers represented by reversed linked lists
    // without using a dummy head node.
    //Time Complexity: O(max(m, n)), Space Complexity: O(max(m, n))
    
     private ListNode addTwoNumbersWithoutDummyNode(ListNode l1, ListNode l2) {
        // Pointers to traverse the two input lists
        ListNode curr1 = l1;
        ListNode curr2 = l2;

        // 'result' will act as the first node and then as the tail pointer
        // Initially it's a default node; we'll set its value when we process
        // the first digit.
        ListNode result = new ListNode();

        // 'head' will point to the head of the result list
        ListNode head = null;

        // remainder: digit to store in the current node (sum % 10)
        // carryForwardVal: carry from the previous digit (sum / 10)
        int remainder = 0;
        int carryForwardVal = 0;

        // Step 1: process nodes while both lists have digits
        while (curr1 != null && curr2 != null) {
            int currVal = curr1.val + curr2.val + carryForwardVal;

            remainder = currVal % 10;
            carryForwardVal = currVal / 10;

            // move forward in both lists
            curr1 = curr1.next;
            curr2 = curr2.next;

            // For the very first digit, initialize head and use the
            // pre-created 'result' node
            if (head == null) {
                result.val = remainder;
                head = result;
                continue;
            }

            // After the first digit, always create a new node and link it
            ListNode temp = new ListNode(remainder);
            result.next = temp;
            result = temp; // move tail pointer
        }

        // Step 2: if l1 is longer, process remaining digits of l1
        while (curr1 != null) {
            int currVal = curr1.val + carryForwardVal;

            remainder = currVal % 10;
            carryForwardVal = currVal / 10;

            curr1 = curr1.next;

            ListNode temp = new ListNode(remainder);
            result.next = temp;
            result = temp;
        }

        // Step 3: if l2 is longer, process remaining digits of l2
        while (curr2 != null) {
            int currVal = curr2.val + carryForwardVal;

            remainder = currVal % 10;
            carryForwardVal = currVal / 10;

            curr2 = curr2.next;

            ListNode temp = new ListNode(remainder);
            result.next = temp;
            result = temp;
        }

        // Step 4: if there is still a carry left, append it as a new node
        if (carryForwardVal > 0) {
            ListNode temp = new ListNode(carryForwardVal);
            result.next = temp;
        }

        // Return the head of the resulting list
        return head;
    }
}
