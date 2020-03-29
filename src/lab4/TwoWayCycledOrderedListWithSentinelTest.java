package lab4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TwoWayCycledOrderedListWithSentinelTest
{
	
	private static TwoWayCycledOrderedListWithSentinel<Link> links = new TwoWayCycledOrderedListWithSentinel<Link>();
	private static final Link a = new Link("a", 1);
	private static final Link b = new Link("b", 2);
	private static final Link c = new Link("c", 3);
	private static final Link[] all = {a, b, c };
	private static final String abc = "\na(1)\nb(2)\nc(3)";
	
	/*
	 * @BeforeAll static void setUpBeforeClass() throws Exception {
	 * 
	 * }
	 * 
	 * 
	 * @AfterAll static void tearDownAfterClass() throws Exception {
	 * 
	 * }
	 * 
	 * 
	 * @BeforeEach static void beforeEach() {
	 * 
	 * links = new TwoWayCycledOrderedListWithSentinel<Link>();
	 * 
	 * }
	 */
	
	@Test
	void testAddE()
	{
		
		assertTrue(links.add(a));
		assertTrue(links.add(c));
		assertTrue(links.add(b));
		assertEquals(links.toString(), abc);
		
	}
	
	
	@Test
	void testClear()
	{
		
		links.add(a);
		links.add(c);
		links.add(b);
		links.clear();
		assertEquals(links.toString(), "");
		// does clearing empty cause an exception?
		links.clear();
		
	}
	
	
	@Test
	void testContains()
	{
		
		for (Link link : all)
		{
			links.add(link);
			assertTrue(links.contains(link));
		}
		for (Link link : all) assertTrue(links.contains(link));
		links.clear();
		assertFalse(links.contains(a));
		links.add(a);
		links.add(b);
		assertFalse(links.contains(c));
		
	}
	
	
	@Test
	void testGet()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
	
	@Test
	void testIndexOf()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
	
	@Test
	void testIterator()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
	
	@Test
	void testListIterator()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
	
	@Test
	void testRemoveInt()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
	
	@Test
	void testSize()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
	
	@Test
	void testAddTwoWayCycledOrderedListWithSentinelOfE()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
	
	@Test
	void testRemoveAll()
	{
		
		fail("Not yet implemented"); // TODO
		
	}
	
}
