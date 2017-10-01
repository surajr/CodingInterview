/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        
        if(root == null)
            return true;
        
        return getHeight(root) != -1;
        
    }
    
    public int getHeight(TreeNode root)
    {
        if(root == null)
            return -1;
        
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        
        if(l == -1 || r == -1 || Math.abs(l-r) > 1)
            return -1;
        
        return 1 + Math.max(l,r);
    }
}
