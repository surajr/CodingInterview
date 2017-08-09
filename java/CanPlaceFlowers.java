public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        
        for(int idx=0; idx<flowerbed.length && n > 0; idx++)
        {
            if(flowerbed[idx]==0  && (idx == 0 || flowerbed[idx-1]==0) && (idx == flowerbed.length-1 || flowerbed[idx+1]==0))
            {
                n--; 
                flowerbed[idx]=1;
                idx++;
            }
            
        }
        return (n == 0);
        
    }
}
