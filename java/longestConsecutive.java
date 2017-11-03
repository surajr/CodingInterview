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
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null)
            return 0;
        dfs(root, 0, root.val);
        return max;
    }
    
    public void dfs(TreeNode node, int cur, int target)
    {
        if(node == null) return;
        if(node.val == target) cur++;
        else cur = 1;
        max = Math.max(max, cur);
        dfs(node.left, cur, node.val+1);
        dfs(node.right, cur, node.val+1);
    }
}
