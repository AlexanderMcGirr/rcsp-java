package rcsp;

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
	public void rcspAlgorithm() {
		
		int labelNumber = 0;
		// Set the number of iterations to 0 at the start of this algorithm.
		numberOfIterations = 0;		
		//ResourceContainer startingResources = new ResourceContainer(0);
		//Constructor labelConstructor = Label.class.getConstructor(int);
		
		// Initialize the first label with resources		
		Label currentLabel = new Label(labelNumber++);
		Label newLabel;
		
	}
}
