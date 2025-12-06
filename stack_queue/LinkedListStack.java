package stack_queue;

// Singly Linked List node
class Node {
    int data;
    Node next;

    Node(int value) {
        this.data = value;
        this.next = null;
    }
}

// Stack implementation using Linked List
//GFG Problem: Implement a Stack using Linked List

class LinkedListStack {

    private int size;   // Number of elements currently in stack
    private Node top;   // Top of the stack (head of linked list)

    public LinkedListStack() {
        this.size = 0;
        this.top = null;
    }

    //Time complexity: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    //Time complexity: O(1)
    public void push(int value) {
        // Insert new node at the beginning (LIFO)
        Node newNode = new Node(value);
        newNode.next = top; // Link new node to current top
        top = newNode;      // Update top to new node
        size++;
    }
    //Time complexity: O(1)
    public void pop() {
        // Remove the top element
        if (isEmpty()) return;

        Node nextNode = top.next; // Save next node
        top.next = null;          // Help GC
        top = nextNode;           // Move top down
        size--;
    }

    //Time complexity: O(1)
    public int peek() {
        // Returns top element without removing
        return isEmpty() ? -1 : top.data;
    }

    //Time complexity: O(1)
    public int size() {
        // Get current number of elements
        return size;
    }
}

