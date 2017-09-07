/*
Idea is to Split the string on "+" and replace i with NULL. So we have four different integers.
Perform Multiplication individually
Multiple by -1 for i * i
return the result

*/

class Solution {
    public String complexNumberMultiply(String a, String b) {
        
        
        String [] A = a.split("\\+");
        String [] B = b.split("\\+");
        
        int a1 = Integer.parseInt(A[0]);
        int b1 = Integer.parseInt(A[1].replace("i",""));
        
        int a2 = Integer.parseInt(B[0]);
        int b2 = Integer.parseInt(B[1].replace("i",""));
        
        int a1a2 = a1 * a2;
        int b1b2 = b1 * b2;
        int a1a2b1b2 = (a1 * b2) + (b1 * a2);
        
        String afinal = (a1a2 + (-1 * b1b2))+"";
        String bfinal = (a1a2b1b2)+"i";
        
        String result = afinal + "+" + bfinal;
        return result;
        
        
    }
}
