package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//LeetCode problem 199

class RightSideViewBinaryTree {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        //rightSideViewUsingBFS(root, result);

        rightSideViewUsingDFS(root, 0, result);
        return result;
    }

    //Deapth (Instead of left -> right , we will adopt right -> left) Traversal 
    //Time Complexity O(n)
    //Space Complexity O(h) h is height of tree

    private void rightSideViewUsingDFS(TreeNode node, int depth, List<Integer> result) {
        //Base case
        if(node == null) return;

        if(depth == result.size()) {
            result.add(node.val);
        }

        //recursive
        rightSideViewUsingDFS(node.right, depth + 1, result);
        rightSideViewUsingDFS(node.left, depth + 1, result);
    }

    //LevelOrder Traversal 
    //Time Complexity O(n)
    //Space Complexity O(w) w is width of tree

    private void rightSideViewUsingBFS(TreeNode root, List<Integer> result) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int n = queue.size();
            
            for(int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                
                if(i == n - 1) {
                    result.add(node.val);
                }
            }
        }
    }
}