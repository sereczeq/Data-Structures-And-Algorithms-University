package lab12;

import java.util.LinkedList;

public class Automaton implements IStringMatcher
{
	
	@Override
	public LinkedList<Integer> validShifts(String pattern, String text)
	{
		
		LinkedList<Integer> shifts = new LinkedList<>();
		
		char[] P = pattern.toCharArray();
		char[] T = text.toCharArray();
		int n = T.length;
		int m = P.length;
		int q = 0;
		for (int i = 1; i < n; i++)
		{
			q = getNextState(P, P.length, q, q);
			if (q == m)
			{
				int s = i - m;
				shifts.add(s);
			}
		}
		return null;
		
	}
	
	
	private int getNextState(char[] pat, int M, int state, int x)
	{
		
		// If the character c is same as next
		// character in pattern,then simply
		// increment state
		if (state < M && x == pat[state]) return state + 1;
		
		// ns stores the result which is next state
		int ns, i;
		
		// ns finally contains the longest prefix
		// which is also suffix in "pat[0..state-1]c"
		
		// Start from the largest possible value
		// and stop when you find a prefix which
		// is also suffix
		for (ns = state; ns > 0; ns--)
		{
			if (pat[ns - 1] == x)
			{
				for (i = 0; i < ns - 1; i++) if (pat[i] != pat[state - ns + 1 + i]) break;
				if (i == ns - 1) return ns;
			}
		}
		
		return 0;
		
	}
	
}
