package lab12;

import java.util.Scanner;

public class LinesReader
{
	
	public String concatLines(int howMany, Scanner scanner)
	{
		
		StringBuffer string = new StringBuffer();
		for (int x = 0; x < howMany; x++)
		{
			if (scanner.hasNextLine())
			{
				string.append(scanner.nextLine());
			}
			else return null;
		}
		return string.toString();
		
	}
	
}
