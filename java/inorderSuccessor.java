/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }

root.val > p.val. In this case, root can be a possible answer, so we store the root node first and call it res. However, we don't know if there is anymore node on root's left that is larger than p.val. So we move root to its left and check again.

root.val <= p.val. In this case, root cannot be p's inorder successor, neither can root's left child. So we only need to consider root's right child, thus we move root to its right and check again.


 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode result = null;
        
        while(root != null)
        {
            if(root.val > p.val)
            {
                result = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return result;
    }
}
