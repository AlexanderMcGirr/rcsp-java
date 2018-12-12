package rcsp;

public class Label implements Comparable<Label>{
	public Vertex residentVertex;
	public Edge predecessorEdge;
	public Label predecessorLabel;
	public AccumulatedResources resourceConsumption;
	public int labelID;
	public boolean isDominated = false;
	public boolean isProcessed = false;
	public boolean isFeasible = true;
	
	// This function is only used for first label created.
	public Label(int num){
		residentVertex = null;
		predecessorEdge = null;
		predecessorLabel = null;
		resourceConsumption = new AccumulatedResources();
		labelID = num;
	}
	
	// Comparison function
	public int compareTo(Label second) {
		if(labelID > second.labelID) {
			return 1;
		}
		else if(labelID < second.labelID) {
			return -1;
		}
		else {
			return 0;
		}
	}
}