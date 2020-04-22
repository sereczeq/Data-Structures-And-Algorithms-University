package tut5;

import java.util.Random;

public class SortingComparator
{
	
	private int comparisons = 0;
	private int shifts = 0;
	private String[][] table = new String[8][10];
	
	public SortingComparator()
	{
		
		fillTable();
		
	}
	
	
	private void test(int[]... arrays)
	{
		
		for (int[] arr : arrays)
		{
			long start = System.currentTimeMillis();
			bubbleSort(arr);
			long finish = System.currentTimeMillis();
			int x = 0;
			while (table[x][1] != null) x++;
			table[x][1] = "" + comparisons;
			table[x][2] = "" + shifts;
			table[x][3] = "" + (finish - start);
			comparisons = 0;
			shifts = 0;
			
			start = System.currentTimeMillis();
			insertSort(arr);
			finish = System.currentTimeMillis();
			table[x][4] = "" + comparisons;
			table[x][5] = "" + shifts;
			table[x][6] = "" + (finish - start);
			comparisons = 0;
			shifts = 0;
			
			start = System.currentTimeMillis();
			selectSort(arr);
			finish = System.currentTimeMillis();
			table[x][7] = "" + comparisons;
			table[x][8] = "" + shifts;
			table[x][9] = "" + (finish - start);
			comparisons = 0;
			shifts = 0;
		}
		showTable();
		
	}
	
	
	private void fillTable()
	{
		
		table[0] = new String[] {" ", "bubble", "insert", "select" };
		table[1] = new String[] {" ", "compares", "shifts", "time", "compares", "shifts", "time", "compares", "shifts",
				"time" };
		table[2][0] = "sorted";
		table[3][0] = "almost sorted";
		table[4][0] = "inverted";
		table[5][0] = "almost inverted";
		table[6][0] = "random";
		table[7][0] = "random";
		
	}
	
	
	private void showTable()
	{
		
		System.out.format("%20s%25s%35s%35s\n", table[0]);
		for (int x = 1; x < table.length; x++)
		{
			System.out.format("%20s%15s%10s%10s%15s%10s%10s%15s%10s%10s\n", table[x]);
		}
		
	}
	
	
	private void swap(int[] arr, int i, int j)
	{
		
		shifts++;
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
	}
	
	
	private boolean compare(int one, int two)
	{
		
		comparisons++;
		return one > two;
		
	}
	
	
	public static void showArray(int[] arr)
	{
		
		if (arr.length > 0)
		{
			System.out.print(arr[0]);
			for (int i = 1; i < arr.length; i++) System.out.print(" " + arr[i]);
		}
		System.out.println();
		
	}
	
	
	void bubbleSort(int[] arr)
	{
		
		for (int begin = 0; begin < arr.length - 1; begin++)
		{
			for (int j = arr.length - 1; j > begin; j--) if (compare(arr[j - 1], arr[j])) swap(arr, j - 1, j);
			
		}
		
	}
	
	
	public void insertSort(int[] arr)
	{
		
		for (int pos = arr.length - 2; pos >= 0; pos--)
		{
			int value = arr[pos];
			int i = pos + 1;
			while (i < arr.length && compare(value, arr[i]))
			{
				shifts++;
				arr[i - 1] = arr[i];
				i++;
			}
			shifts++;
			arr[i - 1] = value;
		}
		showArray(arr);
		
	}
	
	
	public void selectSort(int[] arr)
	{
		
		for (int border = arr.length; border > 1; border--)
		{
			int maxPos = 0;
			for (int pos = 0; pos < border; pos++) if (compare(arr[maxPos], arr[pos])) maxPos = pos;
			swap(arr, border - 1, maxPos);
			
		}
		
	}
	
	
	public static void main(String[] args)
	{
		
		/*
		 * Program counts under assumptions that: Comparing is an action of comparing
		 * two elements of array (not checking array size inside of loops), Shifting is
		 * an action of changing data inside array (not integer values that are outside
		 * of an array)
		 */
		
		// int[] sortedArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// int[] almostSortedArr = {1, 3, 2, 4, 10, 6, 7, 8, 9, 5 };
		// int[] invertedArr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		// int[] almostInvertedArr = {10, 9, 1, 7, 6, 5, 4, 3, 2, 8 };
		// int[] randomArr = {10, 3, 5, 2, 7, 1, 4, 8, 9 };
		// int[] randomArr2 = {1, 10, 2, 9, 3, 8, 4, 7, 5, 6 };
		int[] sortedArr = new int[10000];
		int[] almostSortedArr = new int[10000];
		int[] invertedArr = new int[10000];
		int[] almostInvertedArr = new int[10000];
		int[] randomArr = new int[10000];
		int[] randomArr2 = new int[10000];
		Random rand = new Random();
		for (int x = 0; x < 10000; x++)
		{
			sortedArr[x] = x;
			invertedArr[10000 - x - 1] = x;
			randomArr[x] = rand.nextInt(10000);
			randomArr2[x] = rand.nextInt(10000);
		}
		almostSortedArr = sortedArr;
		almostSortedArr[rand.nextInt(10000)] = rand.nextInt(10000);
		almostSortedArr[rand.nextInt(10000)] = rand.nextInt(10000);
		almostSortedArr[rand.nextInt(10000)] = rand.nextInt(10000);
		almostInvertedArr = invertedArr;
		almostInvertedArr[rand.nextInt(10000)] = rand.nextInt(10000);
		almostInvertedArr[rand.nextInt(10000)] = rand.nextInt(10000);
		almostInvertedArr[rand.nextInt(10000)] = rand.nextInt(10000);
		SortingComparator comp = new SortingComparator();
		comp.test(sortedArr, almostSortedArr, invertedArr, almostInvertedArr, randomArr, randomArr2);
		
	}
	
}
