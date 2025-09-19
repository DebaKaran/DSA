package binarytree;

import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;

//Leetcode 222
// https://leetcode.com/problems/count-complete-tree-nodes/

public class CountNodesInCompleteBinaryTree {
    // BFS approach
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int countNodes(TreeNode root) {
        int count = 0;
        if(root == null) return count;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int n = queue.size();
            for(int i = 0; i < n; i++) {
                count++;
                TreeNode curr = queue.remove();
                if(curr.left != null) {
                    queue.add(curr.left);
                }

                if(curr.right != null) {
                    queue.add(curr.right);
                }
            }
        }

        return count;
    }

    // 1: Time Complexity: O(log² n)

    // At each recursion, you compute heights in O(log n)

    // Recursion depth is O(log n)

    // Total = O(log n × log n) = O(log² n)

    // 2: Space Complexity: O(log n) (recursion stack in worst case).
    
    public int countNodes1(TreeNode root) {
        if(root == null) return 0;

         int leftHeight = getLeftHeight(root);
         int rightHeight = getRightHeight(root);
         
         // If left and right heights are same, it's a perfect binary tree
         if(leftHeight == rightHeight) {
            // 2^h - 1
            return (1 << leftHeight) - 1;
         }

         // Otherwise, recurse

         return 1 + countNodes1(root.left) + countNodes1(root.right);
        
    }

    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while(node != null) {
            height++;
            node = node.left;
        }

        return height;
    }

    private int getRightHeight(TreeNode node) {
        int height = 0;
        while(node != null) {
            height++;
            node = node.right;
        }

        return height;
    }
}
