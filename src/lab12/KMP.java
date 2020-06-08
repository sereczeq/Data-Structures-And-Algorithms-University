package lab12;

import java.util.LinkedList;

public class KMP implements IStringMatcher
{
	
	@Override
	public LinkedList<Integer> validShifts(String pattern, String text)
	{
		
		LinkedList<Integer> shifts = new LinkedList<Integer>();
		char[] P = pattern.toCharArray();
		char[] T = text.toCharArray();
		int patternLength = pattern.length() - 1; // -1 because it helps
		int[] pi = computePrefixFunction(P);
		int q = 0;
		for (int i = 0; i < T.length; i++)
		{
			while (q > 0 && P[q] != T[i]) q = pi[q - 1];
			if (P[q] == T[i])
			{
				q++;
				
				if (q == patternLength + 1)
				{
					shifts.add(i - patternLength);
					q = pi[q - 1];
				}
			}
		}
		return shifts;
		
	}
	
	
	private int[] computePrefixFunction(char[] P)
	{
		
		int[] pi = new int[P.length];
		for (int q = 1, k = 0; q < P.length - 1; q++) // idk why it starts from 2
		{
			k = pi[q - 1]; // added this
			while (k > 0 && P[k] != P[q])
			{
				k = pi[k - 1];
			}
			if (P[k] == P[q])
			{
				k = k + 1;
			}
			pi[q] = k;
		}
		return pi;
		
	}
	
}
