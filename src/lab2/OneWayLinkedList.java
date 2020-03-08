package lab2;

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

class OneWayLinkedList<E> implements IList<E> // ready
{
	
	private class Element
	{
		
		public Element(E e)
		{
			
			this.object = e;
			
		}
		
		E object;
		Element next = null;
		
	}
	
	Element sentinel;
	
	private class InnerIterator implements Iterator<E> // ready
	{
		
		Element elem;
		
		public InnerIterator()
		{
			
			elem = sentinel;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return elem.next != null;
			
		}
		
		
		// @SuppressWarnings("unchecked")
		@Override
		public E next() throws NullPointerException
		{
			
			if (hasNext())
			{
				elem = elem.next;
				return (E) elem;
			}
			else throw new NullPointerException("InnerIterator.next");
			
		}
		
	}
	
	public OneWayLinkedList()
	{
		
		// make a sentinel
		sentinel = new Element(null);
		
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
	public boolean add(E e)
	{
		
		sentinel.next = new Element(e);
		return true;
		
	}
	
	
	@Override
	public void add(int index, E element) throws NoSuchElementException
	{
		
		Iterator<E> it = this.iterator();
		for (int x = 0; x < index; x++)
		{
			if (it.hasNext()) it.next();
			else throw new NoSuchElementException("add");
		}
		E temp = (E) sentinel;
		sentinel.object = element;
		sentinel.next = (OneWayLinkedList<E>.Element) temp;
		// TODO: fix classes and casting
		
	}
	
	
	@Override
	public void clear()
	{
		
		sentinel.object = null;
		sentinel.next = null;
		
	}
	
	
	@Override
	public boolean contains(E element)
	{
		
		Iterator<E> it = this.iterator();
		while (it.hasNext())
		{
			if (it.next() == element) return true;
		}
		return false;
		
	}
	
	
	@Override
	public E get(int index) throws NoSuchElementException
	{
		
		Iterator<E> it = this.iterator();
		for (int x = 0; x < index && it.hasNext(); x++)
		{
			if (x == index) return it.next();
		}
		throw new NoSuchElementException("get");
		
	}
	
	
	@Override
	public E set(int index, E element) throws NoSuchElementException
	{
		
		Iterator<E> it = this.iterator();
		for (int x = 0; x < index && it.hasNext(); x++)
		{
			if (x == index)
			{
				E temp = sentinel.object;
				sentinel.object = element;
				return temp;
			}
		}
		throw new NoSuchElementException("set");
		
	}
	
	
	@Override
	public int indexOf(E element)
	{
		
		Iterator<E> it = this.iterator();
		for (int x = 0; it.hasNext(); x++)
		{
			if (it.next() == element) return x;
		}
		return -1;
		// TODO: exception maybe?
		
	}
	
	
	@Override
	public boolean isEmpty()
	{
		
		return sentinel.object == null && sentinel.next == null;
		
	}
	
	
	@Override
	public E remove(int index) throws NoSuchElementException
	{
		
		return set(index, null);
		
	}
	
	
	@Override
	public boolean remove(E e)
	{
		
		int temp = indexOf(e);
		if (temp == -1) return false;
		remove(temp);
		return true;
		
	}
	
	
	@Override
	public int size()
	{
		
		Iterator<E> it = this.iterator();
		int x = 0;
		for (; it.hasNext(); x++) it.next();
		return x;
		
	}
	
}

class Link // ready
{
	
	public String ref;
	
	public Link(String ref)
	{
		
		this.ref = ref;
		
	}
	
	
	@Override
	public String toString()
	{
		
		return ref;
		
	}
	
	// in the future there will be more fields
}

class Document // ready
{
	
	public String name;
	public OneWayLinkedList<Link> links = new OneWayLinkedList<Link>(); // changed stuff myself
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
		
		String s = "Document: " + name + " contains: \n";
		s += links.toString();
		return s;
		
	}
	
}
