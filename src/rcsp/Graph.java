package rcsp;

import java.util.*;

// For RCSP, the graph is going to be directed and weighted.
// Self-loops are permitted but they must increase consumption in some capacity.
public class Graph{
	public Map <String, Vertex> vertexMap;
	public Map <Vertex, VertexAttributes> vertexResources;
	
	// Kind of like an adjacency list
	public Map <Vertex,ArrayList<Vertex>> adjacencyList;
	
	// This may seem wrong because Lists can change 
	// but as long as we are careful, it should be ok.
	// List<String> for edgeMap key is (s,t) of the edge.
	// This means it maps Strings (s,t) to Edges.
	public Map <List<String>,Edge> edgeMap;
	public Map <Edge, EdgeAttributes> edgeConsumption;
	
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
	
	// This attribute is for setting attributes.
	public Vertex addVertex(String name, int c, int t) {
		Vertex result = addVertex(name);
		setVertex(result,c,t);		
		return result;
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
	
	// This overload is for setting attributes
	public void addEdge(String s, String t, int cost, int time) {
		// Find vertices
		Vertex source = getVertex(s);
		Vertex sink = getVertex(t);
		
		// Now use overloaded function to deal with the rest.
		addEdge(source,sink);		
		setEdge(s,t,cost,time);
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
	
	public void setVertex(Vertex s, int e, int l) {
		VertexAttributes a = vertexResources.get(s);
		a.earliestTime = e;
		a.latestTime = l;
	}
	
	public void setVertex(String vert, int e, int l) {
		Vertex s = getVertex(vert);
		setVertex(s, e, l);
	}
	
	public void setEdge(Edge e, int c, int tc ) {
		EdgeAttributes attr = edgeConsumption.get(e);
		attr.cost = c;
		attr.timeCost = tc;
	}
	
	public void setEdge(String s, String t, int c, int tc ) {
		Edge e = edgeMap.get(Arrays.asList(s, t));
		setEdge(e,c,tc);
	}
}
