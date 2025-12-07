package stack_queue.implemenations;

// Node class for singly linked list
class Node {
    int data;
    Node next;

    Node(int value) {
        this.data = value;
        this.next = null;
    }
}

// Queue implementation using linked list
//GFG Problem Link: https://practice.geeksforgeeks.org/problems/implement-queue-using-linked-list/1
class LinkedListQueue {

    private Node front;  // Points to first element (head)
    private Node rear;   // Points to last element (tail)
    private int size;    // Number of elements in queue

    public LinkedListQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    //Time Complexity: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    //Time Complexity: O(1)
    public void enqueue(int value) {
        // Insert element at the rear
        Node newNode = new Node(value);

        if (isEmpty()) {
            front = newNode;   // First element becomes both front and rear
        } else {
            rear.next = newNode; // Attach new node at end
        }

        rear = newNode;  // Update rear pointer
        size++;
    }

    //Time Complexity: O(1)
    public void dequeue() {
        // Remove element from the front
        if (isEmpty()) return;

        if (size == 1) {
            // Queue becomes empty
            front = null;
            rear = null;
        } else {
            Node nextNode = front.next; // Hold reference before disconnecting
            front.next = null;          // Help GC
            front = nextNode;           // Move front forward
        }

        size--;
    }

    //Time Complexity: O(1)
    public int front() {
        // Return front element; -1 if empty
        return isEmpty() ? -1 : front.data;
    }

    //Time Complexity: O(1)
    public int size() {
        return size;
    }
}
