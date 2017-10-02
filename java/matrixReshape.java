// Using Queue - Extra space

public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        
        int m = nums.length, n = nums[0].length;
        
        if(m * n != r * c) return nums;
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<nums.length; i++)
            for(int j=0; j<nums[0].length; j++)
                queue.offer(nums[i][j]);
        
        int [][] result = new int[r][c];
        
        for(int i=0; i<r; i++)
            for(int j=0; j<c; j++)
                result[i][j] = queue.poll();
        
        return result;
        
    }
}

// Without using extra space

public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        
        int m = nums.length, n = nums[0].length, row = 0, col = 0;
        
        if(m * n != r * c) return nums;
        
        int [][] result = new int[r][c];
        
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                result[row][col] = nums[i][j];
                col++;
                if(col == c)
                {
                    col = 0;
                    row++;
                }
            }
        }       
        return result;        
    }
}
