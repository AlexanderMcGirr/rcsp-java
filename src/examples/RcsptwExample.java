package examples;

import rcsp.*;
import java.util.*;

public class RcsptwExample {
	
	// Implement the resource extension function
	public static class REF implements ResourceExtensionFunction{
		public LabelFeasibility ref(Graph g,Label firstLabel, Edge edge, int labelNumber) {
			EdgeAttributes attr = g.edgeConsumption.get(edge);
			
			// Now take previous label and add time and cost
			// Maybe put this part in the actual class?
			Label labelNew = new Label(labelNumber);
			labelNew.residentVertex = edge.terminal;
			labelNew.predecessorEdge = edge;
			labelNew.predecessorLabel = firstLabel;
			
			labelNew.resourceConsumption.cost = firstLabel.resourceConsumption.cost + attr.cost;
			labelNew.resourceConsumption.time = firstLabel.resourceConsumption.time + attr.timeCost;
			
			LabelFeasibility result = new LabelFeasibility();
			result.feasible = true;
			result.label = labelNew;
			
			// Check only latest time window.
			// If early then hold for that time at node
			if(labelNew.resourceConsumption.time > g.vertexResources.get(labelNew.residentVertex).latestTime) {
				result.feasible = false;
			}
			
			// Still true after having waited.
			if(labelNew.resourceConsumption.time < g.vertexResources.get(labelNew.residentVertex).earliestTime) {
				labelNew.resourceConsumption.time = g.vertexResources.get(labelNew.residentVertex).earliestTime;
			}			
			return result;
		}
	}
	
	// Implement specific label domination function
	public static class LDF implements LabelDominationFunction{
		public DominationType labelDomination(Label first, Label second) {
			// One label dominates another if the cost and time cost is absolutely less.
			if( first.resourceConsumption.time <= second.resourceConsumption.time &&
					first.resourceConsumption.cost <= second.resourceConsumption.cost) {
				return DominationType.SECONDLABELDOMINATED;
			}
			
			if( first.resourceConsumption.time >= second.resourceConsumption.time &&
					first.resourceConsumption.cost >= second.resourceConsumption.cost) {
				return DominationType.FIRSTLABELDOMINATED;
			}
			
			return DominationType.NOLABELDOMINATED;
		}
	}
	
	public static void main(String[] args) {
/*		Graph<String,DefaultEdge> g = new DefaultDirectedWeightedGraph<>(DefaultEdge.class);
		
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		
		g.addEdge("A", "C");
	    g.addEdge("B", "B");
	    g.addEdge("B", "D");
	    g.addEdge("B", "E");
	    g.addEdge("C", "B");
	    g.addEdge("C", "D");
	    g.addEdge("D", "E");
	    g.addEdge("E", "A");
	    
	    Iterator<String> iterator = new DepthFirstIterator<String, DefaultEdge>(g, "A");
	    List<String> pred = Graphs.predecessorListOf(g,"D");
	    
	    for(String a: pred)
	    {
	    	System.out.println(a);
	    }
	    
	    System.out.println();
	    
	    while(iterator.hasNext()){
	    	String next = iterator.next();
	    	System.out.println(next);
	    }*/
		
		Rcsp g = new Rcsp();
		g.addVertex("A", 0, 0);
		g.addVertex("B", 5, 20);
		g.addVertex("C", 6, 10);
		g.addVertex("D", 3, 12);
		g.addVertex("E", 0 ,100);		
		
		g.addEdge("A", "C", 1, 5);
	    g.addEdge("B", "B", 2, 5);
	    g.addEdge("B", "D", 1, 2);
	    g.addEdge("B", "E", 2, 7);
	    g.addEdge("C", "B", 7, 3);
	    g.addEdge("C", "D", 3, 8);
	    g.addEdge("D", "E", 1, 3);
	    g.addEdge("E", "A", 1, 5);
	    g.addEdge("E", "B", 1, 5);
		
	    
	    // Make new functions.
	    REF refToPass = new REF();
	    LDF ldfToPass = new LDF();
	    
	    List<Label> results = g.rcspAlgorithm(g.getVertex("A"), g.getVertex("E"), ldfToPass, refToPass);
	    
	    for(Label r: results ) {
	    	System.out.println("[" + r.resourceConsumption.cost + "," + r.resourceConsumption.time + "]");
	    }
		
	}
}
