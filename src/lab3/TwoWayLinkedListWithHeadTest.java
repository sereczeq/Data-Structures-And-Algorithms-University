package lab3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TwoWayLinkedListWithHeadTest
{
	
	private final Link a = new Link("a");
	private final Link b = new Link("b");
	private final Link c = new Link("c");
	private TwoWayLinkedListWithHead<Link> links = new TwoWayLinkedListWithHead<Link>();
	
	@AfterEach
	void beforeEach()
	{
		
		links.clear();
		
	}
	
	
	@Test
	void testAddE()
	{
		
		assertTrue(links.add(a));
		assertTrue(links.add(b));
		assertTrue(links.add(c));
		assertEquals("\na\nb\nc", links.toString());
		
	}
	
	
	@Test
	void testAddIntE()
	{
		
		assertThrows(NoSuchElementException.class, () -> links.add(2, a));
		assertThrows(NoSuchElementException.class, () -> links.add(-1, a));
		links.add(0, a);
		assertEquals("\na", links.toString());
		assertThrows(NoSuchElementException.class, () -> links.add(2, a));
		assertThrows(NoSuchElementException.class, () -> links.add(-1, a));
		links.add(1, b);
		assertEquals("\na\nb", links.toString());
		links.add(1, c);
		assertEquals("\na\nc\nb", links.toString());
		
	}
	
	
	@Test
	void testContains()
	{
		
		assertFalse(links.contains(a));
		assertFalse(links.contains(null));
		links.add(a);
		assertTrue(links.contains(a));
		assertFalse(links.contains(b));
		
	}
	
	
	@Test
	void testGet()
	{
		
		assertThrows(NoSuchElementException.class, () -> links.get(0));
		assertThrows(NoSuchElementException.class, () -> links.get(1));
		assertThrows(NoSuchElementException.class, () -> links.get(-1));
		links.add(a);
		links.add(b);
		links.add(c);
		assertEquals(b, links.get(1));
		assertThrows(NoSuchElementException.class, () -> links.get(10));
		assertThrows(NoSuchElementException.class, () -> links.get(3));
		assertThrows(NoSuchElementException.class, () -> links.get(-1));
		
	}
	
	
	@Test
	void testSet()
	{
		
		assertThrows(NoSuchElementException.class, () -> links.set(0, a));
		assertThrows(NoSuchElementException.class, () -> links.set(1, a));
		assertThrows(NoSuchElementException.class, () -> links.set(-1, a));
		links.add(a);
		links.add(a);
		links.add(c);
		assertEquals(a, links.set(1, b));
		assertEquals("\na\nb\nc", links.toString());
		assertThrows(NoSuchElementException.class, () -> links.set(10, a));
		assertThrows(NoSuchElementException.class, () -> links.set(3, a));
		assertThrows(NoSuchElementException.class, () -> links.set(-1, a));
		
	}
	
	
	@Test
	void testIndexOf()
	{
		
		assertEquals(-1, links.indexOf(a));
		links.add(a);
		links.add(b);
		links.add(c);
		assertEquals(0, links.indexOf(a));
		assertEquals(1, links.indexOf(b));
		assertEquals(2, links.indexOf(c));
		
	}
	
	
	@Test
	void testIsEmpty()
	{
		
		assertTrue(links.isEmpty());
		links.add(a);
		assertFalse(links.isEmpty());
		
	}
	
	
	@Test
	void testRemoveInt()
	{
		
		assertThrows(NoSuchElementException.class, () -> links.remove(0));
		assertThrows(NoSuchElementException.class, () -> links.remove(-1));
		assertThrows(NoSuchElementException.class, () -> links.remove(1));
		assertThrows(NoSuchElementException.class, () -> links.remove(5));
		links.add(a);
		links.add(b);
		links.add(c);
		assertEquals(a, links.remove(0));
		assertEquals(c, links.remove(1));
		assertEquals(b, links.remove(0));
		assertTrue(links.isEmpty());
		
	}
	
	
	@Test
	void testRemoveE()
	{
		
		assertThrows(NoSuchElementException.class, () -> links.remove(a));
		links.add(a);
		assertThrows(NoSuchElementException.class, () -> links.remove(b));
		assertTrue(links.remove(a));
		assertTrue(links.isEmpty());
		
	}
	
	
	@Test
	void testSize()
	{
		
		assertEquals(0, links.size());
		links.add(a);
		assertEquals(1, links.size());
		links.clear();
		assertEquals(0, links.size());
		
	}
	
	
	@Test
	void testToStringReverse()
	{
		
		assertEquals("", links.toStringReverse());
		links.add(a);
		assertEquals("\na", links.toStringReverse());
		links.add(b);
		links.add(c);
		assertEquals("\nc\nb\na", links.toStringReverse());
		
	}
	
	
	@Test
	void testAddTwoWayLinkedListWithHeadOfE()
	{
		
		links.add(a);
		links.add(b);
		TwoWayLinkedListWithHead<Link> links2 = links;
		links.add(links);
		// if the same nothing happens
		assertEquals(links, links2);
		links2 = new TwoWayLinkedListWithHead<>();
		links2.add(c);
		links2.add(b);
		links.add(links2);
		// after adding empty second list
		assertTrue(links2.isEmpty());
		assertEquals("\na\nb\nc\nb", links.toString());
		// adding an empty list
		links2.clear();
		links.add(links2);
		assertEquals("\na\nb\nc\nb", links.toString());
		// adding to an empty list
		links2.add(links);
		assertEquals("\na\nb\nc\nb", links2.toString());
		// adding a completely new list
		links = new TwoWayLinkedListWithHead<>();
		links2.add(links);
		assertEquals("\na\nb\nc\nb", links2.toString());
		// not working because some final stuff
		// //adding null
		// links2 = null;
		// links.add(a);
		// links.add(b);
		// assertThrows(NullPointerException.class, () -> links.add(links2));
		
	}
	
}