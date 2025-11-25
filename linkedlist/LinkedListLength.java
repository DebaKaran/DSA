/**
 * GFG Problem: Length of a linked list
 * URL: https://www.geeksforgeeks.org/length-of-a-linked-list-iterative-and-recursive/
 * 
 */
public class LinkedListLength {
    //Traverses list iteratively in O(n) time and O(1) extra space
    public int getCount(Node head) {
        int result = 0;
        Node temp = head;
        while(temp != null) {
            result++;
            temp = temp.next;
        }
        return result;
    }
}
