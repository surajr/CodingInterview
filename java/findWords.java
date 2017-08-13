public class Solution {
    
    // Used String datastructure to store the rows 
    private String [] rows = new String [] {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
    
    public String[] findWords(String[] words) {   
        
        ArrayList<String> findWord = new ArrayList<>();
        int rowNumber = -1;
        
        for(String word : words)
        {
            char [] charWord = word.toCharArray();            
            rowNumber = findRowNumber(charWord[0]);            
            for(int idx = 1; idx<charWord.length; idx++)
            {
                // If the rows are different, then update rowNumber as -1
                if(rowNumber != findRowNumber(charWord[idx]))
                {
                    rowNumber = -1;
                    break;
                }                    
            }                  
            
            if(rowNumber != -1)
                findWord.add(new String(charWord));                
        }        
        return findWord.toArray(new String[findWord.size()]);       
    }
    
    public int findRowNumber(char c)
    {
        for(int num=0; num<3; num++)
        {            //Identify the row number
            if(rows[num].indexOf(Character.toUpperCase(c)) != -1)
                return num;
        }
        return -1;
    }
}
