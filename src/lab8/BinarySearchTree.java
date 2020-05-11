package lab8;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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

public class BinarySearchTree<T>
{
	
	// public static void main(String[] arts)
	// {
	//
	// BinarySearchTree bst = new BinarySearchTree();
	// System.out.println(bst.add(7));
	// System.out.println(bst.add(7));
	// bst.add(2);
	// System.out.println(bst.add(7));
	// bst.remove(1);
	// System.out.println(bst.toStringInOrder());
	// System.out.println(bst.toStringPostOrder());
	// System.out.println(bst.toStringPreOrder());
	//
	// }
	
	private class Node
	{
		
		T value;
		Node left = null, right = null, parent = null;
		
		public Node(T v)
		{
			
			value = v;
			
		}
		
		
		public Node(T value, Node left, Node right, Node parent)
		{
			
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
			
		}
		
		
		@Override
		public String toString()
		{
			
			String string = "";
			string += value.toString();
			// string += "value = " + value;
			// string += ", parent=" + (parent != null ? parent.value : "null");
			// string += ", right=" + (right != null ? right.value : "null");
			// string += ", left=" + (left != null ? left.value : "null");
			return string;
			
		}
		
	}
	
	private Node root;
	
	public BinarySearchTree()
	{
		
		root = null;
		
	}
	
	
	public T getElement(T toFind)
	{
		
		return getElement(root, toFind);
		
	}
	
	
	@SuppressWarnings("unchecked")
	private T getElement(Node start, T toFind)
	{
		
		// TODO
		Comparable toFindComparable = (Comparable) toFind;
		if (start == null)
		{
			return null;
		}
		
		if (toFindComparable.compareTo(toFind) == 0)
		{
			return start.value;
		}
		else if (toFindComparable.compareTo(start.value) < 0)
		{
			// searched value is smaller - look in the left subtree
			return getElement(start.left, toFind);
		}
		else
		{
			// searched value is larger - look in the right subtree
			return getElement(start.right, toFind);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	private Node findNode(Node start, T toFind)
	{
		
		// TODO
		Comparable toFindComparable = (Comparable) toFind;
		if (start == null)
		{
			return null;
		}
		
		if (toFindComparable.compareTo(start.value) == 0)
		{
			return start;
		}
		else if (toFindComparable.compareTo(start.value) < 0)
		{
			// searched value is smaller - look in the left subtree
			return findNode(start.left, toFind);
		}
		else
		{
			// searched value is larger - look in the right subtree
			return findNode(start.right, toFind);
		}
		
	}
	
	
	private T getMinimum(Node startNode)
	{
		
		if (root == null)
		{
			throw new NoSuchElementException();
		}
		
		return getMinimumNode(startNode).value;
		
	}
	
	
	private Node getMinimumNode(Node startNode)
	{
		
		Node currentNode = startNode;
		
		while (currentNode.left != null)
		{
			currentNode = currentNode.left;
		}
		
		return currentNode;
		
	}
	
	
	private T getMaximum(Node startNode)
	{
		
		if (root == null)
		{
			throw new NoSuchElementException();
		}
		
		return getMaximumNode(startNode).value;
		
	}
	
	
	private Node getMaximumNode(Node startNode)
	{
		
		Node currentNode = startNode;
		
		while (currentNode.right != null)
		{
			currentNode = currentNode.right;
		}
		
		return currentNode;
		
	}
	
	
	public T successor(T elem)
	{
		
		Node successor = findNode(root, elem);
		
		if (successor.right != null)
		{
			return getMinimum(successor.right);
		}
		
		Node currNode = successor.parent;
		while (currNode != null && successor == currNode.right)
		{
			successor = currNode;
			currNode = currNode.parent;
		}
		
		return currNode.value;
		
	}
	
	
	public String toStringInOrder()
	{
		
		String string = inOrder(root);
		if (string.length() < 2) return string;
		string = string.substring(0, string.length() - 2);
		return string;
		
	}
	
	
	private String inOrder(Node node)
	{
		
		String string = "";
		if (node == null) return string;
		string += inOrder(node.left);
		string += node.toString() + ", ";
		string += inOrder(node.right);
		return string;
		
	}
	
	
	public String toStringPreOrder()
	{
		
		String string = preOrder(root);
		if (string.length() < 2) return string;
		string = string.substring(0, string.length() - 2);
		return string;
		
	}
	
	
	private String preOrder(Node node)
	{
		
		String string = "";
		if (node == null) return string;
		string += node.toString() + ", ";
		string += preOrder(node.left);
		string += preOrder(node.right);
		return string;
		
	}
	
	
	public String toStringPostOrder()
	{
		
		String string = postOrder(root);
		if (string.length() < 2) return string;
		string = string.substring(0, string.length() - 2);
		return string;
		
	}
	
	
	private String postOrder(Node node)
	{
		
		String string = "";
		if (node == null) return string;
		string += postOrder(node.left);
		string += postOrder(node.right);
		string += node.toString() + ", ";
		return string;
		
	}
	
	
	public boolean add(T elem)
	{
		
		return add(root, elem);
		
	}
	
	
	@SuppressWarnings("unchecked")
	private boolean add(Node startNode, T elem)
	{
		// todo return false if already exists
		
		Node currNode = null;
		Node root = startNode;
		Node newNode = new Node(elem);
		
		while (root != null)
		{
			currNode = root;
			if (((Comparable) newNode.value).compareTo(root.value) < 0)
			{
				root = root.left;
			}
			else if (((Comparable) newNode.value).compareTo(root.value) > 0)
			{
				root = root.right;
			}
			else return false;
		}
		
		newNode.parent = currNode;
		if (currNode == null)
		{
			this.root = newNode;
			return true;
		}
		else if (((Comparable) newNode.value).compareTo(currNode.value) < 0)
		{
			// todo make sure it's correct
			currNode.left = newNode;
			return true;
		}
		else if (((Comparable) newNode.value).compareTo(currNode.value) > 0)
		{
			currNode.right = newNode;
			return true;
		}
		else return false;
		
	}
	
	
	public T remove(T value)
	{
		
		Node toDelete = findNode(root, value);
		if (toDelete == null) return null;
		T temp = toDelete.value;
		if (size() == 1) clear();
		
		// case 1 - no children
		else if (toDelete.left == null && toDelete.right == null)
		{
			if (toDelete.parent.left == toDelete) toDelete.parent.left = null;
			else toDelete.parent.right = null;
		}
		// case 2 - has one child
		else if (toDelete.left != null && toDelete.right == null)
		{
			if (toDelete == root) root = toDelete.left;
			else if (toDelete.parent.left == toDelete) toDelete.parent.left = toDelete.left;
			else toDelete.parent.right = toDelete.left;
		}
		else if (toDelete.right != null && toDelete.left == null)
		{
			if (toDelete == root) root = toDelete.right;
			else if (toDelete.parent.left == toDelete) toDelete.parent.left = toDelete.right;
			else toDelete.parent.right = toDelete.right;
		}
		// case 3 - has two children
		else
		{
			Node succesor = findNode(root, successor(toDelete.value));
			remove(succesor.value);
			toDelete.value = succesor.value;
		}
		return temp;
		
	}
	
	
	public void clear()
	{
		
		root = null;
		
	}
	
	
	public int size()
	{
		
		return size(root);
		
	}
	
	
	private int size(Node start)
	{
		
		if (start == null) return 0;
		int x = 1;
		x += size(start.left);
		x += size(start.right);
		return x;
		
	}
	
}
