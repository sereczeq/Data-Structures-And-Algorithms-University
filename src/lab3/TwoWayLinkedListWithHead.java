package lab3;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface IList<E> extends Iterable<E>
{
	
	boolean add(E e); // qdd element to the end of list
	
	void add(int index, E element) throws NoSuchElementException; // add element on position index
	
	void clear(); // delete all elements
	
	boolean contains(E element); // is list containing an element (equals())
	
	E get(int index) throws NoSuchElementException; // get element from position
	
	E set(int index, E element) throws NoSuchElementException; // set new value on position
	
	int indexOf(E element); // where is element (equals())
	
	boolean isEmpty();
	
	Iterator<E> iterator();
	
	ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator
	
	E remove(int index) throws NoSuchElementException; // remove element from position index
	
	boolean remove(E e); // remove element
	
	int size();
	
}

class TwoWayLinkedListWithHead<E> implements IList<E>
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
		
		E object;
		Element next = null;
		Element prev = null;
		
	}
	
	Element head;
	// can be realization with the field size or without
	int size;
	
	private class InnerIterator implements Iterator<E>
	{
		
		Element pos;
		// TODO maybe more fields....
		
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
		
		Element p;
		// TODO maybe more fields....
		
		@Override
		public void add(E e)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			// TODO Auto-generated method stub
			return false;
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			// TODO Auto-generated method stub
			return false;
			
		}
		
		
		@Override
		public E next()
		{
			
			// TODO Auto-generated method stub
			return null;
			
		}
		
		
		@Override
		public int nextIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public E previous()
		{
			
			// TODO Auto-generated method stub
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
		public void set(E e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public TwoWayLinkedListWithHead()
	{
		
		// make a head
		head = null;
		
	}
	
	
	@Override
	public boolean add(E e)
	{
		// TODO
		
		return true;
		
	}
	
	
	@Override
	public void add(int index, E element)
	{
		
		// TODO
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
		
		// TODO
		return null;
		
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
		
		return false;
		
	}
	
	
	@Override
	public Iterator<E> iterator()
	{
		
		return new InnerIterator();
		
	}
	
	
	@Override
	public ListIterator<E> listIterator()
	{
		
		throw new UnsupportedOperationException();
		
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
		return true;
		
	}
	
	
	@Override
	public int size()
	{
		
		// TODO
		return -1;
		
	}
	
	
	public String toStringReverse()
	{
		
		ListIterator<E> iter = new InnerListIterator();
		while (iter.hasNext()) iter.next();
		String retStr = "";
		// TODO use reverse direction of the iterator
		return retStr;
		
	}
	
	
	public void add(TwoWayLinkedListWithHead<E> other)
	{
		
		// TODO
	}
	
}

class Link
{
	
	public String ref;
	
	// in the future there will be more fields
	public Link(String ref)
	{
		
		this.ref = ref;
		
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		
		// TODO
		return true;
		
	}
	
}

class Document // ready
{
	
	public String name;
	public TwoWayLinkedListWithHead<Link> links = new TwoWayLinkedListWithHead<Link>(); // changed stuff myself
	// anything below added myself
	final static Pattern regex = Pattern.compile("link=[a-z]\\w*");
	
	public Document(String name, Scanner scan)
	{
		
		this.name = name;
		load(scan);
		
	}
	
	
	public void load(Scanner scan)
	{
		
		while (scan.hasNext())
		{
			String line = scan.next().toLowerCase();
			if (line.contains("eod")) break;
			if (correctLink(line)) links.add(new Link(line.split("=")[1]));
		}
		
	}
	
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	private static boolean correctLink(String link)
	{
		
		Matcher matcher = regex.matcher(link);
		return matcher.find();
		
	}
	
	
	@Override
	public String toString()
	{
		
		String s = "Document: " + name;
		s += links.toString();
		return s;
		
	}
	
}
