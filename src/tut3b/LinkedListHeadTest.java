package tut3b;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class LinkedListHeadTest {

    LinkedListHead<Integer> list;
    Iterator<Integer> iter;

    @Before
    public void setUp() throws Exception {
        list = new LinkedListHead<Integer>();
        iter = list.iterator();

    }

    @Test
    public void NewListTest() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        try {
            list.get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // OK
        }
        try {
            list.get(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // OK
        }
        iter = list.iterator();
        assertFalse(iter.hasNext());
    }

    @Test
    public void iteratorOneElemTest() {
        list.add(10);
        for (Integer x : list) {
            assertEquals(new Integer(10), x);
        }
        iter = list.iterator();
        assertTrue(iter.hasNext());
        int x = iter.next();
        assertEquals(10, x);
        assertFalse(iter.hasNext());
    }


    @Test
    public void iteratorThreeElemTest() {
        list.add(10);
        list.add(20);
        list.add(30);
        int factor = 0;
        for (Integer x : list) {
            assertEquals(new Integer(++factor * 10), x);
        }
        iter = list.iterator();
        assertTrue(iter.hasNext());
        int x = iter.next();
        assertEquals(10, x);
        assertTrue(iter.hasNext());
        x = iter.next();
        assertEquals(20, x);
        assertTrue(iter.hasNext());
        x = iter.next();
        assertEquals(30, x);
        assertFalse(iter.hasNext());
    }


    @Test
    public void insertAtZero() {
        list.add(0, 10);
        list.add(0, 9);
        list.add(0, 8);
        assertEquals("[8, 9, 10]", list.toString());
    }

}
