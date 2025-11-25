/*
class Node
{
    int data;
    Node next;

    Node(int d)
    {
        this.data = d;
        this.next = null;
    }
}
*/

/**
 * GFG Problem Statement: https://practice.geeksforgeeks.org/problems/delete-a-node-in-single-linked-list/1
 * Time Complexity: O(N) where N is the number of nodes in the linked list.
 * Space Complexity: O(1) as we are using constant space.
 * 
 */
class LinkedListDeleteAtPosition {
    Node deleteNode(Node head, int x) {
        if(head == null || x < 1) {
            return head;
        }
        
        if(x == 1) {
            Node nd = head.next;
            head.next = null;
            head = nd;
            return head;
        }
        
        Node temp = head;
        Node prev = null;
        int pos = x;
        
        while(temp != null && pos > 1) {
            prev = temp;
            temp = temp.next;
            pos--;
        }
        
        
        if(pos == 1) {
            prev.next = temp.next;
            temp.next = null;
        }
        
        return head;
    }
}