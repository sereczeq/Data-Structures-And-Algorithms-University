package lab8;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Document
{
	
	public String name;
	public BinarySearchTree<Link> link;
	final static Pattern regex = Pattern.compile("(link=)(?<ref>[a-z]([\\w_]*)?)(\\((?<number>[0-9-]*)\\))?$");
	final static Pattern docName = Pattern.compile("^[a-z].*$");
	
	public Document(String name, Scanner scan)
	{
		
		this(name);
		load(scan);
		
	}
	
	
	public Document(String name)
	{
		
		this.name = name.toLowerCase();
		link = new BinarySearchTree<Link>();
		
	}
	
	
	public void load(Scanner scan)
	{
		
		while (scan.hasNext())
		{
			String line = scan.next().toLowerCase();
			if (line.contains("eod")) break;
			if (createLink(line) != null) link.add(createLink(line));
		}
		
	}
	
	
	static boolean isCorrectId(String id)
	{
		
		return docName.matcher(id.toLowerCase()).find();
		
	}
	
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	// and eventually weight in parenthesis
	static Link createLink(String line)
	{
		
		Matcher matcher = regex.matcher(line);
		if (matcher.find())
		{
			if (matcher.group("number") != null)
			{
				if (Integer.parseInt(matcher.group("number")) < 0) return null;
				return new Link(matcher.group("ref"), Integer.parseInt(matcher.group("number")));
			}
			return new Link(matcher.group("ref"));
		}
		return null;
		
	}
	
	// @Override
	// public String toString()
	// {
	//
	// String retStr = "Document: " + name;
	// Iterator<Link> it = link.iterator();
	// for (int x = 0; x < link.size(); x++)
	// {
	// if (x == 0 || x == 10) retStr += "\n";
	// retStr += it.next();
	// if (x != link.size() - 1 && x != 9) retStr += " ";
	// }
	// return retStr;
	//
	// }
	
	
	@Override
	public boolean equals(Object other)
	{
		
		return other instanceof Document && name.contentEquals(((Document) other).name);
		
	}
	
	
	@Override
	public int hashCode()
	{
		
		int[] multValues = {7, 11, 13, 17, 19 };
		char[] name = this.name.toCharArray();
		double key = 0;
		if (name.length >= 1) key = (int) name[0];
		for (int x = 1, n = 0; x < name.length; x++, n++)
		{
			key *= multValues[n % 5];
			key += (int) name[x];
		}
		return (int) key;
		
	}
	
	
	public BigInteger hashCodeBig()
	{
		
		int[] multValues = {7, 11, 13, 17, 19 };
		char[] name = this.name.toCharArray();
		BigInteger key = BigInteger.ZERO;
		if (name.length >= 1) key = BigInteger.valueOf((int) name[0]);
		for (int x = 1, n = 0; x < name.length; x++, n++)
		{
			key = key.multiply(BigInteger.valueOf(multValues[n % 5]));
			key = key.add(BigInteger.valueOf((int) name[x]));
		}
		return key;
		
	}
	
	
	public String getName()
	{
		
		return name;
		
	}
	
	// public String toStringReverse()
	// {
	//
	// String retStr = "Document: " + name;
	//
	// ListIterator<Link> it = link.listIterator();
	// for (int x = 0; x < link.size(); x++)
	// {
	// if (x == 0 || x == 10) retStr += "\n";
	// retStr += it.previous();
	// if (x != link.size() - 1 && x != 9) retStr += " ";
	// }
	// return retStr;
	//
	// }
	
	// public int[] getWeights()
	// {
	//
	// int[] array = new int[link.size()];
	// Iterator<Link> it = link.iterator();
	// for (int x = 0; x < link.size(); x++) array[x] = it.next().getWeight();
	// return array;
	//
	// }
	
	
	public static void showArray(int[] arr)
	{
		
		if (arr != null && arr.length > 0) System.out.print(arr[0]);
		for (int x = 1; x < arr.length; x++) System.out.print(" " + arr[x]);
		System.out.println();
		
	}
	
	
	void bubbleSort(int[] arr)
	{
		
		showArray(arr);
		for (int begin = 0; begin < arr.length - 1; begin++)
		{
			for (int j = arr.length - 1; j > begin; j--) if (arr[j - 1] > arr[j]) swap(arr, j - 1, j);
			showArray(arr);
		}
		
	}
	
	
	private static void swap(int[] arr, int i, int j)
	{
		
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
	}
	
	
	public void insertSort(int[] arr)
	{
		
		showArray(arr);
		for (int pos = arr.length - 2; pos >= 0; pos--)
		{
			int value = arr[pos];
			int i = pos + 1;
			while (i < arr.length && value > arr[i])
			{
				arr[i - 1] = arr[i];
				i++;
			}
			arr[i - 1] = value;
			showArray(arr);
		}
		
	}
	
	
	public void selectSort(int[] arr)
	{
		
		showArray(arr);
		for (int border = arr.length; border > 1; border--)
		{
			int maxPos = 0;
			for (int pos = 0; pos < border; pos++) if (arr[maxPos] < arr[pos]) maxPos = pos;
			swap(arr, border - 1, maxPos);
			showArray(arr);
		}
		
	}
	
	
	public static void iterativeMergeSort(int[] arr)
	{
		
		showArray(arr);
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
			}
			showArray(arr);
		}
		
	}
	
	
	private static int[] mergeSort(int[] arr, int[] arrL, int[] arrR, int leftStart)
	{
		
		// MergeSorting arrays
		int leftPos = 0;
		int rightPos = 0;
		while (leftPos < arrL.length && rightPos < arrR.length)
		{
			if (arrL[leftPos] < arrR[rightPos]) arr[leftStart++] = arrL[leftPos++];
			else arr[leftStart++] = arrR[rightPos++];
		}
		// Rewriting the rest
		while (leftPos < arrL.length) arr[leftStart++] = arrL[leftPos++];
		while (rightPos < arrR.length) arr[leftStart++] = arrR[rightPos++];
		return arr;
		
	}
	
	
	public static void radixSort(int[] arr)
	{
		
		showArray(arr);
		// numArr is an array containing the significant digit of corresponding number
		int[] numArr = new int[arr.length];
		// for the last digit
		for (int x = 0; x < numArr.length; x++) numArr[x] = arr[x] % 10;
		arr = countSort(arr, numArr);
		showArray(arr);
		// for the middle digit
		for (int x = 0; x < numArr.length; x++) numArr[x] = (arr[x] / 10) % 10;
		arr = countSort(arr, numArr);
		showArray(arr);
		// for the first digit
		for (int x = 0; x < numArr.length; x++) numArr[x] = arr[x] / 100;
		arr = countSort(arr, numArr);
		showArray(arr);
		
	}
	
	
	public static int[] countSort(int[] arr, int[] numArr)
	{
		
		int[] count = new int[10];
		for (int x : numArr) count[x]++;
		for (int x = 1; x < 10; x++) count[x] += count[x - 1];
		int[] newArr = new int[numArr.length];
		for (int x = arr.length - 1; x >= 0; x--) newArr[count[numArr[x]]-- - 1] = arr[x];
		return newArr;
		
	}
	
	
	public String toStringPostOrder()
	{
		
		// TODO Auto-generated method stub
		return null;
		
	}
	
	
	public String toStringPreOrder()
	{
		
		// TODO Auto-generated method stub
		return null;
		
	}
	
}