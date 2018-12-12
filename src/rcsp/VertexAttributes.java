package rcsp;

import java.util.ArrayList;
import java.util.List;

public class VertexAttributes{
	public List<Label> vertexLabels;
	
	// Time Window
	public int earliestTime;
	public int latestTime;
	
	
	public VertexAttributes() {
		// Create empty List of labels
		vertexLabels = new ArrayList<Label>();
	}
	
	public VertexAttributes(int e, int l) {
		// Create empty List of labels
		vertexLabels = new ArrayList<Label>();
		earliestTime = e;
		latestTime = l;
	}
	
	public void set(int e, int l) {
		earliestTime = e;
		latestTime = l;
	}
}
