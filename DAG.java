import java.util.ArrayList;

public class DAG <Key>{

	//Data structure to implement new ancestor problem

	public class Node{

		public Key key;
		public ArrayList children;

		public Node(Key key){
			this.key = key;
		}

	}

	public ArrayList nodes = new ArrayList();

	public boolean contains(Key key){
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).equals(key)){
				return true;
			}
		}
		return false;
	}

	public void addChild(Node x, Key key){
		x.children.add(key);
		nodes.add(key);
	}

}
