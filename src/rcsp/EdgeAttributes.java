package rcsp;

public class EdgeAttributes {
	public int cost;
	public int timeCost;
	
	// Empty constructor
	public EdgeAttributes() {
		cost = 0;
		timeCost = 0;
	}
	
	public EdgeAttributes(int c, int time) {
		cost = c;
		timeCost = time;
	}
	
	public void set(int c, int time) {
		cost = c;
		timeCost = time;
	}
}
