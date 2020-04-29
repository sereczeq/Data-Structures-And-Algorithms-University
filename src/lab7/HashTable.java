package lab7;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

class HashTable
{
	
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize = 8;
	private final static double defaultMaxLoadFactor = 0.7;
	private int size;
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
		
	}
	
	
	public boolean add(Object elem)
	{
		
		// TODO
		return true;
		
	}
	
	
	private void doubleArray()
	{
		
		// TODO
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
		
		// TODO
		return null;
		
	}
	
}

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>
{
	
	private class Element
	{
		
		public Element(E e)
		{
			
			// TODO
		}
		
		
		public Element(E e, Element next, Element prev)
		{
			
			// TODO
		}
		
		
		// add element e after this
		public void addAfter(Element elem)
		{
			
			// TODO
		}
		
		
		// assert it is NOT a sentinel
		public void remove()
		{
			
			// TODO
		}
		
		E object;
		Element next = null;
		Element prev = null;
		
	}
	
	Element sentinel;
	int size;
	
	private class InnerIterator implements Iterator<E>
	{
		
		// TODO
		public InnerIterator()
		{
			
			// TODO
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			// TODO
			return false;
			
		}
		
		
		@Override
		public E next()
		{
			
			// TODO
			return null;
			
		}
		
	}
	
	private class InnerListIterator implements ListIterator<E>
	{
		
		// TODO
		public InnerListIterator()
		{
			
			// TODO
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			// TODO
			return false;
			
		}
		
		
		@Override
		public E next()
		{
			
			// TODO
			return null;
			
		}
		
		
		@Override
		public void add(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			// TODO
			return false;
			
		}
		
		
		@Override
		public int nextIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public E previous()
		{
			
			// TODO
			return null;
			
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
		
		// TODO
	}
	
	
	// @SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		
		// TODO
		return false;
		
	}
	
	
	private Element getElement(int index)
	{
		
		// TODO
		return null;
		
	}
	
	
	private Element getElement(E obj)
	{
		
		// TODO
		return null;
		
	}
	
	
	@Override
	public void add(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	@Override
	public void clear()
	{
		
		// TODO
	}
	
	
	@Override
	public boolean contains(E element)
	{
		
		// TODO
		return false;
		
	}
	
	
	@Override
	public E get(int index)
	{
		
		// TODO
		return null;
		
	}
	
	
	@Override
	public E set(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	@Override
	public int indexOf(E element)
	{
		
		// TODO
		return -1;
		
	}
	
	
	@Override
	public boolean isEmpty()
	{
		
		// TODO
		return true;
		
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
		
		// TODO
		return null;
		
	}
	
	
	@Override
	public boolean remove(E e)
	{
		
		// TODO
		return false;
		
	}
	
	
	@Override
	public int size()
	{
		
		// TODO
		return -1;
		
	}
	
	
	// @SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other)
	{
		
		// TODO
	}
	
	
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e)
	{
		
		// TODO
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
		
		// TODO
		return false;
		
	}
	
	
	@Override
	public String toString()
	{
		
		return ref + "(" + weight + ")";
		
	}
	
	
	@Override
	public int compareTo(Link another)
	{
		
		// TODO
		return 0;
		
	}
	
}

class Document implements IWithName
{
	
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	
	public Document(String name)
	{
		
		// TODO
	}
	
	
	public Document(String name, Scanner scan)
	{
		
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
		
	}
	
	
	public void load(Scanner scan)
	{
		
		// TODO
	}
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	
	
	public static boolean isCorrectId(String id)
	{
		
		// TODO
		return false;
		
	}
	
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	public static Link createLink(String link)
	{
		
		// TODO
		return null;
		
	}
	
	
	@Override
	public String toString()
	{
		
		String retStr = "Document: " + name;
		// TODO
		return retStr;
		
	}
	
	
	public String toStringReverse()
	{
		
		String retStr = "Document: " + name;
		ListIterator<Link> iter = link.listIterator();
		while (iter.hasNext()) iter.next();
		// TODO
		while (iter.hasPrevious())
		{
		}
		return retStr;
		
	}
	
	
	@Override
	public String getName()
	{
		
		// TODO
		return null;
		
	}
	
}


