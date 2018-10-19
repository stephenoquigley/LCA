import java.util.ArrayList;

public class LCA <Key extends Comparable<Key>, Value>{


	//public LinkedHashSet<Key> keySet = new LinkedHashSet<Key>();	// all nodes in graph
	//private LinkedHashSet<Key> orphanSet = new LinkedHashSet<Key>();	// all nodes with no requirement nodes

	Node root;


	class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}

	}

	class DAG {
		private Node root;
		public DAG(Node root) {
			this.root = root;
		}
	}
	//Constructor for DAG, not sure if this will be kept.

	public boolean isEmpty() {
		return size() == 0;
	}

	// return number of key-value pairs in BST
	public int size() {
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		//BST is empty
		if (x == null) return 0;

		//N is number of nodes
		else return x.N;
	}

	/**
	 *  Search BST for given key.
	 *  Does there exist a key-value pair with given key?
	 *
	 *  @param key the search key
	 *  @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 *  Search BST for given key.
	 *  What is the value associated with given key?
	 *
	 *  @param key the search key
	 *  @return value associated with the given key if found, or null if no such key exists.
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node node, Key key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if      (cmp < 0) return get(node.left, key);
		else if (cmp > 0) return get(node.right, key);
		else return node.val;

	}

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 *
	 *  @param key the key to insert
	 *  @param val the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node put(Node node, Key key, Value val) {
		if (node == null) return new Node(key, val, 1); //new bst
		int cmp = key.compareTo(node.key);
		if      (cmp < 0) node.left  = put(node.left,  key, val);
		else if (cmp > 0) node.right = put(node.right, key, val);
		else              node.val   = val; //updating value
		node.N = 1 + size(node.left) + size(node.right); // value child1 + value child2 + 1
		return node;
	}



	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: worst case O(N) which would occur when
	 * the tree would act as a list, that meaning either all the nodes are to right or all the nods are to the left
	 * when N is the number of nodes in the BST.
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 * Example 1: for an empty tree this should return -1.
	 * Example 2: for a tree with only one node it should return 0.
	 * Example 3: for the following tree it should return 2.
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 */

	public int height() {
		return height(root);
	}


	private int height(Node x) {
		if (x == null) {
			return -1;
		}
		else {
			return 1 + Math.max(height(x.left), height(x.right));
		}
	}

	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key
	 * is the element at position (N+1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	public Key median() {
		if (isEmpty()) return null; //returning null if the bst is empty

		else {
			int median=(((size(root)+1)/2)-1);   //add 1 to size(root)
			//need to change int to key?
			return intToKey(median);
		}
	}

	private Key intToKey(int passedInt) {
		Node node = intToKey(root, passedInt);
		return node.key;
	}
	//find the node with equivalent key given passedInt
	private Node intToKey(Node node, int passedInt) {
		int leftSize = (size(node.left));

		//check is it in the left or right subtree
		if (leftSize > passedInt) {
			return intToKey(node.left,  passedInt);
		}
		else if (leftSize < passedInt) {
			return intToKey(node.right, passedInt-leftSize-1);
		}
		else {
			return node;
		}
	}


	/**
	 * Print all keys of the tree in a sequence, in-order.
	 * That is, for each node, the keys in the left subtree should appear before the key in the node.
	 * Also, for each node, the keys in the right subtree should appear before the key in the node.
	 * For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()"
	 * Example 2: Tree containing only "A" -- output: "(()A())"
	 * Example 3: Tree:
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		String res= "";
		if (isEmpty()){
			return res += "()";
		}
		else {
			return res = printKeysInOrder(root);
		}

	}

	 private String printKeysInOrder(Node node) {
		 String res = "";
		 if (node == null) {
			 return res += "()";
		 }

		 else {
			return res += ("(" + printKeysInOrder(node.left) + node.key.toString() + printKeysInOrder(node.right) + ")");
		 }

	 }

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ASCII picture of the tree.
	 */
	public String prettyPrintKeys() {
		if(isEmpty()) return "-null\n";
	     return prettyPrint(root,"");
	}

	private String prettyPrint(Node node, String prefix) {
		if (node == null) {
			return (prefix + "-null\n");
		}
		else {

			return (prefix+"-"+node.key.toString()+"\n" +prettyPrint(node.left,(prefix+" |"))+ prettyPrint(node.right,(prefix+"  ")));
    	}

	}

	/**
	 * Deletes a key from a tree (if the key is in the tree).
	 * Note that this method works symmetrically from the Hibbard deletion:
	 * If the node to be deleted has two child nodes, then it needs to be
	 * replaced with its predecessor (not its successor) node.
	 *
	 * @param key the key to delete
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete (Node node, Key key) {
		if (node == null) {
			return null;
		}

        int compare = key.compareTo(node.key);

        if   (compare > 0) {
        	node.right = delete(node.right, key);
        	node.left  = delete(node.left,  key);
        }
        else if (compare < 0) {
        	node.left  = delete(node.left,  key);
        }
        else {
        	if (node.right == null) {
        		return node.left;
        	}
            if (node.left  == null) {
            	return node.right;
            }
            Node temp = node;
            node = max(temp.left);
            node.left = deleteMax(temp.left);
            node.right = temp.right;
        }

        node.N = size(node.left) + size(node.right) + 1;
        return node;

	}

	private Node deleteMax(Node node)
    {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

	public Node max(Node node)
	   {
	     if(node.right!=null) {
	       return max(node.right);
	     }
	     return node;
	   }

	// Used: www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree as a help source.
	 	public Key lowestCommonAncestor (Node node, Key key1, Key key2){
	 		if (node == null)
	             return null;
	 		if (node.key == key1) {
	 			return node.key;
	 		}
	 		if (node.key == key2) {
	 			return node.key;
	 		}
	 		int cmp1 = node.key.compareTo(key1);
	 		int cmp2 = node.key.compareTo(key2);

	         if (cmp1 >= 0 && cmp2 >= 0)
	             return lowestCommonAncestor(node.left, key1, key2);

	         if (cmp1 <= 0 && cmp2 <= 0)
	             return lowestCommonAncestor(node.right, key1, key2);

	         return node.key;
	 	}

	 	public static void main(String [] args)
	 	{
	 		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();

			System.out.println(bst.lowestCommonAncestor(bst.root,1,2));
			bst.put(7, 7);   //        _7_
			bst.put(8, 8);   //      /     \
			bst.put(3, 3);   //    _3_      8
			bst.put(1, 1);   //  /     \
			bst.put(2, 2);   // 1       6
			bst.put(6, 6);   //  \     /
			bst.put(4, 4);   //   2   4
			bst.put(5, 5);   //        \

			System.out.print(bst.lowestCommonAncestor(bst.root, 1, 6));
	 	}
}
