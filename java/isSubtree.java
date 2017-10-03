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
       
    public boolean isSubtree(TreeNode s, TreeNode t) 
    {        
        String tree1 = preorder(s, true);
        String tree2 = preorder(t, true);
        
        return tree1.indexOf(tree2) >= 0;        
    }
    
    public String preorder(TreeNode t, boolean left)
    {
        if(t == null)
        {
            if(left)
                return "lnull";
            else
                return "rnull";
        }        
        return "#" + t.val + " " + preorder(t.left, true) + " " + preorder(t.right, true);
    }
}
