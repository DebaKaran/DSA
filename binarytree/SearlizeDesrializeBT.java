package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class SearlizeDesrializeBT {
    // Encodes a tree to a single string.

    //I am going to use level order traversal
    //Incase of null, i will be using #

    // We perform a level-order traversal (BFS).

    // Each node is enqueued and dequeued exactly once.

    // For every node, we do constant work (append, add children).

    // Even null children are processed once.

    // 👉 Time Complexity = O(n)
    // (where n = number of nodes in the tree)

    // 👉 Space Complexity = O(n)

    // Queue<TreeNode> can hold up to O(n) nodes in worst case (complete binary tree).

    // StringBuilder also stores O(n) characters.

    public String serialize(TreeNode root) {
        if(root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if(curr != null) {
                queue.add(curr.left);
                queue.add(curr.right);
                sb.append(curr.val);
            } else {
                sb.append("#");
            }
            sb.append(",");
        }
         
        // remove the trailing comma
         sb.deleteCharAt(sb.length() - 1);
         return sb.toString();
    }

    // Decodes your encoded data to tree.
    //We rebuild the tree from the serialized string.

    // We split the string → O(n) (length of string proportional to nodes).

    // Each node is processed once when dequeued.

    // Constant work per node (create children if not #).

    // Time Complexity = O(n)
    // Space Complexity = O(n)

    // Queue<TreeNode> again can store O(n) nodes.

    // The parts[] array after split also holds O(n) entries.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        String[] parts = data.split(",");

        int i = 0;
        String part = parts[i++];
    
        TreeNode root = new TreeNode(Integer.parseInt(part));
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            String s1 = parts[i++];
            String s2 = parts[i++];

             // Left child
            if(!s1.equals("#")) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(s1));
                curr.left = leftNode;
                queue.add(leftNode);
            }

            // Right child
            if(!s2.equals("#")) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(s2));
                curr.right = rightNode;
                queue.add(rightNode);
            }
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));