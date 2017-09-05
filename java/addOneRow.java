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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        
        if(d == 1)
        {
            TreeNode result = new TreeNode(v);
            result.left = root;
            return result;
        }
        
        dfs(root, 1, v, d);
        return root;        
    }
    
    public void dfs(TreeNode root, int depth, int v, int d)
    {
        if(root == null)
            return;
        
        if(depth < d-1)
        {
            dfs(root.left, depth+1, v, d);
            dfs(root.right, depth+1, v, d);
        }
        else
        {
            TreeNode result = root.left;
            root.left = new TreeNode(v);
            root.left.left = result;
            result = root.right;
            root.right = new TreeNode(v);
            root.right.right = result;
            
            
        }
    }
}
