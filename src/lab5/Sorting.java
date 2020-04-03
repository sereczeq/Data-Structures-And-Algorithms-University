package lab5;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>
{
	
	private class Element
	{
		
		public Element(E e)
		{
			
			this.object = e;
			this.next = this;
			this.prev = this;
			
		}
		
		
		public Element(E e, Element next, Element prev)
		{
			
			this.object = e;
			this.next = next;
			this.prev = prev;
			
		}
		
		
		// add element e after this
		public void addAfter(Element elem)
		{
			
			elem.next = this.next;
			elem.prev = this;
			this.next.prev = elem;
			this.next = elem;
			
		}
		
		
		// assert it is NOT a last element in a list
		public void remove()
		{
			
			this.next.prev = this.prev;
			this.prev.next = this.next;
			
		}
		
		E object;
		Element next = null;
		Element prev = null;
		
	}
	
	Element sentinel;
	int size;
	
	private class InnerIterator implements Iterator<E>
	{
		
		Element p;
		int pos = 0;
		
		public InnerIterator()
		{
			
			pos = 0;
			p = sentinel.next;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return pos < size;
			
		}
		
		
		@Override
		public E next()
		{
			
			E elem = p.object;
			p = p.next;
			pos++;
			return elem;
			
		}
		
	}
	
	private class InnerListIterator implements ListIterator<E>
	{
		
		Element p;
		int pos = 0;
		
		public InnerListIterator()
		{
			
			pos = 0;
			p = sentinel.next;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return pos < size;
			
		}
		
		
		@Override
		public E next()
		{
			
			E elem = p.object;
			p = p.next;
			pos++;
			return elem;
			
		}
		
		
		@Override
		public void add(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			return pos > 0;
			
		}
		
		
		@Override
		public int nextIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public E previous()
		{
			
			pos--;
			p = p.prev;
			return p.object;
			
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
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		
		Element p = sentinel.next;
		while (p != sentinel && ((Comparable<E>) p.object).compareTo(e) <= 0) p = p.next;
		p.prev.addAfter(new Element(e));
		size++;
		return true;
		
	}
	
	
	private Element getElement(int index)
	{
		
		checkIndex(index);
		Element p = sentinel.next;
		while (index > 0)
		{
			index--;
			p = p.next;
		}
		return p;
		
	}
	
	
	private Element getElement(E obj)
	{
		
		Element p = sentinel.next;
		while (p != sentinel)
		{
			if (p.object.equals(obj)) return p;
			p = p.next;
		}
		return null;
		
	}
	
	
	@Override
	public void add(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	private void checkIndex(int index)
	{
		
		if (index < 0 || index >= size) throw new NoSuchElementException();
		
	}
	
	
	@Override
	public void clear()
	{
		
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
		
	}
	
	
	@Override
	public boolean contains(E element)
	{
		
		return indexOf(element) != -1;
		
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
		
		Element p = sentinel.next;
		int counter = 0;
		counter++;
		while (p != sentinel)
		{
			if (p.object.equals(element)) return counter;
			else
			{
				counter++;
				p = p.next;
			}
		}
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
		
		Element p = getElement(index);
		E retValue = p.object;
		p.remove();
		size--;
		return retValue;
		
	}
	
	
	@Override
	public boolean remove(E e)
	{
		
		Element p = getElement(e);
		if (p == null) return false;
		p.remove();
		size--;
		return true;
		
	}
	
	
	@Override
	public int size()
	{
		
		return size;
		
	}
	
	
	public String toStringReverse()
	{
		
		// TODO
		ListIterator<E> iter = new InnerListIterator();
		while (iter.hasNext()) iter.next();
		String retStr = "";
		while (iter.hasPrevious()) retStr += "\n" + iter.previous().toString();
		return retStr;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other)
	{
		
		if (this == other) return;
		if (other.isEmpty()) return;
		// this empty, but other not
		if (isEmpty())
		{
			sentinel.next = other.sentinel.next;
			sentinel.prev = other.sentinel.prev;
			size = other.size;
			other.clear();
		}
		// both not empty
		else
		{
			Element p1 = sentinel.next;
			Element p2 = other.sentinel.next;
			while (p1 != sentinel && p2 != other.sentinel)
			{
				if (((Comparable<E>) p1.object).compareTo(p2.object) <= 0)
				{
					p1 = p1.next;
				}
				else
				{
					p2 = p2.next;
					p1.prev.addAfter(p2.prev);
				}
			}
			while (p2 != other.sentinel)
			{
				p2 = p2.next;
				p1.prev.addAfter(p2.prev);
			}
			size += other.size;
			other.clear();
		}
		
	}
	
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public void removeAll(E e)
	{
		
		Element p = getElement(e);
		if (p == null) return;
		while (p != sentinel && ((Comparable) p.object).compareTo(e) == 0)
		{
			p.remove();
			p = p.next;
			size--;
		}
		
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
		this.weight = weight;
		
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		
		return ((Link) obj).ref.equalsIgnoreCase(ref);
		
	}
	
	
	@Override
	public String toString()
	{
		
		return ref + "(" + weight + ")";
		
	}
	
	
	@Override
	public int compareTo(Link another)
	{
		
		return ref.compareTo(another.ref);
		
	}
	
}

class Document
{
	
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	
	public Document(String name, Scanner scan)
	{
		
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
		
	}
	
	
	public void load(Scanner scan)
	{
		
		String marker = "link=";
		String endMarker = "eod";
		String line = scan.nextLine().toLowerCase();
		while (!line.equalsIgnoreCase(endMarker))
		{
			String arr[] = line.split(" ");
			for (String word : arr)
			{
				if (word.startsWith(marker))
				{
					String linkStr = word.substring(marker.length());
					Link l;
					if ((l = createLink(linkStr)) != null) link.add(l);
				}
				
			}
			line = scan.nextLine().toLowerCase();
		}
		
	}
	
	
	public static boolean isCorrectId(String id)
	{
		
		id = id.toLowerCase();
		if (id.length() == 0) return false;
		if (id.charAt(0) < 'a' || id.charAt(0) > 'z') return false;
		for (int i = 1; i < id.length(); i++)
		{
			if (!(id.charAt(i) >= 'a' && id.charAt(i) <= 'z' || id.charAt(i) >= '0' && id.charAt(i) <= '9'
					|| id.charAt(i) == '_'))
				return false;
		}
		return true;
		
	}
	
	
	private static Link createIdAndNumber(String id, int n)
	{
		
		if (!isCorrectId(id)) return null;
		return new Link(id.toLowerCase(), n);
		
	}
	
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	static Link createLink(String link)
	{
		
		if (link.length() == 0) return null;
		int openBracket = link.indexOf('(');
		int closeBracket = link.indexOf(')');
		if (openBracket > 0 && closeBracket > openBracket && closeBracket == link.length() - 1)
		{
			String strNumber = link.substring(openBracket + 1, closeBracket);
			try
			{
				int number = Integer.parseInt(strNumber);
				if (number < 1) return null;
				return createIdAndNumber(link.substring(0, openBracket), number);
			}
			catch (NumberFormatException ex)
			{
				return null;
			}
		}
		
		return createIdAndNumber(link, 1);
		
	}
	
	
	@Override
	public String toString()
	{
		
		String retStr = "Document: " + name;
		int counter = 0;
		for (Link linkElem : link)
		{
			if (counter % 10 == 0) retStr += "\n";
			else retStr += " ";
			retStr += linkElem.toString();
			counter++;
		}
		return retStr;
		
	}
	
	
	public String toStringReverse()
	{
		
		String retStr = "Document: " + name;
		int counter = 0;
		ListIterator<Link> iter = link.listIterator();
		while (iter.hasNext()) iter.next();
		while (iter.hasPrevious())
		{
			if (counter % 10 == 0) retStr += "\n";
			else retStr += " ";
			retStr += iter.previous().toString();
			counter++;
		}
		return retStr;
		
	}
	
	
	public int[] getWeights()
	{
		
		int[] arr = new int[link.size()];
		int i = 0;
		for (Link linkElem : link)
		{
			arr[i++] = linkElem.weight;
		}
		return arr;
		
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
		
		showArray(arr);
		for (int begin = 0; begin < arr.length - 1; begin++)
		{
			for (int j = arr.length - 1; j > begin; j--) if (arr[j - 1] > arr[j]) swap(arr, j - 1, j);
			showArray(arr);
		}
		
	}
	
	
	private void swap(int[] arr, int i, int j)
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
	
}
