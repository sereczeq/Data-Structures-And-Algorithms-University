package lab12;

import java.util.LinkedList;

public class Automaton implements IStringMatcher
{
	
	@Override
	public LinkedList<Integer> validShifts(String pattern, String text)
	{
		
		LinkedList<Integer> shifts = new LinkedList<>();
		char[] textArr = text.toCharArray();
		char[] patternArr = pattern.toCharArray();
		int[][] transitionFunction = null;
		transitionFunction = computeTransitionFunction(patternArr);
		if (transitionFunction == null) return shifts;
		for (int i = 0, state = 0; i < textArr.length; i++)
		{
			state = transitionFunction[state][textArr[i]];
			if (state == patternArr.length)
			{
				shifts.add(i + 1 - patternArr.length); // random 1, but works
				i -= state;
				// i += 1; // random 1 again, but works
				// state = 0;
			}
		}
		
		return shifts;
		
	}
	
	
	// assume codes from 0 to 225
	public int[][] computeTransitionFunction(char[] patternArr)
	{
		
		if (patternArr.length <= 0) return null;
		int[][] transitionFunction = new int[patternArr.length + 1][226];
		for (int q = 0; q < patternArr.length; q++)
		{
			for (int a = 65; a < 122; a++)
			{ // assumed input alphabet
				if (a == 91)
				{
					a = 97;
				}
				int k = Math.min(patternArr.length + 1, q + 2);
				
				String pattern = String.copyValueOf(patternArr);
				String Pk, Pqa;
				// Pk = the substring of pattern ending at k
				do
				{
					k--;
					Pk = pattern.substring(0, k);
					Pqa = pattern.substring(0, q) + (char) a;
					/*
					 * It then decreases k until Pk ⊐ Pqa, which must eventually occur, since P0 D "
					 * is a suffix of every string
					 */
				}
				while (!isSuffix(Pk, Pqa));
				
				transitionFunction[q][a] = k;
			}
		}
		
		return transitionFunction;
		
	}
	
	
	private boolean isSuffix(String smallString, String fullString)
	{
		
		return fullString.endsWith(smallString);
		
	}
	
}
