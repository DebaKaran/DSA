package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversalBinaryTree {

    public List<List<Integer>> levelOrder(TreeNode root) {
        return levelOrderTraversal(root);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public List<List<Integer>> levelOrderTraversal(TreeNode node) {
        List<List<Integer>> result = new ArrayList<>();
        if(node == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            List<Integer>  ds = new ArrayList<>();
            int n = queue.size();

            for(int i = 0; i < n; i++) {
                TreeNode temp = queue.remove();
                ds.add(temp.val);
                if(temp.left != null) {
                    queue.add(temp.left);
                }
                if(temp.right != null) {
                    queue.add(temp.right);
                }
            }
            result.add(ds);
        }
        return result;
    }
}
