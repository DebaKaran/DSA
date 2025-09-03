package binarytree;

import java.util.LinkedList;
import java.util.Queue;

//LeetCode 104
//https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

public class MaxDepthBinaryTree {

    public int maxDepth(TreeNode root) {
        //Using Recursion
        //return maxDepthR(root);

        return maxDepthLevelOrder(root);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(h) where h is the height of the tree

    private int maxDepthR(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftDepth = maxDepthR(node.left);
        int rightDepth = maxDepthR(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n) in worst case when the tree is skewed

    private static int maxDepthLevelOrder(TreeNode node) {
        if(node == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 0;
        int size = 0;

        while(!queue.isEmpty()) {
            depth++;
            size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode nd = queue.poll();
                if(nd.left != null) {
                    queue.offer(nd.left);
                }

                if(nd.right != null) {
                    queue.offer(nd.right);
                }
            }
        }
        return depth;
    }

}
