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
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        helper(root, null);
        return max;
    }
    
    public int helper(TreeNode root, Integer value)
    {
        if(root == null)
            return 0;
        int left = helper(root.left, root.val);
        int right = helper(root.right, root.val);
        max = Math.max(max, left+right);
        if(value == null || value != root.val) return 0;
        
        return Math.max(left, right)+1;
    }
}
