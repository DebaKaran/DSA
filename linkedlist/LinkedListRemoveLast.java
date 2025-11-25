/*
class Node
{
    int data;
    Node next;

    Node(int data)
    {
        this.data = data;
        this.next = next;
    }
}
*/

/**
 * GFG Problem Statement: https://practice.geeksforgeeks.org/problems/remove-last-node-of-linked-list/1
 * Remove Last Node of Linked List  
 * Given a singly linked list, remove the last node of the linked list.
 * The task is to complete the function removeLastNode() which takes head of the linked list as input parameter and returns the head of the modified linked list.   
 * If the linked list is empty or has only one node, then it should return null.
 * 
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
class LinkedListRemoveLast {
    public Node removeLastNode(Node head) {
        if(head == null || head.next == null) {
            return null;
        }
        
        Node temp = head;
        Node prev = null;
        
        while(temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        return head;
    }
}
