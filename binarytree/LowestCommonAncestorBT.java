package binarytree;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

//LeetCode 236
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

public class LowestCommonAncestorBT {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLca(root, p, q);
    }

   //1: Time Complexity

    // findPath visits nodes until it finds the target → O(n) in the worst case.

    //You call it twice (p and q), so total = O(2n) ≈ O(n).

    // Comparing both paths = O(min(h1, h2)) where h1 and h2 are path lengths ≤ height of tree h.

    //Overall: O(n).

    // 2: Space Complexity

    // pathP and pathQ each store up to O(h) nodes.

    // Recursion stack in findPath also O(h).

    // Overall: O(h), where h = height of tree.
    
    private TreeNode findLca(TreeNode node, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>(); 

        findNode(node, p, pathP);
        findNode(node, q, pathQ);

        TreeNode lca = null;

      int minLen = Math.min(pathP.size(), pathQ.size());

        for(int i = 0; i < minLen; i++) {
            if(pathP.get(i) == pathQ.get(i)) {
                lca = pathP.get(i);
            } else {
                break;
            }
        }
        return lca;
    }
    //Time Complexity: O(n)
    //Space Complexity: O(h) where h is the height of the tree

    private boolean findNode(TreeNode curr, TreeNode target, List<TreeNode> path) {
        if(curr == null) {
            return false;
        }

        path.add(curr);

        if(curr == target) {
            return true;
        }

       if(findNode(curr.left, target, path) || findNode(curr.right, target, path)) {
            return true;
       }

       path.remove(path.size() - 1);
       return false;
    }
}
