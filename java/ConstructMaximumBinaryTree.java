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
    public TreeNode constructMaximumBinaryTree(int[] nums) 
    {
        //Recursive helper function
        return helper(nums,0,nums.length-1);    
    }
    
    public TreeNode helper(int [] nums, int low, int high)
    {
        if(high < low)
            return null;
        
        //Identify the maximum element's index
        int maxIndex = getMaxIndex(nums, low, high);        
        
        //Construct the root node
        TreeNode root = new TreeNode(nums[maxIndex]);
        
        //Generate the left sub tree from the left part of sub array
        root.left = helper(nums,low, maxIndex-1);
        
        //Generate the right sub tree from the right part of sub array
        root.right = helper(nums, maxIndex+1, high);
        
        //Return the root
        return root;
    }
    
    public int getMaxIndex(int [] nums, int low, int high)
    {
        //Identify the maximum element's index to construct the root element
        int maxIndex = low;
        for(int i = low+1; i <= high; i++)
        {
            if(nums[i] > nums[maxIndex])
                maxIndex = i;
        }
        return maxIndex;
    }
}
