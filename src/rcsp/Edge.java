package rcsp;


public class Edge {
	int edgeId;
	static int counter = 0;
	Vertex source;
	Vertex terminal;
	
	public Edge(Vertex s, Vertex t) {
		source = s;
		terminal = t;
		edgeId = counter;
		counter++;
	}
	
	@Override
	public String toString() {
		return String.valueOf(edgeId);
	}
}
