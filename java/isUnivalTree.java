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
    /*
    Recursion: Do a DFS recursively 
    Time & Space: O(n)
    
    */
    
    public boolean isUnivalTree(TreeNode root) {
        if(root == null)
            return true;
        
        return dfs(root, root.val);
        
    }
    
    public boolean dfs(TreeNode node, int val){
        if(node == null)
            return true;
        if(node.val != val)
            return false;
        return dfs(node.left, val) && dfs(node.right, val);
    }


/*

Approach 2: Iteratively 
Time and Space: O(n)
*/

    public boolean isUnivalTree(TreeNode root) {
        if(root == null)
            return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            TreeNode node = queue.remove();
            
            if(root.val != node.val) 
                return false;
            
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
            
        }
        
        return true;
    }
}




