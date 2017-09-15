/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public String tree2str(TreeNode t) {
        
        if(t == null)
            return "";
        
        String left = tree2str(t.left);
        String right = tree2str(t.right);
        
        if(left == "" && right == "") return t.val + "";
        
        return t.val + "(" + left + ")" + (right == "" ? "" : "(" + right + ")") ;
        
    }
}
