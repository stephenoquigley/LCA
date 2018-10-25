import static org.junit.Assert.*;

import org.junit.Test;

public class LCATest
{
	@Test
	public void testLCA()
	{
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();

		assertSame("Testing LCA for null root", null, bst.lowestCommonAncestor(bst.root, 1, 2));

		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
						//			5
		assertSame("Testing LCA left side", 3, bst.lowestCommonAncestor(bst.root, 2,6));
		assertSame("Testing LCA right side", 7, bst.lowestCommonAncestor(bst.root, 8,3));
		assertSame("Testing LCA where LCA is one of the nodes", 7, bst.lowestCommonAncestor(bst.root, 7,8));
		assertSame("Testing LCA where LCA is one of the nodes", 7, bst.lowestCommonAncestor(bst.root, 3,7));
	}

	/** <p>Test {@link LCA#delete(Comparable)}.</p> */
	@Test
	public void testDelete()
	{
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.delete(1);
		assertEquals("Deleting from empty tree", "()", LCA.printKeysInOrder());

		LCA.put(7, 7);   //        _7_
		LCA.put(8, 8);   //      /     \
		LCA.put(3, 3);   //    _3_      8
		LCA.put(1, 1);   //  /     \
		LCA.put(2, 2);   // 1       6
		LCA.put(6, 6);   //  \     /
		LCA.put(4, 4);   //   2   4
		LCA.put(5, 5);   //        \
						//			5

		assertEquals("Checking order of constructed tree",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", LCA.printKeysInOrder());

		LCA.delete(9);
		assertEquals("Deleting non-existent key",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", LCA.printKeysInOrder());

		LCA.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", LCA.printKeysInOrder());

		LCA.delete(6);
		assertEquals("Deleting node with single child",
				"(((()1(()2()))3(()4(()5())))7())", LCA.printKeysInOrder());

		LCA.delete(3);
		assertEquals("Deleting node with two children",
				"(((()1())2(()4(()5())))7())", LCA.printKeysInOrder());
	}

	@Test
	public void testPut()
	{
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.put(1, null);
		LCA.put(10, 1);
		LCA.put(15,2);
		LCA.put(15, 15);

		assertEquals("Putting nodes", "(()10(()15()))", LCA.printKeysInOrder());
	}

	@Test
	public void testForDirectedGraph()
	{
		DAG test = new DAG(10);
		test.addEdge(1, 2);
		test.addEdge(1, 3);
		test.addEdge(3, 4);
		test.addEdge(4, 5);
		test.addEdge(4, 6);

		assertEquals("", 5, test.E());
		assertEquals("", 10, test.V());
		String ans = "[5, 6]";
		assertEquals("",ans, test.adj(4).toString());
	}

	@Test
	public void testAddEdge()
	{
		DAG test4 = new DAG(5);
		test4.addEdge(0, 1);

		//Doesnt add an edge
		test4.addEdge(-2, -5);
		assertEquals("Testing edge count is 1", 1, test4.E());

		test4.addEdge(1, 2);
		assertEquals("Testing edge count is 2", 2, test4.E());
	}

	@Test(expected=Exception.class)
	public void exceptionTest()
	{
		//Can't make a directed graph with less than 0 vertices
		DAG test3 = new DAG(-5);
	}

	//Directed graph isnt necessary directed acyclic graph, so will need to ensure it is a DAG.
	@Test
	public void testsForCycle()
	{
		DAG cyclic = new DAG(20);
		cyclic.addEdge(0, 1);
		cyclic.addEdge(1, 2);
		cyclic.addEdge(2, 0);

		//Parameter is first vertex
		cyclic.findCycle(0);

		//Cycle from 0 - 1 - 2 - 0, should return true.
		assertTrue(cyclic.hasCycle());

		DAG acyclic = new DAG(20);
		acyclic.addEdge(0, 1);
		acyclic.addEdge(1, 3);
		acyclic.addEdge(2, 4);
		//Parameter is first vertex
		acyclic.findCycle(0);
		//No Cycle,return false
		assertFalse(acyclic.hasCycle());
	}

	@Test
	public void testLCAForNoCommonAncestors()
	{
		DAG lca2 = new DAG(11);
		//-----1----5----
		//---0-|---/-----
		//-----2--3---4--
		lca2.addEdge(0, 1);
		lca2.addEdge(0, 2);
		lca2.addEdge(1, 2);
		lca2.addEdge(2, 3);
		lca2.addEdge(3, 4);
		lca2.addEdge(1, 5);
		lca2.addEdge(3, 5);

		//Check it works ok
		assertEquals("Finding LCA when there is no LCA", 0, lca2.findLCA(3, 1));
		assertEquals("", 2, lca2.findLCA(3, 2));
		assertEquals("", 3, lca2.findLCA(4, 5));

		//Check for no common ancestors
		assertEquals("Finding LCA when one node doesnt exist", -1, lca2.findLCA(7, 3));
	}

	//unique case where graph is just a digraph but no acyclic!
	@Test
	public void testLCAForNonDAG()
	{
		DAG lca3 = new DAG(11);
		//---0
		//--|-\
		//---\-\
		//----2--3

		//0 - 2 - 3 make a cycle

		lca3.addEdge(0, 1);
		lca3.addEdge(0, 2);
		lca3.addEdge(2, 3);
		lca3.addEdge(3, 0);
		lca3.addEdge(3, 4);

		//Should return -1 if graph is not a DAG
		assertEquals("", -1, lca3.findLCA(2, 3));
		assertEquals("", -1, lca3.findLCA(3, 4));
		assertEquals("", -1, lca3.findLCA(1, 2));
		assertEquals("", -1, lca3.findLCA(0, 3));
		assertEquals("", -1, lca3.findLCA(1, 3));

	}

	@Test
	public void testLCAforEmpty()
	{
		DAG lca = new DAG(10);
		assertEquals("Testing LCA is -1", -1, lca.findLCA(1, 2));
	}
}
