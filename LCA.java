public class LCA {

	Digraph graph;

	public LCA(Digraph g){
		this.graph = g;
	}

	public boolean hasCycle(){
		DirectedCycle testDAG = new DirectedCycle(graph);
		return testDAG.hasCycle();
	}

	public int lowestCommonAncestor(int a, int b){
		if(hasCycle()){
			return -1;
		}else if(graph.V()==0){
			return -1;
		}else{
			return 0;
			//Some form of augmented DFS alogrithm
		}
	}
}
