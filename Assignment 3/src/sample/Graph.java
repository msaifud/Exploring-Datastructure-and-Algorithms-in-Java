package sample;

import java.util.*; 

public class Graph {

    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<>();
        tmp.add(b);
        adjacencyMap.put(a,tmp);
    }

    public void addEdge(Node source, Node destination) {

        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);
        addEdgeHelper(destination, source);
        
    }
    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }
    
    public void resetNodesVisited()
    {
    	 for (Node n : adjacencyMap.keySet()) {
             n.unvisit();
         }
    
    }
    
    String BFS(Node node) {
    //implement the BFS code
    	    	
    	LinkedList<Node> queue = new LinkedList<Node>();
    	LinkedList<Node> output = new LinkedList<Node>();
    	queue.add(node);
    	node.visit();
		String out = "The output of BFS is: ";
    	while(!queue.isEmpty()) {
    		Node first = queue.removeFirst();
    		output.add(first);
    		for (Node n : adjacencyMap.keySet()) {
    			if((hasEdge(first, n)) && !(n.isVisited())) {
    				queue.add(n);
    				n.visit();
    			}
    		}
    	}
    	while(!output.isEmpty()) {
			out = out + output.removeFirst().name + " ";
    	}
    	return out;
    }
    
   
   public String DFS(Node node) {
    //Implement DFS
	   	
	   LinkedList<Node> stack = new LinkedList<Node>();
  		LinkedList<Node> output = new LinkedList<Node>();
  		stack.push(node);
  		node.visit();
  		String out = "The output of DFS is: ";
  		while(!stack.isEmpty()) {
  			Node top = stack.pop();
  			output.add(top);
  			for (Node n : adjacencyMap.keySet()) {
  				if((hasEdge(top, n)) && !(n.isVisited())) {
  					stack.push(n);
  					n.visit();
  				}
  			}
  		}
  		while(!output.isEmpty()) {
			out = out + output.pop().name + " ";
  		}
  		return out;
	   
   }
   
   public void printEdges() {
    //implement printEdges
	   
	   for (Node n : adjacencyMap.keySet()) {
		   System.out.print("The " + n.name + " has an edge towards: ");
		   for (Node j : adjacencyMap.keySet()) {
   				if(hasEdge(n, j)) {
   					System.out.print(j.name + " ");
   				}
   			}
		   System.out.println();
	    }
   }
}