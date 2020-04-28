package tut5;

import java.util.Arrays;

public class Main
{
	
	public static void main(String[] args)
	{
		
		Random r = new Random(11);
		Inverse i = new Inverse();
		Ordered o = new Ordered();
		System.out.println("Sorting values from a random ordered array: ");
		r.sorting();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Sorting values from an inversed ordered array: ");
		i.sorting();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Sorting values from an almost ordered array: ");
		o.sorting();
		
	}
	
}

class Sorts
{
	
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
		
		int noSawp = 0;
		int noCompar = 0;
		showArray(arr);
		for (int begin = 0; begin < arr.length - 1; begin++)
		{
			for (int j = arr.length - 1; j > begin; j--) if (arr[j - 1] > arr[j])
			{
				swap(arr, j - 1, j);
				noSawp++;
			}
			noCompar++;
		}
		showArray(arr);
		System.out.println("The number of comparision using Bubble sort is: " + (noCompar + 1));
		System.out.println("The number of shifting using Bubble sort is: " + noSawp);
		
	}
	
	
	private void swap(int[] arr, int i, int j)
	{
		
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
	}
	
	
	public void insertSort(int[] arr)
	{
		
		int noSawp = 0;
		int noCompar = 0;
		showArray(arr);
		for (int pos = arr.length - 2; pos >= 0; pos--)
		{
			int value = arr[pos];
			int i = pos + 1;
			while (i < arr.length && value > arr[i])
			{
				arr[i - 1] = arr[i];
				noSawp++;
				i++;
			}
			arr[i - 1] = value;
			noCompar++;
		}
		showArray(arr);
		System.out.println("The number of comparision using Insert sort is: " + (noCompar + 1));
		System.out.println("The number of shifting using Insert sort is: " + noSawp);
		
	}
	
	
	public void selectSort(int[] arr)
	{
		
		int noSawp = 0;
		int noCompar = 0;
		showArray(arr);
		for (int border = arr.length; border > 1; border--)
		{
			int maxPos = 0;
			for (int pos = 0; pos < border; pos++)
			{
				if (arr[maxPos] < arr[pos])
				{
					maxPos = pos;
				}
			}
			swap(arr, border - 1, maxPos);
			noSawp++;
			noCompar++;
		}
		showArray(arr);
		System.out.println("The number of comparision using Select sort is: " + (noCompar + 1));
		System.out.println("The number of shifting using Select sort is: " + noSawp);
		
	}
	
}

class Random
{
	
	private int n = 0;
	
	public Random(int n)
	{
		
		this.n = n;
		
	}
	
	private Sorts s = new Sorts();
	
	private int[] insertEle()
	{
		
		int[] array = new int[n];
		for (int x = 0; x < array.length; x++)
		{
			double randomNumber = (int) (Math.random() * 100) + 1;
			array[x] = (int) randomNumber;
		}
		return array;
		
	}
	
	
	public void sorting()
	{
		
		int[] array = insertEle();
		System.out.println(Arrays.toString(array.clone()));
		System.out.println("Sorting random values: ");
		System.out.println("Sorting using Bubble sort: ");
		s.bubbleSort(array.clone());
		System.out.println("Sorting using Insert sort: ");
		s.insertSort(array.clone());
		System.out.println("Sorting using Select: ");
		s.selectSort(array.clone());
		
	}
	
}

class Ordered
{
	
	private Sorts s = new Sorts();
	
	public void sorting()
	{
		
		int[] array = {0, 3, 6, 9, 12, 15, 18, 16, 10, 21, 24 };
		System.out.println(Arrays.toString(array.clone()));
		System.out.println("Sorting using Bubble sort: ");
		s.bubbleSort(array.clone());
		System.out.println("Sorting using Insert sort: ");
		s.insertSort(array.clone());
		System.out.println("Sorting using Select: ");
		s.selectSort(array.clone());
		
	}
	
}

class Inverse
{
	
	private Sorts s = new Sorts();
	
	public void sorting()
	{
		
		int[] array = {50, 45, 40, 35, 30, 25, 20, 15, 10, 5, 0 };
		System.out.println(Arrays.toString(array.clone()));
		System.out.println("Sorting using Bubble sort: ");
		s.bubbleSort(array.clone());
		System.out.println("Sorting using Insert sort: ");
		s.insertSort(array.clone());
		System.out.println("Sorting using Select: ");
		s.selectSort(array.clone());
		
	}
	
}
