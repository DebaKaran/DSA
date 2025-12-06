public class BrowserHistory {

    // current page node (doubly-linked)
    private Node curr;

    /** Initialize with homepage as the current page. */
    public BrowserHistory(String homepage) {
        this.curr = new Node(homepage);
    }

    /**
     * Visit a new URL from the current page.
     * This clears any forward history (pages reachable via curr.next).
     * Time complexity: O(1)
     * Space complexity: O(1)
     */
    public void visit(String url) {
        // Drop forward history by severing link to next
        // (the rest of the forward chain becomes unreachable and eligible for GC)
        curr.next = null;

        // Append new node and move current pointer
        Node node = new Node(url);
        curr.next = node;
        node.prev = curr;
        curr = node;
    }

    /**
     * Move back up to 'steps' pages. Stops early at the first page.
     * Returns the current page after moving.
     * Time complexity: O(steps)
     * Space complexity: O(1)
     */
    public String back(int steps) {
        int remaining = steps;
        while (remaining > 0 && curr.prev != null) {
            curr = curr.prev;
            remaining--;
        }
        return curr.data;
    }

    /**
     * Move forward up to 'steps' pages. Stops early at the latest page.
     * Returns the current page after moving.
     * Time complexity: O(steps)
     * Space complexity: O(1)
     */
    public String forward(int steps) {
        int moved = 0;
        while (moved < steps && curr.next != null) {
            curr = curr.next;
            moved++;
        }
        return curr.data;
    }

    /** Doubly-linked node representing a visited page. */
    private static class Node {
        String data;
        Node next;
        Node prev;

        Node(String val) {
            this.data = val;
        }
    }
}
