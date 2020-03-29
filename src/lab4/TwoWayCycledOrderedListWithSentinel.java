package lab4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			elem.next = next;
			
		}
		
		
		// assert it is NOT a sentinel
		public void remove() throws UnsupportedOperationException
		{
			
			if (object == null) throw new UnsupportedOperationException("can't remove a sentinel");
			if (prev != null) prev.next = next;
			if (next != null) next.prev = prev;
			
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
			
			return curr.next != null;
			
		}
		
		
		@Override
		public E next()
		{
			
			if (hasNext())
			{
				curr = curr.next;
				return curr.object;
			}
			else throw new NoSuchElementException();
			
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
			
			return curr.next != null;
			
		}
		
		
		@Override
		public E next()
		{
			
			if (hasNext())
			{
				curr = curr.next;
				return curr.object;
			}
			else throw new NoSuchElementException();
			
		}
		
		
		@Override
		public void add(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			return curr.prev != null;
			
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
				return curr.object;
			}
			else throw new NoSuchElementException();
			
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
		
	}
	
	
	// @SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		
		if (isEmpty())
		{
			Element elem = new Element(e);
			elem.prev = elem;
			elem.next = elem;
			sentinel.next = elem;
			return true;
		}
		Element temp = sentinel.next;
		Element elem = new Element(e, temp.prev, temp);
		sentinel.next = elem;
		temp.prev.next = elem;
		temp.prev = elem;
		return true;
		
	}
	
	
	private Element getElement(int index)
	{
		
		Element temp = sentinel.next;
		for (int x = 0; temp.next != null && x < size(); x++, temp = temp.next) if (x == index) return temp;
		throw new NoSuchElementException();
		
	}
	
	
	private Element getElement(E obj)
	{
		
		return (getElement(indexOf(obj)));
		
	}
	
	
	@Override
	public void add(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	@Override
	public void clear()
	{
		
		sentinel.next = null;
		
	}
	
	
	@Override
	public boolean contains(E element)
	{
		
		return indexOf(element) > -1;
		
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
		
		Element temp = sentinel.next;
		for (int x = 0; temp.next != null && x < size(); x++, temp = temp.next) if (temp == element) return x;
		throw new NoSuchElementException();
		
	}
	
	
	@Override
	public boolean isEmpty()
	{
		
		return sentinel.next == null;
		
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
		
		Element temp = sentinel.next;
		for (int x = 0; temp.next != null && x < size(); x++, temp = temp.next) if (x == index)
		{
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
		}
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
		
		Element temp = sentinel.next;
		int x = 0;
		for (x = 0; temp.next != null && x < size(); x++, temp = temp.next);
		return x;
		
	}
	
	
	// @SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other)
	{
		
		for (E elem : other) add(elem);
		
	}
	
	
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e)
	{
		
		clear();
		
		// TODO: not sure if this works
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
		
		if (obj instanceof Link) return ref.contentEquals(((Link) obj).ref);
		return false;
		
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
	final static Pattern regex = Pattern.compile("(link=)(?<ref>[a-z][\\w_]*)(?<id>\\((?<number>[0-9-]*)\\))?");
	
	public Document(String name, Scanner scan)
	{
		
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
		
	}
	
	
	public void load(Scanner scan)
	{
		
		while (scan.hasNext())
		{
			String line = scan.next().toLowerCase();
			if (line.contains("eod")) break;
			if (correctLink(line)) link.add(createLink(line));
		}
		
	}
	
	
	private static boolean correctLink(String line)
	{
		
		Matcher matcher = regex.matcher(line);
		return matcher.find();
		
	}
	
	
	static boolean isCorrectId(String id)
	{
		
		return true;
		// TODO: what is this method supposed to do?
		
	}
	
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	// and eventually weight in parenthesis
	static Link createLink(String line)
	{
		
		String s = line.split("=")[1].split("\\(")[0];
		int x = 1;
		try
		{
			x = Integer.parseInt(line.split("\\(")[1].split("\\)")[0]);
			if (x < 0) x = 1;
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
		{
		}
		return new Link(s, x);
		
	}
	
	
	@Override
	public String toString()
	{
		
		String retStr = "Document: " + name;
		retStr += link.toString();
		return retStr;
		
	}
	
	
	public String toStringReverse()
	{
		
		String retStr = "Document: " + name;
		ListIterator<Link> iter = link.listIterator();
		Link first = iter.next();
		Link elem = first;
		while (iter.hasNext() && !elem.equals(first)) elem = iter.next();
		retStr += "\n" + elem;
		while (iter.hasPrevious())
		{
			Link temp = iter.previous();
			retStr += "\n" + temp;
			if (temp.equals(first)) break;
			
		}
		return retStr;
		
	}
	
}
