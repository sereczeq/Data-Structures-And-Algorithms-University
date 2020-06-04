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
		int n = text.length();
		int m = pattern.length() - 1;
		int[] pi = computePrefixFunction(P);
		int q = 0;
		for (int i = 1; i < n; i++)
		{
			while (q > 0 && P[q + 1] != T[i])
			{
				q = pi[q];
			}
			if (P[q + 1] == T[i])
			{
				q++;
			}
			if (q == m)
			{
				shifts.add(i - m);
				q = pi[q];
			}
		}
		return shifts;
		
	}
	
	
	private int[] computePrefixFunction(char[] P)
	{
		
		int m = P.length;
		int[] pi = new int[m];
		pi[1] = 0;
		int k = 0;
		for (int q = 2; q < m; q++)
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
