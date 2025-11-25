/**
 * GFG Problem: Search an element in a linked list
 * URL: https://www.geeksforgeeks.org/search-an-element-in-a-linked-list/
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LinkedListSearch {
    public boolean searchKey(Node head, int key) {
        Node temp = head;
        
        while(temp != null) {
            if(temp.data == key) {
                return true;
            }
            temp = temp.next;
        }
        return false;
        
    }
}
