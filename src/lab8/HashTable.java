package lab8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface IWithName
{
	
	String getName();
	
}

interface IList<E> extends Iterable<E>
{
	
	boolean add(E e); // add element to the list on proper position
	
	void add(int index, E element) throws NoSuchElementException; // not implemented
	
	void clear(); // delete all elements
	
	boolean contains(E element); // is list containing an element (equals())
	
	E get(int index) throws NoSuchElementException; // get element from position
	
	E set(int index, E element) throws NoSuchElementException; // not implemented
	
	int indexOf(E element); // where is element (equals())
	
	boolean isEmpty();
	
	Iterator<E> iterator();
	
	ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator
	
	E remove(int index) throws NoSuchElementException; // remove element from position index
	
	boolean remove(E e); // remove element
	
	int size();
	
}

class HashTable<E>
{
	
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize = 8;
	private final static double defaultMaxLoadFactor = 0.7;
	private int size;
	private int elemAmount;
	private final double maxLoadFactor;
	
	public HashTable()
	{
		
		this(defaultInitSize);
		
	}
	
	
	public HashTable(int size)
	{
		
		this(size, defaultMaxLoadFactor);
		
	}
	
	
	public HashTable(int initCapacity, double maxLF)
	{
		
		// TODO
		this.maxLoadFactor = maxLF;
		this.size = initCapacity;
		this.arr = new LinkedList[size];
		for (int x = 0; x < size; x++) arr[x] = new LinkedList<E>();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean add(Object elem)
	{
		
		int key = hash(elem.hashCode());
		if (arr[key].contains(elem)) return false;
		arr[key].add(elem);
		elemAmount++;
		if (elemAmount > (float) size * maxLoadFactor) doubleArray();
		return true;
		
	}
	
	
	private void doubleArray()
	{
		
		size *= 2;
		elemAmount = 0;
		LinkedList[] tempArr = arr;
		arr = new LinkedList[size];
		for (int x = 0; x < size; x++) arr[x] = new LinkedList<E>();
		for (LinkedList x : tempArr) for (Object obj : x) add(obj);
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		for (int x = 0; x < size; x++)
		{
			string += x + ":";
			Iterator it = arr[x].iterator();
			if (it.hasNext())
			{
				string += " " + ((Document) it.next()).getName();
				while (it.hasNext()) string += ", " + ((Document) it.next()).getName();
			}
			string += "\n";
		}
		return string;
		
	}
	
	
	public Object get(Object toFind)
	{
		
		int key = hash(toFind.hashCode());
		for (Object x : arr[key]) if (x.equals(toFind)) return x;
		return null;
		
	}
	
	
	private int hash(int key)
	{
		
		// return ((key / 100) * 6 + key) % size;
		return key % size;
		
	}
	
}

class BinarySearchTree<T>
{
	
	private class Node
	{
		
		T value;
		Node left, right, parent;
		
		public Node(T v)
		{
			
			value = v;
			
		}
		
		
		public Node(T value, Node left, Node right, Node parent)
		{
			
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
			
		}
		
	}
	
	private Node root = null;
	
	public BinarySearchTree()
	{
		
	}
	
	
	public T getElement(T toFind)
	{
		
		// TODO
		return null;
		
	}
	
	
	public T successor(T elem)
	{
		
		// TODO
		return null;
		
	}
	
	
	public String toStringInOrder()
	{
		
		// TODO
		return null;
		
	}
	
	
	public String toStringPreOrder()
	{
		
		// TODO
		return null;
		
	}
	
	
	public String toStringPostOrder()
	{
		
		// TODO
		return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean add(T elem)
	{
		
		if (root == null)
		{
			root = new Node(elem);
			return true;
		}
		Node node = root;
		while (node != null)
		{
			if (((Comparable<T>) elem).compareTo(node.value) > 0) node = node.right;
			else if (((Comparable<T>) elem).compareTo(node.value) < 0) node = node.right;
			else return false;
		}
		node = new Node(elem);
		return true;
		
	}
	
	
	public T remove(T value)
	{
		
		// TODO
		return null;
		
	}
	
	
	public void clear()
	{
		
		// TODO
	}
	
	
	public int size()
	{
		
		// TODO
		return 0;
		
	}
	
}

class Link implements Comparable<Link>
{
	
	public String ref;
	public int weight;
	
	public Link(String ref)
	{
		
		this.ref = ref;
		weight = 1;
		
	}
	
	
	public Link(String ref, int weight)
	{
		
		this.ref = ref;
		this.weight = weight > 0 ? weight : 1;
		
	}
	
	
	public int getWeight()
	{
		
		return weight;
		
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		
		return obj instanceof Link && ref.contentEquals(((Link) obj).ref);
		
	}
	
	
	@Override
	public String toString()
	{
		
		return ref + "(" + weight + ")";
		
	}
	
	
	@Override
	public int compareTo(Link other)
	{
		
		return ref.compareTo(other.ref);
		
	}
	
}

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
