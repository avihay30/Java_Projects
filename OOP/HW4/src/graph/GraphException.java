package graph;

// Exception for errors that accrues during manipulations on a Graph.
public class GraphException extends Exception {
    private static final long serialVersionUID = 1L;

    public GraphException(String message) {
        super(message);
    }
}
