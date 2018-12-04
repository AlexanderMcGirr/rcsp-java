package examples;

import rcsp.*;

public class RcsptwExample {

	public static void main(String[] args) {
/*		Graph<String,DefaultEdge> g = new DefaultDirectedWeightedGraph<>(DefaultEdge.class);
		
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		
		g.addEdge("A", "C");
	    g.addEdge("B", "B");
	    g.addEdge("B", "D");
	    g.addEdge("B", "E");
	    g.addEdge("C", "B");
	    g.addEdge("C", "D");
	    g.addEdge("D", "E");
	    g.addEdge("E", "A");
	    
	    Iterator<String> iterator = new DepthFirstIterator<String, DefaultEdge>(g, "A");
	    List<String> pred = Graphs.predecessorListOf(g,"D");
	    
	    for(String a: pred)
	    {
	    	System.out.println(a);
	    }
	    
	    System.out.println();
	    
	    while(iterator.hasNext()){
	    	String next = iterator.next();
	    	System.out.println(next);
	    }*/
		
		Graph g = new Graph();
		g.addVertex("A");
		g.addVertex("B");
		
		
		g.addEdge("A","B");
		g.addEdge("B","A");
		
		g.printAdjacencyList();
		
	}
}
