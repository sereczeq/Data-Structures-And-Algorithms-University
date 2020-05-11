package lab8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest
{
	
	BinarySearchTree<Link> bst;
	
	@BeforeEach
	void beforeEach()
	{
		
		bst = new BinarySearchTree<Link>();
		
	}
	
	
	@Test
	void getElement()
	{
		
	}
	
	
	@Test
	void successor()
	{
		
	}
	
	
	@Test
	void inOrderWalk()
	{
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		tree.add(7);
		tree.add(5);
		tree.add(2);
		tree.add(10);
		tree.add(12);
		assertEquals(tree.toStringInOrder(), "2, 5, 7, 10, 12");
		
	}
	
	
	@Test
	void toStringInOrder()
	{
		
	}
	
	
	@Test
	void preOrderWalk()
	{
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		tree.add(7);
		tree.add(5);
		tree.add(2);
		tree.add(10);
		tree.add(12);
		
		assertEquals(tree.toStringPreOrder(), "7, 5, 2, 10, 12");
		
	}
	
	
	@Test
	void toStringPreOrder()
	{
		
	}
	
	
	@Test
	void postOrderWalk()
	{
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		tree.add(7);
		tree.add(5);
		tree.add(2);
		tree.add(10);
		tree.add(12);
		
		assertEquals(tree.toStringPostOrder(), "2, 5, 12, 10, 7");
		
	}
	
	
	@Test
	void toStringPostOrder()
	{
		
	}
	
	
	@Test
	void add()
	{
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		assertTrue(tree.add(7));
		assertTrue(tree.add(5));
		assertTrue(tree.add(2));
		assertTrue(tree.add(10));
		assertTrue(tree.add(12));
		
		assertFalse(tree.add(7));
		
	}
	
	
	@Test
	void remove()
	{
		
	}
	
	
	@Test
	void clear()
	{
		
	}
	
	
	@Test
	void size()
	{
		
	}
	
}
