import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DAG
{
	private int V;           							// number of vertices in this digraph
	private int E;                				// number of edges in this digraph
	private ArrayList<Integer>[] adj;    	// adj[v] = adjacency list for vertex v
	private int[] indegree;        				// indegree[v] = indegree of vertex v
	private boolean marked[];							//Boolean List to track visited vertices
	private boolean hasCycle;							//True if cycle in graph
  private boolean stack[];							//Order that vertices were visited

	public DAG(int V)
	{
		if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
	    this.V = V;
	    this.E = 0;
	    indegree = new int[V];
	    marked = new boolean[V];
	    stack = new boolean[V];
	    adj = (ArrayList<Integer>[]) new ArrayList[V];
	    for (int v = 0; v < V; v++)
	    {
	        adj[v] = new ArrayList<Integer>();
	    }
	}

	//Returns current vertex
	public int V()
	{
		return V;
	}

	public int E()
	{
        return E;
    }

	//Adds a directed edge from v->w
	public void addEdge(int v, int w)
	{
	    if((validateVertex(v)>0) && (validateVertex(w)>0))
	    {
	    		adj[v].add(w);
	    		indegree[w]++;
	    		E++;
	    	}
	    	else
	    	{
	    		System.out.println("Please enter vertices between 0 & n-1");
	    }
	}

	private int validateVertex(int v)
	{
        if (v < 0 || v >= V)
        {
        		return -1;
        }
        else
        {
        		return 1;
        	}
	}

	//Returns the adjacent vertices to v
	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}

	public int findLCA(int v, int w)
	{
		findCycle(0);
		if(hasCycle)
		{
			//Graph is not a DAG
			return -1;
		}
		//Reverse the dag, allows easier traversal
		DAG backwards = reverse();

		//Locate the two points in the graph
		ArrayList<Integer> vPath = backwards.BFS(v);
		ArrayList<Integer> wPath = backwards.BFS(w);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
		boolean found = false;

		//cycle through the BFS paths, adding all common ancestors to the arrayList
		//return the first one found, as it is the closest to the nodes.
		for(int i = 0; i<vPath.size(); i++)
		{
			for(int t = 0; t<wPath.size(); t++)
			{
				if(vPath.get(i)==wPath.get(t))
				{
					commonAncestors.add(vPath.get(i));
					found = true;
				}
			}
		}

		//return -1 in any case where no lca is found (empty dag etc)
		if(found)
		{
			return commonAncestors.get(0);
		}
		else
		{
			return -1;
		}
	}

	//to find the LCA, will have to traverse the graph backwards as the lca comes before the two nodes
    public DAG reverse()
    {
        DAG reverse = new DAG(V); //new dag of same parameter
        for (int v = 0; v < V; v++)
        {
            for (int w : adj(v))
            {
                reverse.addEdge(w, v); //reverse the direction of the edges
            }
        }
        return reverse;
    }

		public ArrayList<Integer> BFS(int s)
    {
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> order= new ArrayList<Integer>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            order.add(s);
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return order;
    }

	public boolean hasCycle()
	{
        return hasCycle;
    }

	 public void findCycle(int v)
	 {
		 marked[v] = true;
		 stack[v] = true;

		 for (int w : adj(v))
		 {
			 if(!marked[w])
			 {
				 findCycle(w);
			 }
			 else if (stack[w])
			 {
				 hasCycle = true;
				 return;
			 }
		 }
		 stack[v] = false;
	 }
}
