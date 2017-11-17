ass Solution {
    public String convert(String s, int numRows) {
        char [] c = s.toCharArray();
        StringBuilder [] sb = new StringBuilder[numRows];
        
        for(int i=0; i<numRows; i++)
            sb[i] = new StringBuilder();
        
        int i=0;
        while(i < c.length)
        {
            for(int j=0; j<numRows && i<c.length; j++)
                sb[j].append(c[i++]);
            for(int j=numRows-2; j>=1 && i<c.length; j--)
                sb[j].append(c[i++]);
        }
        
        for(int j=1; j<numRows; j++)
            sb[0].append(sb[j]);
        
        return sb[0].toString();
    }
}
