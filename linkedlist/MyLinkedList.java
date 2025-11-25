/**
 * LeetCode Problem 707: Design Linked List
 * URL: https://leetcode.com/problems/design-linked-list
 * 
 * | Method          | Time Complexity | Space Complexity |
| --------------- | --------------- | ---------------- |
| `get`           | **O(n)**        | **O(1)**         |
| `addAtHead`     | **O(1)**        | **O(1)**         |
| `addAtTail`     | **O(n)**        | **O(1)**         |
| `addAtIndex`    | **O(n)**        | **O(1)**         |
| `deleteAtIndex` | **O(n)**        | **O(1)**         |

 */
public class MyLinkedList {

    Node head;
    int length;

    public MyLinkedList() {
        this.head = null;
        this.length = 0;
    }
    
    public int get(int index) {
        if(index < 0 || index >= length) return -1;
        Node temp = head;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.val;
    }
    
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        length++; 
    }
    
    public void addAtTail(int val) {
        if(length == 0) {
            addAtHead(val);
            return;
        }
        Node node = new Node(val);
        Node temp = head;
        for(int i = 0; i < length - 1; i++) {
            temp = temp.next;
        }
        temp.next = node;
        length++;
    }
    
    public void addAtIndex(int index, int val) {
        if(index < 0 || index > length) return;

        if(index == 0) {
            addAtHead(val);
            return;
        }

        if(index == length) {
            addAtTail(val);
            return;
        }

        Node node = new Node(val);
        Node temp = head;
        for(int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        Node temp2 = temp.next;
        temp.next = node;
        node.next = temp2;
        length++;
    }
    
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= length) return;

         if(index == 0) {
           deleteFromHead();
           return;
         } else {
            Node temp = head;
            for(int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }

            if(index == length - 1) {
                temp.next = null;
            } else {
                temp.next = temp.next.next;
            }

         }

        length--;
    }

    private void deleteFromHead() {
            if(length == 1) {
                head = null;
            } else {
                Node node = head.next;
                head.next = null;
                head = node;
            }
            length--;
    }
}

class Node {
    Node next;
    int val;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }
}