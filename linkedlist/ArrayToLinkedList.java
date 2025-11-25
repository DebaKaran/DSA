/**
 * GFG Problem: Convert an array to a linked list
 * URL: https://www.geeksforgeeks.org/convert-an-array-to-a-linked-list
 * 
 */

    /*
// Representation of a node
class Node {
    int data;
    Node next;
    Node (int d) {
       data = d;
       next = null;
    }
};
*/
public class ArrayToLinkedList {
    public Node arrayToList(int arr[]) {
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i = 1; i < n; i++) {
            Node node = new Node(arr[i]);
            temp.next = node;
            temp = node;
        }
        
        return head;
        
    }
}

