package lab12;

import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
	
	private static Scanner scan; // for input stream
	
	public interface IStringMatcher
	{
		
		LinkedList<Integer> validShifts(String pattern, String text);
		
	}
	
	public static class LinesReader
	{
		
		String concatLines(int howMany, Scanner scanner)
		{
			
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < howMany; i++)
			{
				result.append(scanner.nextLine());
			}
			return result.toString();
			
		}
		
	}
	
	public static class Automaton implements IStringMatcher
	{
		
		private static final int CHARS_COUNT = 256;
		
		private static int getNextState(char[] pat, int patLength, int state, int c)
		{
			
			if (state < patLength && c == pat[state])
			{
				return state + 1;
			}
			int i;
			for (int nextState = state; nextState > 0; nextState--)
			{
				if (pat[nextState - 1] == c)
				{
					for (i = 0; i < nextState - 1; i++)
					{
						if (pat[i] != pat[state - nextState + 1 + i])
						{
							break;
						}
					}
					if (i == nextState - 1)
					{
						return nextState;
					}
				}
			}
			return 0;
			
		}
		
		
		private static void computeSetOfStates(char[] pat, int patLength, int[][] setOfStates)
		{
			
			for (int state = 0; state <= patLength; ++state)
			{
				for (int c = 0; c < CHARS_COUNT; ++c)
				{
					setOfStates[state][c] = getNextState(pat, patLength, state, c);
				}
			}
			
		}
		
		
		@Override
		public LinkedList<Integer> validShifts(String pattern, String text)
		{
			
			LinkedList<Integer> result = new LinkedList<>();
			char[] pat = pattern.toCharArray();
			char[] txt = text.toCharArray();
			int patLength = pat.length;
			int textLength = txt.length;
			int[][] setOfStates = new int[patLength + 1][CHARS_COUNT];
			computeSetOfStates(pat, patLength, setOfStates);
			int i, state = 0;
			for (i = 0; i < textLength; i++)
			{
				state = setOfStates[state][txt[i]];
				if (state == patLength)
				{
					result.add((i - patLength + 1));
				}
			}
			return result;
			
		}
		
	}
	
	public static class KMP implements IStringMatcher
	{
		
		static int[] computeLongestMatches(String pat, int patternLength)
		{
			
			int[] result = new int[patternLength];
			for (int i = 1, len = 0; i < patternLength;)
			{
				if (pat.charAt(i) == pat.charAt(len))
				{
					result[i++] = ++len;
				}
				else
				{
					if (len > 0)
					{
						len = result[len - 1];
					}
					else
					{
						i++;
					}
				}
			}
			return result;
			
		}
		
		
		@Override
		public LinkedList<Integer> validShifts(String pattern, String text)
		{
			
			LinkedList<Integer> result = new LinkedList<>();
			
			if (text == null || pattern == null)
			{
				return result;
			}
			int patternLength = pattern.length();
			int textLength = text.length();
			int i = 0;
			int j = 0;
			if (patternLength > textLength)
			{
				return result;
			}
			int[] matches = computeLongestMatches(pattern, patternLength);
			while (i < textLength)
			{
				if (pattern.charAt(j) == text.charAt(i))
				{
					j++;
					i++;
				}
				if (j == patternLength)
				{
					result.add(i - j);
					j = matches[j - 1];
				}
				else
				{
					if (i < textLength && pattern.charAt(j) != text.charAt(i))
					{
						
						if (j != 0)
						{
							j = matches[j - 1];
						}
						
						else
						{
							i = i + 1;
						}
						
					}
				}
				
			}
			return result;
			
		}
		
	}
	
	private static String input = "#Test for Lab12\n" + "automaton 1 1\n" + "ababac\n" + "ababac\n" + "kmp 2 3\n"
			+ "aba\n" + "bac\n" + "ggggab\n" + "ab\n" + "acaaaaaaaaa\n" + "automaton 1 3\n" + "abab\n"
			+ "abababababadsdsdsdsdsdsdsdsaba\n" + "fffjjjabababababababab\n" + "cccccccc\n" + "kmp 1 1\n" + "abcdef\n"
			+ "abcdeabcder\n" + "ha";
	
	public static void main(String[] args)
	{
		
		System.out.println("START");
		scan = new Scanner(System.in);
		// scan = new Scanner(input);
		Automaton automaton = new Automaton();
		KMP kmp = new KMP();
		LinesReader reader = new LinesReader();
		boolean halt = false;
		while (!halt)
		{
			String line = scan.nextLine();
			// empty line and comment line - read next line
			if (line.length() == 0 || line.charAt(0) == '#')
			{
				continue;
			}
			// copy line to output (it is easier to find a place of a mistake)
			System.out.println("!" + line);
			String[] word = line.split(" ");
			// ha
			if (word[0].equalsIgnoreCase("ha") && word.length == 1)
			{
				halt = true;
				continue;
			}
			// automaton <PatternLines> <TextLines >
			if (word[0].equalsIgnoreCase("automaton") && word.length == 3)
			{
				int patternLines = Integer.parseInt(word[1]);
				int textLines = Integer.parseInt(word[2]);
				String pattern = reader.concatLines(patternLines, scan);
				String text = reader.concatLines(textLines, scan);
				LinkedList<Integer> result = automaton.validShifts(pattern, text);
				System.out.println(result);
				continue;
			}
			// kmp <PatternLines> <TextLines>
			if (word[0].equalsIgnoreCase("kmp") && word.length == 3)
			{
				int patternLines = Integer.parseInt(word[1]);
				int textLines = Integer.parseInt(word[2]);
				String pattern = reader.concatLines(patternLines, scan);
				String text = reader.concatLines(textLines, scan);
				LinkedList<Integer> result = kmp.validShifts(pattern, text);
				System.out.println(result);
				continue;
			}
			System.out.println("Wrong command");
		}
		System.out.println("END OF EXECUTION");
		scan.close();
		
	}
	
}
