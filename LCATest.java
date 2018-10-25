import static org.junit.Assert.*;

import org.junit.Test;

public class LCATest {



	@Test
	public void testLCA() {

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
		//        				 	5
		assertSame("Testing LCA left side", 3, bst.lowestCommonAncestor(bst.root, 2,6));
		assertSame("Testing LCA right side", 7, bst.lowestCommonAncestor(bst.root, 8,3));
		assertSame("Testing LCA where LCA is one of the nodes", 7, bst.lowestCommonAncestor(bst.root, 7,8));
		assertSame("Testing LCA where LCA is one of the nodes", 7, bst.lowestCommonAncestor(bst.root, 3,7));
	}



//	@Test
//	public void testPrettyPrint() {
//		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
//		assertEquals("Checking pretty printing of empty tree",
//				"-null\n", LCA.prettyPrintKeys());
//
//		//  -7
//		//   |-3
//		//   | |-1
//		//   | | |-null
//		LCA.put(7, 7);       //   | |  -2
//		LCA.put(8, 8);       //   | |   |-null
//		LCA.put(3, 3);       //   | |    -null
//		LCA.put(1, 1);       //   |  -6
//		LCA.put(2, 2);       //   |   |-4
//		LCA.put(6, 6);       //   |   | |-null
//		LCA.put(4, 4);       //   |   |  -5
//		LCA.put(5, 5);       //   |   |   |-null
//		//   |   |    -null
//		//   |    -null
//		//    -8
//		//     |-null
//		//      -null
//
//		String result =
//				"-7\n" +
//						" |-3\n" +
//						" | |-1\n" +
//						" | | |-null\n" +
//						" | |  -2\n" +
//						" | |   |-null\n" +
//						" | |    -null\n" +
//						" |  -6\n" +
//						" |   |-4\n" +
//						" |   | |-null\n" +
//						" |   |  -5\n" +
//						" |   |   |-null\n" +
//						" |   |    -null\n" +
//						" |    -null\n" +
//						"  -8\n" +
//						"   |-null\n" +
//						"    -null\n";
//		assertEquals("Checking pretty printing of non-empty tree", result, LCA.prettyPrintKeys());
//
//
//	}


	/** <p>Test {@link LCA#delete(Comparable)}.</p> */
	@Test
	public void testDelete() {
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
		//         5

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
	public void testPut() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.put(1, null);
		LCA.put(10, 1);
		LCA.put(15,2);
		LCA.put(15, 15);

		assertEquals("Putting nodes", "(()10(()15()))", LCA.printKeysInOrder());
	}

//	@Test
//	public void testGet() {
//		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
//		assertEquals("Testing empty", false, LCA.contains(5));
//		LCA.put(1, null);
//		LCA.put(10, 1);
//		LCA.put(5, 9);
//		LCA.put(15,2);
//		LCA.put(9, 15);
//
//		assertEquals("Testing left", "9", LCA.get(5).toString());
//		assertEquals("Testing right then right", "2", LCA.get(15).toString());
//		assertEquals("Testing right then left", "15", LCA.get(9).toString());
//		assertEquals("Testing root", "1", LCA.get(10).toString());
//	}

//	@Test
//	public void testHeight() {
//		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
//
//		assertEquals("Testing height", -1, LCA.height());
//
//		LCA.put(7, 7);
//
//		assertEquals("Testing height", 0, LCA.height());
//
//		LCA.put(8, 8);
//		LCA.put(3, 3);
//
//		assertEquals("Testing height", 1, LCA.height());
//
//		LCA.put(1, 1);
//		LCA.put(2, 2);
//
//		assertEquals("Testing height", 3, LCA.height());
//
//		LCA.put(6, 6);
//		LCA.put(4, 4);
//		LCA.put(5, 5);
//
//		assertEquals("Testing height", 4, LCA.height());
//
//	}

//	@Test
//	public void testMedian() {
//		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
//		assertEquals("Testing median", null, LCA.median());
//		LCA.put(7, 7);
//		assertEquals("Testing median", "7", LCA.median().toString());
//		LCA.put(8, 8);
//		LCA.put(3, 3);
//		assertEquals("Testing median", "7", LCA.median().toString());
//		LCA.put(1, 1);
//		LCA.put(2, 2);
//		assertEquals("Testing median", "3", LCA.median().toString());
//		LCA.put(6, 6);
//		LCA.put(4, 4);
//		LCA.put(5, 5);
//		assertEquals("Testing median", "4", LCA.median().toString());
//	}
//	@Test
//	public void testContains() {
//		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
//		assertEquals("Testing contains", false, LCA.contains(1));
//		LCA.put(7, 7);
//		assertEquals("Testing contains", true, LCA.contains(7));
//	}

	@Test
	public void testForDirectedGraph(){
		DAG test = new DAG(10);
		test.addEdge(1, 2);
		test.addEdge(1, 3);
		test.addEdge(3, 4);
		test.addEdge(4, 5);
		test.addEdge(4, 6);

		//assertEquals("", 1, test.indegree(4));
		//assertEquals("", 2, test.outdegree(4));
		assertEquals("", 5, test.E());
		assertEquals("", 10, test.V());
		String ans = "[5, 6]";
		assertEquals("",ans, test.adj(4).toString());
	}


	@Test
	public void testAddEdge(){
		DAG test4 = new DAG(5);
		test4.addEdge(0, 1);

		//Doesnt add an edge
		test4.addEdge(-2, -5);

		assertEquals("Testing edge count is 1", 1, test4.E());

		test4.addEdge(1, 2);

		assertEquals("Testing edge count is 2", 2, test4.E());
	}

//	@Test
//	public void testinDegree(){
//		DAG test5 = new DAG(5);
//		assertEquals("", -1, test5.indegree(-3));
//	}
//
//	@Test
//	public void testOutDegree(){
//		DAG test6 = new DAG(5);
//		assertEquals("", -1, test6.outdegree(8));
//	}


	@Test(expected=Exception.class)
	public void exceptionTest(){
		//Can't make a directed graph with less than 0 vertices
		DAG test3 = new DAG(-5);
	}

	//Directed graph isnt necessary directed acyclic graph, so will need to ensure it is a DAG.
	@Test
	public void testsForCycle(){
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
	public void testLCAForNoCommonAncestors(){
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
	public void testLCAForNonDAG(){
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
	public void testLCAforEmpty() {
		DAG lca = new DAG(10);
		assertEquals("Testing LCA is -1", -1, lca.findLCA(1, 2));
	}

}
