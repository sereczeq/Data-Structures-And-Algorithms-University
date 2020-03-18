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

// There was no tail in source code so every mention of tail is added by me :D
class TwoWayLinkedListWithHead<E> implements IList<E>
{
	
	private class Element
	{
		
		E object;
		Element next = null;
		Element prev = null;
		
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
		
	}
	
	Element head = new Element(null);
	Element tail = new Element(null);
	// can be realization with the field size or without
	int size;
	
	private class InnerIterator implements Iterator<E>
	{
		
		Element pos;
		// TODO maybe more fields....
		
		public InnerIterator()
		{
			
			pos = head;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return pos.next != tail;
			
		}
		
		
		@Override
		public E next()
		{
			
			if (hasNext())
			{
				pos = pos.next;
				return pos.object;
			}
			else throw new NoSuchElementException();
			
		}
		
	}
	
	private class InnerListIterator implements ListIterator<E>
	{
		
		Element curr = head; // renamed "p" to "curr"
		// TODO maybe more fields....
		
		@Override
		public void add(E e)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return curr.next != tail;
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			return curr.prev != head && curr.prev != null;
			
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
		public void set(E e)
		{
			
			curr.object = e;
			
		}
		
	}
	
	public TwoWayLinkedListWithHead()
	{
		
		// make a head
		head.next = tail;
		tail.prev = head;
		
	}
	
	
	@Override
	public boolean add(E e)
	{
		
		Element elem = new Element(e, tail, tail.prev);
		tail.prev.next = elem;
		tail.prev = elem;
		return true;
		
	}
	
	
	@Override
	public void add(int index, E e)
	{
		
		// TODO: fix counting probably
		Element temp = head.next;
		for (int x = 0; x < index; x++)
		{
			if (temp.next == null || index < 0) throw new NoSuchElementException();
		}
		temp.next = new Element(e, temp.next, temp.prev);
		
	}
	
	
	@Override
	public void clear()
	{
		
		head = new Element(null);
		tail = new Element(null);
		head.next = tail;
		tail.prev = head;
		
	}
	
	
	@Override
	public boolean contains(E e)
	{
		
		Iterator<E> it = this.iterator();
		while (it.hasNext())
		{
			if (it.next() == e) return true;
		}
		return false;
		
	}
	
	
	@Override
	public E get(int index)
	{
		
		if (index > size() || index < 0) throw new NoSuchElementException();
		Iterator<E> it = this.iterator();
		for (int x = 0; x < index; x++)
		{
			it.next();
		}
		return it.next();
		
	}
	
	
	@Override
	public E set(int index, E e)
	{
		
		E temp = remove(index);
		add(index, e);
		return temp;
		
	}
	
	
	@Override
	public int indexOf(E e)
	{
		
		Iterator<E> it = this.iterator();
		for (int x = 0; it.hasNext(); x++)
		{
			if (it.next().equals(e)) return x;
		}
		return -1;
		
	}
	
	
	@Override
	public boolean isEmpty()
	{
		
		return head.next == tail;
		
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
	public E remove(int index) throws NoSuchElementException
	{
		
		if (index > size() || index < 0) throw new NoSuchElementException();
		Element temp = head.next;
		for (int x = 0; x < index; x++)
		{
			temp = temp.next;
		}
		E tempE = temp.object;
		temp.prev.next = temp.next;
		return tempE;
		
	}
	
	
	@Override
	public boolean remove(E e)
	{
		
		remove(indexOf(e));
		return true;
		
	}
	
	
	@Override
	public int size()
	{
		
		int x = 0;
		Iterator it = this.iterator();
		while (it.hasNext())
		{
			x++;
			it.next();
		}
		return x;
		
	}
	
	
	public String toStringReverse()
	{
		
		// changes signature of this method
		String retStr = "";
		ListIterator<E> iter = new InnerListIterator();
		while (iter.hasNext()) retStr = "\n" + iter.next();
		// TODO use reverse direction of the iterator DONE
		while (iter.hasPrevious()) retStr += "\n" + iter.previous();
		return retStr;
		
	}
	
	
	@Override
	public String toString()
	{
		
		String s = "";
		Iterator<E> it = this.iterator();
		while (it.hasNext()) s += "\n" + it.next();
		return s;
		
	}
	
	
	public void add(TwoWayLinkedListWithHead<E> other)
	{
		
		if (this.equals(other)) return;
		// TODO DONE
		for (E e : other) add(e);
		other.clear();
		
	}
	
}

class Link
{
	
	public String ref;
	
	public Link(String ref)
	{
		
		this.ref = ref;
		
	}
	
	
	@Override
	public boolean equals(Object other)
	{
		
		if (other.getClass() != this.getClass()) return false;
		Link temp = (Link) other;
		return ref.contentEquals(temp.ref);
		
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
	
	
	public String toStringReverse()
	{
		
		String s = "Document: " + name;
		s += links.toStringReverse();
		return s;
		
	}
	
}
