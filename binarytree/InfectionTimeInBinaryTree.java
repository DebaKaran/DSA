package binarytree;

import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class InfectionTimeInBinaryTree {

    // 1: Time Complexity

    // findParentNode(root) → BFS through all nodes → O(n)

    // findTargetNode(start, root) → BFS through all nodes (in worst case) → O(n)

    // Main BFS (infection spread) → Visits each node once → O(n)

    // Total Time Complexity = O(n)

    // Space Complexity

    // Parent map → stores up to n entries → O(n)

    // Visited set → stores up to n nodes → O(n)

    // Queue → in worst case stores ~n/2 nodes (a level in BFS) → O(n)

    // Total Space Complexity = O(n)
    /**public int amountOfTime(TreeNode root, int start) {
        int time = 0;

        // Step 1: Build a mapping from child -> parent to allow upward traversal
        Map<TreeNode, TreeNode> parents = findParentNode(root);

        // Keep track of visited nodes to avoid cycles
        Set<TreeNode> visited = new HashSet<>();


        // BFS queue starting from the target node
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode target = findtargetNode(start, root);
        queue.add(target);

        // Step 2: BFS until we reach all the nodes
        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.remove();

                // Explore parent
                TreeNode parentNode = parents.get(curr);
                if (parentNode != null && !visited.contains(parentNode)) {
                    queue.add(parentNode);
                }

                // Explore left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.add(curr.left);
                }

                // Explore right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.add(curr.right);
                }

                // Mark current node as visited
                visited.add(curr);
            }
            time++;
        }

        return time - 1;
    } */

    public int amountOfTime(TreeNode root, int start) {
        // Step 1: Build parent mapping
        Map<TreeNode, TreeNode> parents = findParentNode(root);

        // Step 2: Find target node (start of infection)
        TreeNode target = findTargetNode(start, root);

        // BFS setup
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);

        int time = 0;

        // BFS infection spread
        while (!queue.isEmpty()) {
            int n = queue.size();
            boolean spread = false; // Track if infection spreads further

            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.remove();

                // Explore neighbors (parent, left, right)
                TreeNode parentNode = parents.get(curr);
                if (parentNode != null && !visited.contains(parentNode)) {
                    queue.add(parentNode);
                    visited.add(parentNode);
                    spread = true;
                }

                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.add(curr.left);
                    visited.add(curr.left);
                    spread = true;
                }

                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.add(curr.right);
                    visited.add(curr.right);
                    spread = true;
                }
            }

            // Only increment if new nodes got infected this round
            if (spread) time++;
        }

        return time;
    }

    private TreeNode findTargetNode(int start, TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr.val == start) {
                return curr;
            }
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        return null;
    }

    private Map<TreeNode, TreeNode> findParentNode(TreeNode curr) {
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);
        parents.put(curr, null);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();

            if (node.left != null) {
                queue.add(node.left);
                parents.put(node.left, node);
            }

            if (node.right != null) {
                queue.add(node.right);
                parents.put(node.right, node);
            }
        }
        return parents;
    }
}
