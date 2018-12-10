package rcsp;

import java.util.*;

// For RCSP, the graph is going to be directed and weighted.
// Self-loops are permitted but they must increase consumption in some capacity.
public class Graph{
	Map <String, Vertex> vertexMap;
	public Map <Vertex, VertexAttributes> vertexResources;
	
	// Kind of like an adjacency list
	Map <Vertex,ArrayList<Vertex>> adjacencyList;
	
	// This may seem wrong because Lists can change 
	// but as long as we are careful, it should be ok.
	// List<String> for edgeMap key is (s,t) of the edge.
	// This means it maps Strings (s,t) to Edges.
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
		
		// Put a new list in adjacencyList for this vertex
		ArrayList<Vertex> succList = new ArrayList<>();
		
		// Create empty list of Labels for vertex attributes and map it to current vertex.
		VertexAttributes attr = new VertexAttributes();
		vertexResources.put(newVertex, attr);
		
		// This creates an empty list for strings for each vertex
		adjacencyList.put(newVertex, succList);
		return newVertex;
	}
	
	public void addEdge(Vertex s, Vertex t) {
		Edge newEdge = new Edge(s,t);
		edgeMap.put(Arrays.asList(s.ID, t.ID),newEdge);
		List<Vertex>succList = adjacencyList.get(s);
		succList.add(t);
		
		// Now make an Edge attribute and add it to the map.
		// The attributes are set to 0 due to constructor.
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
