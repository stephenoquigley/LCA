import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeTest 
{

	@Test
	public void testNodeConstructor()
	{
		Node temp = new Node(1);
		assertNotNull("Testing Node constructor: ", temp);
	}
	
	@Test
	public void testEmptyTree() 
	{	
		BinaryTree tree = new BinaryTree();
		assertNull("Find LCA when tree is empty: ", tree.findLCA(1, 2));
		assertEquals("Find LCA when tree is empty: ", null, tree.findLCA(1, 2) );
	}
	
	@Test
	public void testCommonAncestor()
	{
		//test tree structure:
		//		1
		//	2		3
		//4   5   6   7
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);

		assertEquals("LCA of 4 and 5: ", 2, tree.findLCA(4, 5).data);
		assertEquals("LCA of 6 and 7: ", 3, tree.findLCA(6, 7).data);
		assertEquals("LCA of 2 and 3: ", 1, tree.findLCA(2, 3).data);
		assertEquals("LCA of 4 and 7: ", 1, tree.findLCA(4, 7).data);
	}
	
	@Test
	public void testTwoNodes()
	{	
		//test tree structure:
		//			1
		//		2
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);

		assertEquals("Find LCA of tree with only root and one child: ", 1, tree.findLCA(1, 2).data);	
	}
	
	@Test
	public void testForNonExistentNodes()
	{
		//test tree structure:
		//		1
		//	2		3
		//4   5   6   7
		
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);
		
		assertEquals("Find ancestors of non-existent nodes in populated tree: ", null, tree.findLCA(8, 9));
		assertEquals("Find ancestors of non-existent nodes in populated tree: ", null, tree.findLCA(45, 93));
	}
	
}