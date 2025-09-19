package binarytree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class InfectionTimeInBinaryTree {
    public int amountOfTime(TreeNode root, int start) {
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
    }

    private TreeNode findtargetNode(int start, TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            int n = queue.size();
             for (int i = 0; i < n; i++) {
                TreeNode curr = queue.remove();
                if(curr.val == start) {
                    return curr;
                }

                if(curr.left != null) {
                    queue.add(curr.left);
                }

                if(curr.right != null) {
                    queue.add(curr.right);
                }
             }
        }

        return null;
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
