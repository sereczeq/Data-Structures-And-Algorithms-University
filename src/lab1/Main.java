package lab1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
	
	static Scanner scan;
	final static Pattern regex = Pattern.compile("link=[a-z]\\w*");
	
	public static void loadDocument(String name)
	{
		
		Scanner fileScanner = scan;
		
		// deleted lines would work if we were to read an actual file
		
		// Scanner fileScanner = new Scanner(new File(name));
		// try
		// {
		// fileScanner = new Scanner(new File(name));
		// }
		// catch (FileNotFoundException e)
		// {
		// System.out.println("File: \"" + name + "\" could not be found");
		// return;
		// }
		
		while (fileScanner.hasNext())
		{
			String line = fileScanner.next().toLowerCase();
			if (line.contains("eod")) break;
			if (correctLink(line)) System.out.println(line.split("=")[1]);
		}
		// fileScanner.close();
		
	}
	
	
	// accepted only small letters, capital letter, digits and '_' (but not on the
	// beginning)
	private static boolean correctLink(String link)
	{
		
		Matcher matcher = regex.matcher(link);
		return matcher.find();
		
	}
	
	
	private static void drawLine(int n, char ch)
	{
		
		for (int x = 0; x < n; x++) System.out.print(ch);
		
	}
	
	
	private static void drawPyramid(int n)
	{
		
		for (int x = 0, howMany = 1; x < n; x++, howMany += 2)
		{
			drawLine(n - x, ' ');
			drawLine(howMany, 'X');
			System.out.println();
		}
		
	}
	
	
	private static void drawZ(int n)
	{
		
		drawLine(n, 'X');
		System.out.println("");
		for (int x = n - 2; x > 0; x--)
		{
			drawLine(x, ' ');
			System.out.println("X");
		}
		drawLine(n, 'X');
		
	}
	
	
	private static void drawZigzag(int n)
	{
		
		// draws top of zet
		for (int x = 0; x < n; x++)
		{
			System.out.print("X");
		}
		// it's equivalent of pressing enter, makes another line
		System.out.print("\n");
		// this draws the middle of the zet
		for (int x = n - 2; x > 0; x--)
		{
			for (int y = 0; y < n; y++)
			{
				// this prints the exact amount of spaces needed
				System.out.print(" ");
			}
			System.out.println("X");
		}
		// draws bottom of zet
		for (int x = 0; x < n; x++)
		{
			System.out.print("X");
		}
		
	}
	
	
	private static void drawChristmassTree(int n)
	{
		
		for (int x = 0; x <= n; x++)
		{
			for (int y = 0, howMany = 1; y < x; y++, howMany += 2)
			{
				drawLine(n - y, ' ');
				drawLine(howMany, 'X');
				System.out.println();
			}
		}
		
	}
	
	
	/***
	 * commands: py size draw a pyramid with size ct size draw a christmas tree with
	 * size ld documentName load document from standard input line by line. Last
	 * line consists of only sequence "eod", which means end of document ha halt
	 * program and finish execution
	 * 
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		
		System.out.println("START");
		scan = new Scanner(System.in);
		boolean halt = false;
		while (!halt)
		{
			String line = scan.nextLine();
			// empty line and comment line - read next line
			if (line.length() == 0 || line.charAt(0) == '#') continue;
			// copy line to output (it is easier to find a place of a mistake)
			System.out.println("!" + line);
			String word[] = line.split(" ");
			if (word[0].equalsIgnoreCase("py") && word.length == 2)
			{
				int value = Integer.parseInt(word[1]);
				drawPyramid(value);
				continue;
			}
			if (word[0].equalsIgnoreCase("ct") && word.length == 2)
			{
				int value = Integer.parseInt(word[1]);
				drawChristmassTree(value);
				continue;
			}
			// ld documentName
			if (word[0].equalsIgnoreCase("ld") && word.length == 2)
			{
				loadDocument(word[1]);
				continue;
			}
			
			// draw Z
			if (word[0].equalsIgnoreCase("dz") && word.length == 2)
			{
				drawZ(Integer.parseInt(word[1]));
				continue;
			}
			// ha
			if (word[0].equalsIgnoreCase("ha") && word.length == 1)
			{
				halt = true;
				continue;
			}
			System.out.println("Wrong command");
		}
		System.out.println("END OF EXECUTION");
		scan.close();
		
	}
	
}
