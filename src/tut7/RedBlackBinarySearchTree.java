package tut7;

public class RedBlackBinarySearchTree<T> extends BinarySearchTree<T>
{
	
	public static void main(String[] args)
	{
		
		RedBlackBinarySearchTree<Integer> tree = new RedBlackBinarySearchTree<>();
		for (int x = 51; x > 44; x--)
		{
			tree.add(x);
		}
		System.out.println("done");
		
	}
	
	
	@Override
	public boolean add(T value)
	{
		
		if (!super.add(value)) return false;
		
		Node node = findNode(root, value);
		node = findNode(root, node.value);
		while (node != root && node.parent.red)
		{
			if (node.parent == node.parent.parent.left)
			{
				Node other = node.parent.parent.right;
				if (other != null && other.red)
				{
					node.parent.red = false;
					other.red = false;
					node.parent.parent.red = true;
					node = node.parent.parent;
				}
				else if (node == node.parent.right)
				{
					node = node.parent;
					rotateLeft(node);
				}
				else
				{
					node.parent.red = false;
					node.parent.parent.red = true;
					rotateRight(node.parent.parent);
				}
			}
			else
			{
				Node other = node.parent.parent.left;
				if (other != null && other.red)
				{
					node.parent.red = false;
					other.red = false;
					node.parent.parent.red = true;
					node = node.parent.parent;
				}
				else if (node == node.parent.left)
				{
					node = node.parent;
					rotateRight(node);
				}
				else
				{
					node.parent.red = false;
					node.parent.parent.red = true;
					rotateLeft(node.parent.parent);
				}
			}
		}
		root.red = false;
		return true;
		
	}
	
	
	public void rotateLeft(Node node)
	{
		
		Node other = node.right;
		node.right = other.left;
		if (other.left != null) other.left.parent = node;
		other.parent = node.parent;
		if (node.parent == null) root = other;
		else if (node == node.parent.left) node.parent.left = other;
		else node.parent.right = other;
		other.left = node;
		node.parent = other;
		
	}
	
	
	public void rotateRight(Node node)
	{
		
		Node other = node.left;
		node.left = other.right;
		if (other.right != null) other.right.parent = node;
		other.parent = node.parent;
		if (node.parent == null) root = other;
		else if (node == node.parent.right) node.parent.right = other;
		else node.parent.left = other;
		other.right = node;
		node.parent = other;
		
	}
	
}
