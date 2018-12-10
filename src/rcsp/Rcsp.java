package rcsp;

import java.util.*;

// Generics are difficult in java, for instance Label cannot be a generic because every loop a new label is created.
// We don't really want to have to pass the class twice as a type parameter and as a reference in order to create it.
public class Rcsp{
	private int numberOfIterations = 0;
	private int numberOfLabels = 0;	
	
	// TODO: Add defaults for Label, ResourceContainer etc...
	// TODO: Make the algorithm more general in some way. Maybe using interfaces.
	// The C++ templates shown on Boost are impossible to have in Java due to
	// parameterized classes constructors being used which Java does not allow because
	// of type erasure.

	
	// TODO: Possibly make this algorithm static, similarly to the Boost implementation.
	public List<Label> rcspAlgorithm(Graph g, Vertex startVertex, Vertex endVertex,
							  LabelDominationFunction labelDominationFunction,
							  ResourceExtensionFunction resourceExtenstionFunction) {
		
		int labelNumber = 0;
		// Set the number of iterations to 0 at the start of this algorithm.
		numberOfIterations = 0;		
		//ResourceContainer startingResources = new ResourceContainer(0);
		//Constructor labelConstructor = Label.class.getConstructor(int);
		
		// Initialize the first label with resources		
		Label firstLabel = new Label(labelNumber++);
		firstLabel.residentVertex = startVertex;
		
		Label currentLabel;
		
		// This is resource consumption at the initial vertex
		firstLabel.resources.addAll(Arrays.asList(0,0));
		
		PriorityQueue<Label> unprocessedLabels = new PriorityQueue<Label>();
		unprocessedLabels.add(firstLabel);
		
		while(unprocessedLabels.size() != 0) {
			currentLabel = unprocessedLabels.poll();
			
			if(!currentLabel.isDominated) {
				Vertex residentVertex = currentLabel.residentVertex;
				List<Label> labelList = g.vertexResources.get(residentVertex).vertexLabels;
				
				for (Label vertexLabel : labelList) {
					DominationType labelDom;
					if (currentLabel != vertexLabel) {
						labelDom = labelDominationFunction.labelDomination(currentLabel,vertexLabel);
					}
					// Cannot check label domination of same label. So skip.
					else {
						continue;
					}
					// The labelDominationFunction should return:
					// The enumeration defined
					// This could be generalized later, but how?
					if (labelDom == DominationType.FIRSTLABELDOMINATED) {
						currentLabel.isDominated = true;
					}
					else if (labelDom == DominationType.SECONDLABELDOMINATED ) {
						vertexLabel.isDominated = true;
					}
				}
				
				// Now delete any labels in the resident vertex that is dominated AND processed
				// See https://stackoverflow.com/questions/10431981/remove-elements-from-collection-while-iterating
				ListIterator<Label> iter = labelList.listIterator();
				while(iter.hasNext()) {
					if(iter.next().isDominated && iter.next().isProcessed) {
						iter.remove();
					}
				}
			}// End of FIRST if(!currentLabel.isDominated)
			
			if(!currentLabel.isDominated) {
				currentLabel.isProcessed = true;
				
				// Now for all adjacent vertices. I.E forward star
				// Look at the graphs adjacencyList for this vertex
				// List<Vertex> adjacentVertices = g.adjacencyList.get(currentLabel.residentVertex);
				for(Vertex t : g.adjacencyList.get(currentLabel.residentVertex)) {
					// Now we have the current vertex and end vertex.
					// So now we do this for each edge
					Edge adjacentEdge = g.getEdge(currentLabel.residentVertex.ID, t.ID);
					LabelFeasibility newLabelFeasibility = resourceExtenstionFunction.ref(currentLabel,adjacentEdge);
					if(newLabelFeasibility.feasible) {
						unprocessedLabels.add(newLabelFeasibility.label);
						
						// This long line just adds the new label to its respective vertex label list.
						g.vertexResources.get(newLabelFeasibility.label.residentVertex).vertexLabels.add(newLabelFeasibility.label);
						labelNumber++;
					}
				}
			}// End of SECOND if(!currentLabel.isDominated)
			else {
				// Remove this label from its label list because it is dominated.
				// In other words we need to remove it from the vertex list.
				// This is because this will be the only place it will exist.
				g.vertexResources.get(currentLabel.residentVertex).vertexLabels.remove(currentLabel);
			}// End of else statement
			numberOfIterations++;
		}//End of while(unprocessedLabel.size() != 0)
		numberOfLabels = labelNumber;
		// Return labelList of terminal vertex, could be empty if no feasible paths exist.
		return g.vertexResources.get(endVertex).vertexLabels;
		
	}//End of rcspAlgorithm
}
