package rcsp;

public class AccumulatedResources {
	public int cost;
	public int time;
	
	
	public AccumulatedResources() {
		cost = 0;
		time = 0;
	}
	public AccumulatedResources(int c, int t) {
		cost = c;
		time = t;
	}
	
	public void set(int c, int t) {
		cost = c;
		time = t;
	}
}
