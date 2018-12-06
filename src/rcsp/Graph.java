package rcsp;

import java.util.*;

// For RCSP, the graph is going to be directed and weighted.
// Self-loops are permitted but they must increase consumption in some capacity.
public class Graph{
	Map <String, Vertex> vertexMap;
	Map <Vertex, VertexAttributes> vertexResources;
	
	// Kind of like an adjacency list
	Map <String,ArrayList<String>> adjacencyList;
	
	// This may seem wrong because Lists can change 
	// but as long as we are careful, it should be ok.
	Map <List<String>,Edge> edgeMap;
	Map <Edge, EdgeAttributes> edgeConsumption;
	
	// Empty constructor for now
	public Graph() {
		// Initialize all these empty maps, so they can be used when adding parts of the graph.
		vertexMap = new HashMap<>();
		vertexResources = new HashMap<>();
		
		edgeMap = new HashMap<>();
		edgeConsumption = new HashMap<>();
		
		adjacencyList = new HashMap<>();
	}
	
	public Vertex addVertex(String name) {
		// Create vertex
		Vertex newVertex = new Vertex(name);
		vertexMap.put(name,newVertex);
		ArrayList<String> succList = new ArrayList<>();
		
		// Create empty list of Labels for vertex attributes and map it to current vertex.
		VertexAttributes attr = new VertexAttributes();
		vertexResources.put(newVertex, attr);
		
		// This creates an empty list for strings for each vertex
		adjacencyList.put(name, succList);
		return newVertex;
	}
	
	public void addEdge(Vertex s, Vertex t) {
		Edge newEdge = new Edge(s,t);
		edgeMap.put(Arrays.asList(s.ID, t.ID),newEdge);
		List<String>succList = adjacencyList.get(s.ID);
		succList.add(t.ID);
		
		// Now make an Edge attribute and add it to the map.
		// The attributes are set to 0.
		EdgeAttributes attr = new EdgeAttributes();
		edgeConsumption.put(newEdge, attr);
	}
	
	public void addEdge(String s, String t) {
		// Find vertices
		Vertex source = getVertex(s);
		Vertex sink = getVertex(t);
		
		// Now use overloaded function to deal with the rest.
		addEdge(source,sink);	
	}
	
	public Vertex getVertex(String v) {
		return vertexMap.get(v);
	}
	
	public Edge getEdge(String s, String t) {
		return edgeMap.get(Arrays.asList(s,t));
	}
	
	public void printAdjacencyList() {
		System.out.println(adjacencyList);
	}
}
