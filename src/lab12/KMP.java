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
		for (int i = 1; i < T.length; i++)
		{
			while (q > 0 && P[q + 1] != T[i]) q = pi[q];
			if (P[q + 1] == T[i]) q++;
			if (q == patternLength)
			{
				shifts.add(i - patternLength);
				q = pi[q];
			}
		}
		return shifts;
		
	}
	
	
	private int[] computePrefixFunction(char[] P)
	{
		
		int[] pi = new int[P.length];
		int k = 0;
		for (int q = 2; q < P.length; q++) // idk why it starts from 2
		{
			while (k > 0 && P[k + 1] != P[q])
			{
				k = pi[k];
			}
			if (P[k + 1] == P[q])
			{
				k = k + 1;
			}
			pi[q] = k;
		}
		return pi;
		
	}
	
}
