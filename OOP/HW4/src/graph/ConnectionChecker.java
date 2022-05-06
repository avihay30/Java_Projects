package graph;

import java.util.HashSet;
import java.util.Set;

// class that initialized by some GraphInterface
// and can check if 2 vertices are connected
public class ConnectionChecker<V> {
    private final GraphInterface<V> graph;

    public ConnectionChecker(GraphInterface<V> g) {
        graph = g;
    }

    /**
     * @return true if v2 is reachable from v1 in the graph
     */
    public boolean check(V v1, V v2) {
        Set<V> markedVertices = new HashSet<>();
        return isConnected(v1, v2, markedVertices);
    }

    /**
     * Recursive method for checking if vertex v2 is reachable from v1.
     * By calling recursively on all neighbors of v1
     *
     * @return true if vertex v2 is reachable from v1
     */
    private boolean isConnected(V v1, V v2, Set<V> markedVertices) {
        // if v1 is already been checked before
        if (!markedVertices.add(v1)) return false;

        if (v1.equals(v2)) return true;
        for (V neighbor : graph.neighbours(v1)) {
            if (neighbor.equals(v2)) return true; // if some neighbor is v2
                // recursive call on a neighbor.
                // only if the call returns with true - stops the loop and return true.
            else if (isConnected(neighbor, v2, markedVertices)) return true;
        }
        return false; // if there are no neighbors to v1
    }
}
