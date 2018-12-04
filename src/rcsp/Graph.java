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
		vertexMap = new HashMap<>();
		edgeMap = new HashMap<>();
		adjacencyList = new HashMap<>();
		
		vertexResources = new HashMap<>();
		edgeConsumption = new HashMap<>();
	}
	
	public Vertex addVertex(String name) {
		// Create vertex
		Vertex newVertex = new Vertex(name);
		vertexMap.put(name,newVertex);
		ArrayList<String> succList = new ArrayList<>();
		
		// This creates an empty list for strings for each vertex
		adjacencyList.put(name, succList);
		return newVertex;
	}
	
	public void addEdge(Vertex s, Vertex t) {		
		edgeMap.put(Arrays.asList(s.ID, t.ID),new Edge(s,t));
		List<String>succList = adjacencyList.get(s.ID);
		succList.add(t.ID);
	}
	
	public void addEdge(String s, String t) {
		// Find vertices
		Vertex source = getVertex(s);
		Vertex sink = getVertex(t);
		
		// Now add edge to vertexMap
		edgeMap.put(Arrays.asList(s, t), new Edge(source,sink));		
		// Add to adjacency list
		List<String>succList = adjacencyList.get(s);
		succList.add(t);
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
