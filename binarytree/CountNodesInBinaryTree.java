package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class CountNodesInBinaryTree {
    public int countNodes(TreeNode root) {
        return countNodesUsingBFS(root);
}

    private  int countNodesUsingBFS(TreeNode root) {
        if (root == null) return 0;  // important base case

        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            count++;

            if (curr.left != null) {
                queue.add(curr.left);
            }

            if (curr.right != null) {
                queue.add(curr.right);
            }
        }

        return count;
    }

}
