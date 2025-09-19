package binarytree;

import java.util.*;

// LeetCode 863: All Nodes Distance K in Binary Tree
public class AllNodesDistanceK {
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();

        // Step 1: Build a mapping from child -> parent to allow upward traversal
        Map<TreeNode, TreeNode> parents = findParentNode(root);

        // Keep track of visited nodes to avoid cycles
        Set<TreeNode> visited = new HashSet<>();

        // Distance counter
        int dist = 0;

        // BFS queue starting from the target node
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);

        // Step 2: BFS until we reach distance k
        while (dist != k && !queue.isEmpty()) {
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
            dist++;
        }

        // Step 3: All nodes left in the queue are exactly k distance away
        while (!queue.isEmpty()) {
            result.add(queue.remove().val);
        }

        return result;
    }

    // Helper: Builds a map of each node -> its parent using BFS
    //Time Complexity: O(n)
    //Space Complexity: O(n)

    private Map<TreeNode, TreeNode> findParentNode(TreeNode curr) {
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);

        // Root has no parent
        parents.put(curr, null);

        // BFS traversal to assign parent relationships
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

