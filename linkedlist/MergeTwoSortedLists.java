/**
 * Leetcode Problem 21: Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * 
 */
public class MergeTwoSortedLists {

    /**
     * 
     *
     * @param list1 head of the first sorted list
     * @param list2 head of the second sorted list
     * @return head of the merged sorted list
     */

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

    // If both lists are null → merged list is null
    if(list1 == null && list2 == null) return null;

    // If list1 is empty → result is list2
    if(list1 == null) return list2;

    // If list2 is empty → result is list1
    if(list2 == null) return list1;

    return mergeTwoListsUsingExtraSpace(list1, list2);
}

// Merging two sorted linked lists using extra space for new nodes
// Time Complexity: O(n + m) where n and m are lengths of list1 and list2
// Space Complexity: O(n + m) for the new nodes created

private ListNode mergeTwoListsUsingExtraSpace(ListNode list1, ListNode list2) {

    ListNode temp1 = list1;   // pointer for list1
    ListNode temp2 = list2;   // pointer for list2

    ListNode result = new ListNode();  // dummy head (value unused)
    ListNode temp = result;            // tail pointer for result list

    // Merge until one list runs out
    while(temp1 != null && temp2 != null) {

        int val1 = temp1.val;
        int val2 = temp2.val;

        // Always pick smaller node value and create a NEW node for result
        ListNode node = new ListNode(val1 <= val2 ? val1 : val2);

        temp.next = node;    // attach new node to result
        temp = node;         // advance result tail pointer

        // Move ahead in the list from which the node was picked
        if(val1 <= val2) {
            temp1 = temp1.next;
        } else {
            temp2 = temp2.next;
        }
    }

    // If list1 still has nodes, append them
    if(temp1 != null) {
        temp.next = temp1;
    } 
    // Else append remaining nodes from list2
    else {
        temp.next = temp2;
    }

    return result.next;   // result.next skips dummy head
}

}
