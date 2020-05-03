package tut5;

import java.util.Random;

public class SortingComparator
{
	
	private int comparisons = 0;
	private int shifts = 0;
	private String[][] table = new String[8][13];
	
	public SortingComparator()
	{
		
		fillTable();
		
	}
	
	
	private void test(int[]... arrays)
	{
		
		int x = 2;
		for (int[] array : arrays)
		{
			int[] arr = new int[array.length];
			for (int i = 0; i < arr.length; i++) arr[i] = array[i];
			long start = System.nanoTime();
			bubbleSort(arr);
			long finish = System.nanoTime();
			table[x][1] = "" + comparisons;
			table[x][2] = "" + shifts;
			table[x][3] = "" + (finish - start);
			comparisons = 0;
			shifts = 0;
			
			arr = new int[array.length];
			for (int i = 0; i < arr.length; i++) arr[i] = array[i];
			start = System.nanoTime();
			insertSort(arr);
			finish = System.nanoTime();
			table[x][4] = "" + comparisons;
			table[x][5] = "" + shifts;
			table[x][6] = "" + (finish - start);
			comparisons = 0;
			shifts = 0;
			
			arr = new int[array.length];
			for (int i = 0; i < arr.length; i++) arr[i] = array[i];
			start = System.nanoTime();
			selectSort(arr);
			finish = System.nanoTime();
			table[x][7] = "" + comparisons;
			table[x][8] = "" + shifts;
			table[x][9] = "" + (finish - start);
			comparisons = 0;
			shifts = 0;
			
			arr = new int[array.length];
			for (int i = 0; i < arr.length; i++) arr[i] = array[i];
			start = System.nanoTime();
			iterativeMergeSort(arr);
			showArray(arr);
			finish = System.nanoTime();
			table[x][10] = "" + comparisons;
			table[x][11] = "" + shifts;
			table[x][12] = "" + (finish - start);
			comparisons = 0;
			shifts = 0;
			x++;
		}
		
		showTable();
		
	}
	
	
	private void fillTable()
	{
		
		table[0] = new String[] {" ", "bubble", "insert", "select", "merge" };
		table[1] = new String[] {" ", "compares", "shifts", "time", "compares", "shifts", "time", "compares", "shifts",
				"time", "compares", "shifts", "time" };
		table[2][0] = "sorted";
		table[3][0] = "almost sorted";
		table[4][0] = "inverted";
		table[5][0] = "almost inverted";
		table[6][0] = "random";
		table[7][0] = "random";
		
	}
	
	
	@SuppressWarnings("all") // Because format function was throwing warning that I didn't know how to fix
	private void showTable()
	{
		
		System.out.format("%20s%25s%35s%35s%35s\n", table[0]);
		for (int x = 1; x < table.length; x++)
		{
			System.out.format("%20s%15s%10s%10s%15s%10s%10s%15s%10s%10s%15s%10s%10s\n", table[x]);
		}
		
	}
	
	
	public int[][] newArrays(int size, boolean showArrays)
	{
		
		int[] sortedArr = new int[size];
		int[] almostSortedArr = new int[size];
		int[] invertedArr = new int[size];
		int[] almostInvertedArr = new int[size];
		int[] randomArr = new int[size];
		int[] randomArr2 = new int[size];
		Random rand = new Random();
		for (int x = 0; x < size; x++)
		{
			sortedArr[x] = x;
			invertedArr[size - x - 1] = x;
			randomArr[x] = rand.nextInt(size);
			randomArr2[x] = rand.nextInt(size);
		}
		for (int x = 0; x < size; x++)
		{
			almostSortedArr[x] = sortedArr[x];
			almostInvertedArr[x] = invertedArr[x];
		}
		
		for (int x = size / 100 + 1; x > 0; x--) // plus one so it's done at least once
		{
			almostSortedArr[rand.nextInt(size)] = rand.nextInt(size);
			almostInvertedArr[rand.nextInt(size)] = rand.nextInt(size);
		}
		int[][] returnArr = {sortedArr, almostSortedArr, invertedArr, almostInvertedArr, randomArr, randomArr2 };
		if (showArrays) for (int[] arr : returnArr) showArray(arr);
		System.out.println("\n");
		return returnArr;
		
	}
	
	
	// Compare and swap methods include counting the operations
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
	
	
	void bubbleSort(int[] array)
	{
		
		int[] arr = array;
		for (int begin = 0; begin < arr.length - 1; begin++)
		{
			for (int j = arr.length - 1; j > begin; j--) if (compare(arr[j - 1], arr[j])) swap(arr, j - 1, j);
			
		}
		
	}
	
	
	public void insertSort(int[] array)
	{
		
		int[] arr = array;
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
		
	}
	
	
	public void selectSort(int[] array)
	{
		
		int[] arr = array;
		for (int border = arr.length; border > 1; border--)
		{
			int maxPos = 0;
			for (int pos = 0; pos < border; pos++) if (compare(arr[maxPos], arr[pos])) maxPos = pos;
			swap(arr, border - 1, maxPos);
			
		}
		
	}
	
	
	public void iterativeMergeSort(int[] array)
	{
		
		int[] arr = array;
		// Main loop dictating the size of sub-arrays
		for (int size = 1; size < arr.length; size *= 2)
		{
			// Loop for creating sub-arrays and merging them
			for (int leftStart = 0; leftStart < arr.length; leftStart += 2 * size)
			{
				// Setting starting and ending points of new arrays
				int leftEnd = (leftStart + size < arr.length ? leftStart + size : arr.length) - 1;
				int rightEnd = (leftStart + 2 * size < arr.length ? leftStart + 2 * size : arr.length) - 1;
				// Setting up new arrays
				int[] arrL = new int[leftEnd - leftStart + 1];
				int[] arrR = new int[rightEnd - leftEnd];
				for (int x = 0; x < arrL.length; x++) arrL[x] = arr[leftStart + x];
				for (int x = 0; x < arrR.length; x++) arrR[x] = arr[leftEnd + x + 1];
				arr = mergeSort(arr, arrL, arrR, leftStart);
				shifts++;
			}
		}
		
	}
	
	
	private int[] mergeSort(int[] arr, int[] arrL, int[] arrR, int leftStart)
	{
		
		// MergeSorting arrays
		int leftPos = 0;
		int rightPos = 0;
		while (leftPos < arrL.length && rightPos < arrR.length)
		{
			comparisons++;
			if (arrL[leftPos] < arrR[rightPos]) arr[leftStart++] = arrL[leftPos++];
			else arr[leftStart++] = arrR[rightPos++];
		}
		// Rewriting the rest
		while (leftPos < arrL.length) arr[leftStart++] = arrL[leftPos++];
		while (rightPos < arrR.length) arr[leftStart++] = arrR[rightPos++];
		return arr;
		
	}
	
	
	public static void main(String[] args)
	{
		
		/*
		 * Program counts under assumptions that: Comparing is an action of comparing
		 * two elements of array (not checking array size inside of loops), Shifting is
		 * an action of changing data inside array (not integer values that are outside
		 * of an array) Time is measured in nano seconds for easier comparison of the
		 * results
		 */
		int size = 18;
		boolean showArrays = false; // Not recommended for big arrays
		
		SortingComparator comp = new SortingComparator();
		comp.test(comp.newArrays(size, showArrays));
		
	}
	
}
