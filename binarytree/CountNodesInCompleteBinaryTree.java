package binarytree;

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
}
