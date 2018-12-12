package rcsp;

public interface ResourceExtensionFunction {
	public LabelFeasibility ref(Graph g, Label first, Edge edge, int labelNumber);
}