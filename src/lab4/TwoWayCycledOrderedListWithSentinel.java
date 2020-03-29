package lab4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * All for loops with isEmpty() in front of them are just me being lazy
 * TODO: change that to something normal or make a single method
 */

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
			elem.prev = this;
			temp.prev = elem;
			elem.next = temp;
			
		}
		
		
		// assert it is NOT a sentinel
		public void remove()
		{
			
			if (object == null) throw new NoSuchElementException("can't remove sentinel / element with null object");
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
			
			return curr.next != null;
			
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
		
		sentinel = new Element(null, sentinel, sentinel);
		
	}
	
	
	// @SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		
		if (isEmpty()) sentinel.next = sentinel.prev = new Element(e, sentinel, sentinel);
		else
		{
			sentinel.next.addAfter(new Element(e));
		}
		return true;
		
	}
	
	
	private Element getElement(int index)
	{
		
		if (isEmpty()) throw new NoSuchElementException();
		Element elem = sentinel.next;
		for (int x = 0; elem.next != null && elem != sentinel && x <= index; x++, elem = elem.next)
			if (x == index && elem != sentinel) return elem;
		throw new NoSuchElementException();
		
	}
	
	
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
		
		if (isEmpty()) return false;
		for (Element elem = sentinel.next; elem.next != null && elem != sentinel; elem = elem.next)
			if (elem.object == element) return true;
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
		for (int x = 0; x < size(); x++)
		{
			if (it.next() == element) return x;
		}
		throw new NoSuchElementException();
		
	}
	
	
	@Override
	public boolean isEmpty()
	{
		
		return sentinel.next == null || sentinel.next == sentinel;
		
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
		
		Element elem = sentinel;
		for (int x = -1; elem.next != null && x <= index; x++, elem = elem.next) if (x == index && elem != sentinel)
		{
			elem.next.prev = elem.prev;
			elem.prev.next = elem.next;
			return elem.object;
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
		
		int x = 0;
		for (Element elem = sentinel; elem.next != null && elem.next != sentinel; x++, elem = elem.next);
		return x;
		
	}
	
	
	@Override
	public boolean equals(Object other)
	{
		
		return toString().contentEquals(other.toString());
		
	}
	
	
	// @SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other)
	{
		
		if (equals(other)) return;
		while (other.size() > 0)
		{
			Element elem = new Element(other.remove(0), sentinel, sentinel.prev);
			sentinel.prev.next = elem;
			sentinel.prev = elem;
			
		}
		
	}
	
	
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e)
	{
		
		while (contains(e)) remove(e);
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		Iterator<E> it = iterator();
		for (int x = 0; x < size(); x++)
		{
			string += "\n" + it.next();
		}
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
		Iterator it = link.iterator();
		for (int x = 0; it.hasNext() && x < link.size(); x++)
		{
			if (x == 0 || x == 9) retStr += "\n";
			retStr += it.next();
			if (it.hasNext() || x != 8) retStr += " ";
		}
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
