package rcsp;

public class LabelFeasibility {
	public boolean feasible;
	public Label label;
	
	
	public LabelFeasibility() {
	}
	
	public LabelFeasibility(boolean state, Label lab) {
		feasible = state;
		label = lab;
	}
}
