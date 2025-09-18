package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

//LeetCode 662
//https://leetcode.com/problems/maximum-width-of-binary-tree/description/

public class MaxWidthBT {
    public int widthOfBinaryTree(TreeNode root) {
        return maxWidthOfBT(root);
    }

    //Optimal Approach: Level Order Traversal
    //Time Complexity: O(n)
    //Space Complexity: O(w) where w is the maximum width of the tree
    
    private int maxWidthOfBT(TreeNode node) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.addFirst(new Pair(node, 0));

        int maxWidth = 0;

        while(!queue.isEmpty()) {
            int n = queue.size();
            int length = (int)(queue.getLast().index - queue.getFirst().index + 1);
            maxWidth = Math.max(maxWidth, length);

            for(int i = 0; i < n; i++) {
                Pair currPair = queue.removeFirst();
                long index = currPair.index;

                if(currPair.node.left != null) {
                    queue.addLast(new Pair(currPair.node.left, 2 * index + 1));
                }

                if(currPair.node.right != null) {
                    queue.addLast(new Pair(currPair.node.right, 2 * index + 2));
                }
            }
        }

        return maxWidth;
    }

    class Pair {
        TreeNode node;
        long index;

        public Pair(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }
}
