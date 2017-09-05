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
    public boolean checkEqualTree(TreeNode root) {
        
        if(root == null)
            return false;
        
        int sum = checkSum(root);
        
        if(sum %2 != 0)
            return false;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty())
        {
            TreeNode node = stack.pop();
            
            if(node.left != null)
            {
                int leftSum = checkSum(node.left);           
                if(leftSum == sum - leftSum)
                    return true;
                stack.push(node.left);
            }
            
            if(node.right != null)
            {
                int rightSum = checkSum(node.right);
                if(rightSum == sum - rightSum)
                    return true;
                
                stack.push(node.right);
            }       
            
        }
        return false;
        
    }
    
    public int checkSum(TreeNode root)
    {
        if(root == null)
            return 0;
        
        return root.val + checkSum(root.left) + checkSum(root.right);
    }
  
}
