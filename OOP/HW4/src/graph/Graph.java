package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// An abstract class that represent a graph
// that able to check if 2 vertices are connected or not
public class Graph<V> {

    private Set<V> vertices = new HashSet<>();
    // key (=some vertex): all connected vertices
    private Map<V, Set<V>> edges = new HashMap<>();

    /**
     * add new Vertex to Graph
     *
     * @param v the specified vertex to add
     * @throws GraphException when the vertex is already exists
     */
    public void addVertex(V v) throws GraphException {
        if (!vertices.add(v)) { // if already exists
            throw new GraphException("The Vertex is already exists in the graph");
        }
    }

    /**
     * Adds undirected Edge (v1, v2) to the Graph
     *
     * @throws GraphException if one of the specified Vertices isn't exist in the graph
     *                        or the Edge is already exists in the graph
     */
    public void addEdge(V v1, V v2) throws GraphException {
        // check validation of specified vertices, if one of them isn't exists
        if (!vertices.contains(v1) || !vertices.contains(v2)) {
            throw new GraphException("Some of given Vertices aren't exists in the graph");
        }
        edges.putIfAbsent(v1, new HashSet<>()); // adding v1 edge if it's doesn't exist
        edges.putIfAbsent(v2, new HashSet<>()); // adding v2 edge if it's doesn't exist
        Set<V> connectedToV1 = edges.get(v1);
        Set<V> connectedToV2 = edges.get(v2);

        // if the edge from V1 to V2 or vice versa is already connected
        // add method of Set - returns false if the value is exists
        if (!connectedToV1.add(v2) || !connectedToV2.add(v1)) {
            throw new GraphException("The Edge is already exists in the graph");
        }
    }

    /**
     * @return true if Edge (v1, v2) exists in the Graph
     */
    public boolean hasEdge(V v1, V v2) {
        return edges.get(v1).contains(v2) && edges.get(v2).contains(v1);
    }

    /**
     * @return true if vertex v2 is reachable from v1 (or vice versa)
     * @throws GraphException if one of the specified Vertices isn't exist in the graph
     */
    public boolean connected(V v1, V v2) throws GraphException {
        // check validation of specified vertices, if one of them isn't exists
        if (!vertices.contains(v1) || !vertices.contains(v2)) {
            throw new GraphException("Some of given Vertices aren't exists in the graph");
        }

        // DFS
        // to improve performance - marking checked vertices in Connected method
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
        for (V neighbor : edges.get(v1)) { // iterating over all neighbors of v1
            if (neighbor.equals(v2)) return true;
            else if (isConnected(neighbor, v2, markedVertices)) return true;
        }
        return false; // if there are no neighbors to v1
    }

//    public static void main(String[] args) throws GraphException {
//        Graph<Integer> g = new Graph<>();
//        for (int i = 0; i < 100; i++)
//            g.addVertex(i);
//        for (int i = 0; i < 50; i++)
//            g.addEdge(i, i+1);
//        System.out.println(g.connected(1, 10));
//        System.out.println(g.connected(3, 70));
//    }
}
