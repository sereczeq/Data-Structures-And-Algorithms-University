package lab7;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
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
	private int[] multValues = new int[30];
	private int[] addValues = new int[30];
	
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
		Random rand = new Random();
		for (int x = 0; x < 30; x++)
		{
			multValues[x] = rand.nextInt(10) + 2;
			addValues[x] = rand.nextInt(10) + 2;
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean add(Object elem)
	{
		
		int key = hash(elem);
		if (arr[key].contains(elem)) return false;
		arr[key].add(elem);
		elemAmount++;
		if (elemAmount > size * maxLoadFactor) doubleArray();
		return true;
		
	}
	
	
	public int hash(Object elem)
	{
		
		char[] name = null;// elem.toString().toCharArray();
		if (elem instanceof Document) name = ((Document) elem).getName().toCharArray();
		double key = 1;
		int x = 0;
		for (char letter : name)
		{
			key *= Character.getNumericValue(letter) * multValues[x] + addValues[x++];
			if (key <= 0)
			{
				System.out.println("sad");
			}
			if (x == 30) x = 0;
		}
		return (int) key % size;
		
	}
	
	
	private void doubleArray()
	{
		
		size *= 2;
		LinkedList[] tempArr = arr;
		arr = new LinkedList[size];
		for (int x = 0; x < size; x++) arr[x] = new LinkedList<E>();
		for (Object obj : tempArr) add(obj);
		
	}
	
	
	@Override
	public String toString()
	{
		
		// TODO
		// use IWithName x=(IWithName)elem;
		return null;
		
	}
	
	
	public Object get(Object toFind)
	{
		
		if (arr[hash(toFind)].contains(toFind)) return toFind;
		else return null;
		
	}
	
}

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>
{
	
	// public static void main(String[] args)
	// {
	//
	// int[] arr = new int[] {0, 23, 0, 934, 1, 213, 98 };
	// // Document.iterativeMergeSort(arr);
	// Document.radixSort(arr);
	//
	// }
	
	private class Element
	{
		
		public Element(E e)
		{
			
			object = e;
			
		}
		
		
		public Element(E e, Element next, Element prev)
		{
			
			object = e;
			this.next = next;
			this.prev = prev;
			
		}
		
		
		// add element e after this
		public void addAfter(Element elem)
		{
			
			Element temp = next;
			next = elem;
			elem.prev = this;
			temp.prev = elem;
			elem.next = temp;
			
		}
		
		
		// assert it is NOT a sentinel
		public E remove()
		{
			
			if (object == null) throw new NoSuchElementException("can't remove sentinel / element with null object");
			if (prev != null) prev.next = next;
			if (next != null) next.prev = prev;
			return object;
			
		}
		
		E object;
		Element next = null;
		Element prev = null;
		
	}
	
	Element sentinel;
	int size;
	
	private class InnerIterator implements Iterator<E>
	{
		
		Element curr;
		
		public InnerIterator()
		{
			
			curr = sentinel;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return !isEmpty();
			
		}
		
		
		@Override
		public E next()
		{
			
			if (hasNext())
			{
				curr = curr.next;
				if (curr == sentinel) curr = curr.next;
				return curr.object;
			}
			throw new NoSuchElementException();
			
		}
		
	}
	
	private class InnerListIterator implements ListIterator<E>
	{
		
		Element curr;
		
		public InnerListIterator()
		{
			
			curr = sentinel;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return !isEmpty();
			
		}
		
		
		@Override
		public E next()
		{
			
			if (hasNext())
			{
				curr = curr.next;
				if (curr == sentinel) curr = curr.next;
				return curr.object;
			}
			throw new NoSuchElementException();
			
		}
		
		
		@Override
		public void add(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			return !isEmpty();
			
		}
		
		
		@Override
		public int nextIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public E previous()
		{
			
			if (hasPrevious())
			{
				curr = curr.prev;
				if (curr == sentinel) curr = curr.prev;
				return curr.object;
			}
			throw new NoSuchElementException();
			
		}
		
		
		@Override
		public int previousIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public void remove()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public void set(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	public TwoWayCycledOrderedListWithSentinel()
	{
		
		sentinel = new Element(null);
		sentinel.prev = sentinel.next = sentinel;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		
		if (isEmpty())
		{
			sentinel.next = sentinel.prev = new Element(e, sentinel, sentinel);
			return true;
		}
		else if (e instanceof Comparable)
		{
			
			Element elem = sentinel.next;
			for (int x = 0; x < size(); x++)
			{
				// if e is smaller than elem put e before elem
				if (((Comparable<E>) elem.object).compareTo(e) > 0)
				{
					elem.prev.addAfter(new Element(e));
					return true;
				}
				elem = elem.next;
			}
			
		}
		// default case
		sentinel.prev.addAfter(new Element(e));
		return true;
		
	}
	
	
	private Element getElement(int index)
	{
		
		Element elem = sentinel.next;
		// I'm proud of this one, whole method done in one loop :')
		for (int x = 0; elem != sentinel && x <= index; x++, elem = elem.next) if (x == index) return elem;
		throw new NoSuchElementException();
		
	}
	
	
	@SuppressWarnings("unused")
	private Element getElement(E obj)
	{
		
		return getElement(indexOf(obj));
		
	}
	
	
	@Override
	public void add(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	@Override
	public void clear()
	{
		
		sentinel.next = sentinel.prev = sentinel;
		
	}
	
	
	@Override
	public boolean contains(E element)
	{
		
		for (Element elem = sentinel.next; elem != sentinel; elem = elem.next)
			if (elem.object.equals(element)) return true;
		return false;
		
	}
	
	
	@Override
	public E get(int index)
	{
		
		return getElement(index).object;
		
	}
	
	
	@Override
	public E set(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	@Override
	public int indexOf(E element)
	{
		
		Iterator<E> it = iterator();
		for (int x = 0; x < size(); x++) if (it.next().equals(element)) return x;
		return -1;
		
	}
	
	
	@Override
	public boolean isEmpty()
	{
		
		return sentinel.next == sentinel;
		
	}
	
	
	@Override
	public Iterator<E> iterator()
	{
		
		return new InnerIterator();
		
	}
	
	
	@Override
	public ListIterator<E> listIterator()
	{
		
		return new InnerListIterator();
		
	}
	
	
	@Override
	public E remove(int index)
	{
		
		Element elem = sentinel.next;
		for (int x = 0; x <= index && elem != sentinel; x++, elem = elem.next) if (x == index) return elem.remove();
		throw new NoSuchElementException();
		
	}
	
	
	@Override
	public boolean remove(E e)
	{
		
		try
		{
			remove(indexOf(e));
			return true;
		}
		catch (NoSuchElementException ex)
		{
			return false;
		}
		
	}
	
	
	@Override
	public int size()
	{
		
		int x = 0;
		for (Element elem = sentinel.next; elem != sentinel; x++, elem = elem.next);
		return x;
		
	}
	
	
	@Override
	public boolean equals(Object other)
	{
		
		// I assumed everything (Link + weight) will have to be equal (not like with
		// Link, where only ref matters)
		return toString().contentEquals(other.toString());
		
	}
	
	
	public void add(TwoWayCycledOrderedListWithSentinel<E> other)
	{
		
		if (!equals(other)) while (other.size() > 0) add(other.remove(0));
		
	}
	
	
	public void removeAll(E e)
	{
		
		while (remove(e));
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		Iterator<E> it = iterator();
		for (int x = 0; x < size(); x++) string += "\n" + it.next();
		return string;
		
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
	public TwoWayCycledOrderedListWithSentinel<Link> link;
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
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		
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
	
	
	@Override
	public String toString()
	{
		
		String retStr = "Document: " + name;
		Iterator<Link> it = link.iterator();
		for (int x = 0; x < link.size(); x++)
		{
			if (x == 0 || x == 10) retStr += "\n";
			retStr += it.next();
			if (x != link.size() - 1 && x != 9) retStr += " ";
		}
		return retStr;
		
	}
	
	
	@Override
	public boolean equals(Object other)
	{
		
		return other instanceof Document && name == ((Document) other).name;
		
	}
	
	
	public String getName()
	{
		
		return name;
		
	}
	
	
	public String toStringReverse()
	{
		
		String retStr = "Document: " + name;
		
		ListIterator<Link> it = link.listIterator();
		for (int x = 0; x < link.size(); x++)
		{
			if (x == 0 || x == 10) retStr += "\n";
			retStr += it.previous();
			if (x != link.size() - 1 && x != 9) retStr += " ";
		}
		return retStr;
		
	}
	
	
	public int[] getWeights()
	{
		
		int[] array = new int[link.size()];
		Iterator<Link> it = link.iterator();
		for (int x = 0; x < link.size(); x++) array[x] = it.next().getWeight();
		return array;
		
	}
	
	
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
	
}
