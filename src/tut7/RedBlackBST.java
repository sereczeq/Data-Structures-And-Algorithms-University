package tut7;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class RedBlackBST<T>
{
	
	class Node
	{
		
		T value;
		Node left;
		Node right;
		Node parent;
		String color;
		
		Node(T value)
		{
			
			this.value = value;
			
		}
		
		
		Node(T value, Node right, Node left, Node parent)
		{
			
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
			
		}
		
	}
	
	private final Comparator<T> comparator;
	private Node root;
	
	RedBlackBST(Comparator<T> comparator)
	{
		
		this.comparator = comparator;
		root = null;
		
	}
	
	private int noNodesLeft = 0;
	private int noNodesRight = 0;
	private int noLeafsLeft = 0;
	private int noLeafsRight = 0;
	
	// 1.a) find maximum
	public T max()
	{
		
		return find(max(root).value);
		
	}
	
	
	private Node max(Node node)
	{
		
		if (node.right != null) node = max(node.right);
		return node;
		
	}
	
	
	private T find(T elem)
	{
		
		Node node = search(elem);
		return node == null ? null : node.value;
		
	}
	
	
	public Node search(T elem)
	{
		
		Node node = root;
		int cmp = 0;
		while (node != null && (cmp = comparator.compare(elem, node.value)) != 0)
		{
			node = cmp < 0 ? node.left : node.right;
		}
		return node;
		
	}
	
	
	// 1.b)find minimum
	public T min()
	{
		
		return find(min(root).value);
		
	}
	
	
	private Node min(Node node)
	{
		
		while (node.left != null)
		{
			node = node.left;
		}
		return node;
		
	}
	
	
	// 2.a) find successor
	public T findSuccessor(T elem)
	{
		
		// case 1
		if (elem == max()) return null;
		if (!exist(elem)) return null;
		return findSuccessor(search(elem)).value;
		
	}
	
	
	public boolean exist(T elem)
	{
		
		if (search(elem) != null) return true;
		else return false;
		
	}
	
	
	private Node findSuccessor(Node node)
	{
		
		Node successor = null;
		// case 2:
		if (node.right != null) return min(node.right);
		// case 3:
		successor = node.parent;
		while (successor != null && node == successor.right)
		{
			node = successor;
			successor = successor.parent;
		}
		return successor;
		
	}
	
	
	// 2.b) find predecessor
	public T findPredecessor(T elem)
	{
		
		// case 1
		if (elem == min()) return null;
		if (!exist(elem)) return null;
		return findPredecessor(search(elem)).value;
		
	}
	
	
	private Node findPredecessor(Node node)
	{
		
		Node predecessor = null;
		// case 2:
		if (node.left != null) return max(node.left);
		// case 3:
		predecessor = node.parent;
		while (predecessor != null && node == predecessor.left)
		{
			node = predecessor;
			predecessor = predecessor.parent;
		}
		return predecessor;
		
	}
	
	
	// 3 insert
	public Node insert(T elem)
	{
		
		root = insert(root, elem);
		return root;
		
	}
	
	
	private Node insert(Node node, T elem)
	{
		
		if (node == null) return new Node(elem);
		else
		{
			int comp = comparator.compare(elem, node.value);
			if (comp > 0)
			{
				node.right = insert(node.right, elem);
				node.right.parent = node;
			}
			else if (comp < 0)
			{
				node.left = insert(node.left, elem);
				node.left.parent = node;
			}
		}
		return node;
		
	}
	
	
	// 4 remove
	public void delete(T elem)
	{
		
		root = delete(elem, root);
		
	}
	
	
	protected Node delete(T elem, Node node)
	{
		
		if (node == null) throw new NoSuchElementException();
		else
		{
			int comp = comparator.compare(elem, node.value);
			if (comp < 0) node.left = delete(elem, node.left);
			else if (comp > 0) node.right = delete(elem, node.right);
			else if (node.left != null && node.right != null) node.right = detachMin(node, node.right);
			else node = (node.left != null) ? node.left : node.right;
		}
		return node;
		
	}
	
	
	private Node detachMin(Node del, Node node)
	{
		
		if (node.left != null) node.left = detachMin(del, node.left);
		else
		{
			del.value = node.value;
			node = node.right;
		}
		return node;
		
	}
	
	
	public void inOrderWalk()
	{
		
		inOrderWalk(root);
		
	}
	
	
	private void inOrderWalk(Node node)
	{
		
		if (node != null)
		{
			inOrderWalk(node.left);
			System.out.print(node.value + " ");
			inOrderWalk(node.right);
		}
		
	}
	
	
	public void postOrderWalk()
	{
		
		postOrderWalk(root);
		
	}
	
	
	private void postOrderWalk(Node node)
	{
		
		if (node != null)
		{
			postOrderWalk(node.left);
			postOrderWalk(node.right);
			System.out.print(node.value + " ");
		}
		
	}
	
	
	public void preOrderWalk()
	{
		
		preOrderWalk(root);
		
	}
	
	
	private void preOrderWalk(Node node)
	{
		
		if (node != null)
		{
			System.out.print(node.value + " ");
			preOrderWalk(node.left);
			preOrderWalk(node.right);
		}
		
	}
	
	
	public int height1(Node node)
	{
		
		return height(node);
		
	}
	
	
	private int height(Node node)
	{
		
		if (node != null)
		{
			int rightHeight = height(node.right);
			int leftHeight = height(node.left);
			if (rightHeight > leftHeight) return rightHeight + 1;
			else return leftHeight + 1;
		}
		else return 0;
		
	}
	
	
	private int noLeafsLeft(Node node)
	{
		
		if (node != null)
		{
			if (node.left == null && node.right == null)
			{
				noLeafsLeft++;
			}
			noLeafsLeft(node.left);
			noLeafsLeft(node.right);
		}
		return noLeafsLeft;
		
	}
	
	
	private int noLeafsRight(Node node)
	{
		
		if (node != null)
		{
			if (node.left == null && node.right == null)
			{
				noLeafsRight++;
			}
			noLeafsRight(node.left);
			noLeafsRight(node.right);
		}
		return noLeafsRight;
		
	}
	
	
	private int noNodesLeft(Node node)
	{
		
		if (node != null)
		{
			noNodesLeft(node.left);
			noNodesLeft(node.right);
			noNodesLeft++;
		}
		return noNodesLeft;
		
	}
	
	
	private int noNodesRight(Node node)
	{
		
		if (node != null)
		{
			noNodesRight(node.right);
			noNodesRight(node.left);
			noNodesRight++;
		}
		return noNodesRight;
		
	}
	
	
	public void characteristics()
	{
		
		System.out.println("The number of nodes in the left subtree: " + noNodesLeft(root.left));
		System.out.println("The number of nodes in the right subtree: " + noNodesRight(root.right));
		System.out.println("The number of the leafs in the left subtree: " + noLeafsLeft(root.left));
		System.out.println("The number of the leafs in the right subtree: " + noLeafsRight(root.right));
		System.out.println("The height of the tree is: " + (height(root)));
		
	}
	
	
	public Node insertRB(T elem)
	{
		
		root = insertRB(root, elem);
		return root;
		
	}
	
	
	private Node insertRB(Node node, T elem)
	{
		
		Node newNode = insert(node, elem);
		Node root = node;
		newNode.color = "red";
		while (root != null && root.parent.color.equalsIgnoreCase("red"))
		{
			if (root.parent == root.parent.parent.left)
			{
				Node anNode = root.parent.parent.right;
				if (anNode.color.equalsIgnoreCase("red"))
				{
					root.parent.color = "black";
					anNode.color = "Black";
					root.parent.parent.color = "red";
					root = root.parent.parent;
				}
				else
				{
					if (root == root.parent.right)
					{
						root = root.parent;
						// leftRotate(node, root.parent.parent);
					}
					root.parent.color = "Black";
					root.parent.parent.color = "RED";
					// rightRotate(node, root.parent.parent);
				}
			}
			else if (root.parent == root.parent.parent.right)
			{
				Node anNode = root.parent.parent.left;
				if (anNode.color.equalsIgnoreCase("red"))
				{
					root.parent.color = "black";
					anNode.color = "Black";
					root.parent.parent.color = "red";
					root = root.parent.parent;
				}
				else
				{
					if (root == root.parent.left)
					{
						root = root.parent;
						// rightRotate(node, root.parent.parent);
					}
					root.parent.color = "Black";
					root.parent.parent.color = "RED";
					// leftRotate(node, root.parent.parent);
				}
			}
		}
		node.color = "BLACK";
		return newNode;
		
	}
	
}
