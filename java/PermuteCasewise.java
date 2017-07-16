/*
 * Facebook interview question
 * 
 * Problem: Permute the String including case
 * 
 * For example: "abc" = > abc, ABC, Abc, aBc, abC, ABc, abC, AbC 
 * 
 * Solution: If N be the string length of input then there exists 2^N maximum combinations
 * 
 * Represent this as a bitwise operation of size input.length() which is 3
 * 
 * 0 0 0 => 0
 * 1 0 0 => Convert 0th char to upper case
 * 1 1 0 => Convert 0, 1 char to upper case and so on
 * 
 */


public class PermuteCasewise 
{
	public void generatePermute(String input)
	{		
		int max = 1 << input.length();
				
		for(int i=0; i<max; i++)
		{
			input = input.toLowerCase();
			char [] combination = input.toCharArray();
			
			for(int j=0; j<input.length(); j++)
			{
				if( ((i >> j) & 1) == 1 )
					combination[j] = Character.toUpperCase(input.charAt(j));						
			}			
			System.out.println(String.valueOf(combination));			
		}		
	}

	public static void main(String[] args) 
	{		
		PermuteCasewise obj = new PermuteCasewise();
		obj.generatePermute("aBc");		
	}
}
