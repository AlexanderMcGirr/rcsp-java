package rcsp;

public class Label{
	public int residentVertex;
	public int predecessorEdge;
	public int predecessorLabel;
	public int resources;
	public int labelID;
	public boolean isDominated = false;
	public boolean isProcessed = false;
	public boolean isFeasible = true;
	
	public Label(){
	}
	
	public Label(int num){
	}
}