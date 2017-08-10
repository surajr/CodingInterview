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
    public boolean findTarget(TreeNode root, int k) {
        
        //Binary Search on Binary Search Tree. Return True if present       
        return dfs(root, k);        
    }
    
    public boolean dfs(TreeNode root, int k)
    {
        if(root == null) 
            return false;
        
        else if(root.val == k)
            return true;
        
        else
            return dfs(root)
        
    }
    
}

// Solution 2

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
    public boolean findTarget(TreeNode root, int k) {
        
        //Create a HashSet for each of the node value. Return TRUE if k - root.val is present in the set
        
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);        
    }
    
    public boolean dfs(TreeNode root, HashSet<Integer> set, int k)
    {
        if(root == null) 
            return false;
        
        if(set.contains(k-root.val))
            return true;
        else
            set.add(root.val);
        
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}
