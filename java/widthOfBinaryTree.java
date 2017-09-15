/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/*
    use a travel traversal template, use another queue to keep the index of each level nodes. left node index = this node index * 2, right =     this node index*2 + 1. the width should be the last node index - first node index + 1

*/
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        Queue<Integer> count = new ArrayDeque<>();
        int max = 1;
        
        queue.offer(root);
        count.offer(1);
        
        while(!queue.isEmpty())
        {
            int size = queue.size();            
            int left = 0;
            int right = 0;
            
            for(int i=0; i<size; i++)
            {
                TreeNode node = queue.poll();
                int index = count.poll();
                if(i==0) left = index;
                if(i==size-1) right = index;
                
                if(node.left != null)
                {
                    queue.offer(node.left);
                    count.offer(index*2);
                }
                
                if(node.right != null)
                {
                    queue.offer(node.right);
                    count.offer(index*2+1);
                }
            }
            
            max = Math.max(max, right - left+1);
        }
        
        return max;
    }
}
