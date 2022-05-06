package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Class that represent a maze (2 dimensional array)
public class Maze implements GraphInterface<Place> {
    private Place start, end;

    // every wall is null, and all others are Places instances
    private Place[][] maze;

    public Maze(int size, int startx, int starty, int endx, int endy) {
        // if any of the places are not inside the maze
        // will throw IllegalArgumentException
        start = new Place(startx, starty, size);
        end = new Place(endx, endy, size);
        maze = new Place[size][size];
        initMaze(); // init maze with no walls
    }

    /**
     * Adds a wall in the specified (x,y) coordinates
     * could throw IllegalArgumentException if specified (x,y) is OutOfBounds
     *
     * @return true if wall has been added
     */
    public boolean addWall(int x, int y) {
        Place wall = new Place(x, y, maze.length); // throws exception if OutOfBounds
        // if there is a wall or start/end points
        if (maze[x][y] == null || wall.equals(start) || wall.equals(end)) return false;

        maze[x][y] = null;
        return true;
    }

    /**
     * @return true if the maze is solvable.
     * meaning there is a clean path(=without walls) from start to end,
     * using graph representation of the maze (if end is reachable from start).
     */
    public boolean isSolvable() {
        Graph<Place> graph = new Graph<>();
        connectAllVertices(graph);
        connectAllEdges(graph);

        try {
            // returns true if there is a path from start to end
            return graph.connected(start, end);
        } catch (GraphException e) {
            e.printStackTrace();
        }
        return false; // if there is no path from start to end
    }

    /**
     * @param place to look for his neighbours
     * @return a list of all valid neighbours of the specified place
     */
    @Override
    public Collection<Place> neighbours(Place place) {
        List<Place> neighbours = new ArrayList<>(); // a list to hold all neighbours

        Place neighbor = getValidNeighbor(place.getX() - 1, place.getY());
        if (neighbor != null) // Top
            neighbours.add(neighbor);

        neighbor = getValidNeighbor(place.getX(), place.getY() - 1);
        if (neighbor != null) // Left
            neighbours.add(neighbor);

        neighbor = getValidNeighbor(place.getX(), place.getY() + 1);
        if (neighbor != null) // Right
            neighbours.add(neighbor);

        neighbor = getValidNeighbor(place.getX() + 1, place.getY());
        if (neighbor != null) // Bottom
            neighbours.add(neighbor);

        return neighbours;
    }

    /**
     * @return String representation of the maze
     * (S - start, E - end, @ - wall, . - empty path)
     */
    @Override
    public String toString() {
        StringBuilder mazeString = new StringBuilder();

        for (Place[] row : maze) {
            for (Place point : row) {
                if (start.equals(point)) { // if point is start point
                    mazeString.append("S");
                } else if (end.equals(point)) { // if point is end point
                    mazeString.append("E");
                } else if (point == null) { // if it's a wall
                    mazeString.append("@");
                } else mazeString.append(".");
            }
            mazeString.append("\n");
        }
        return mazeString.toString();
    }

    /**
     * filling all maze with places
     * that indicates that there are no walls
     */
    private void initMaze() {
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze.length; j++)
                maze[i][j] = new Place(i, j, maze.length);
    }

    /**
     * Connects all places that are not walls
     * as vertices in the given graph
     */
    private void connectAllVertices(Graph<Place> graph) {
        for (Place[] row : maze) {
            for (Place point : row) {
                if (point != null) { // if point isn't wall
                    connectOneVertex(graph, point);
                }
            }
        }
    }

    /**
     * Connects between all neighbor places that are not walls
     * as edges in the given graph
     */
    private void connectAllEdges(Graph<Place> graph) {
        for (Place[] row : maze) { // iterating over all maze from top-left to bottom-right
            for (Place point : row) {
                if (point != null) { // if point isn't wall
                    // in order to avoid adding the same edge twice
                    // we add only right and bottom edges because of the maze scan order
                    Place neighbor = getValidNeighbor(point.getX(), point.getY() + 1);
                    if (neighbor != null) // Right
                        connectOneEdge(graph, point, neighbor);

                    neighbor = getValidNeighbor(point.getX() + 1, point.getY());
                    if (neighbor != null) // Bottom
                        connectOneEdge(graph, point, neighbor);
                }
            }
        }
    }

    /**
     * Adding new point as vertex in the graph.
     * prints StackTrace - if the new vertex is already exists
     */
    private void connectOneVertex(Graph<Place> graph, Place point) {
        try {
            graph.addVertex(point);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding new connection between places as edge in the graph.
     * prints StackTrace - if the new edge is already exists,
     * or some Vertices aren't exists.
     */
    private void connectOneEdge(Graph<Place> graph, Place from, Place to) {
        try {
            graph.addEdge(from, to);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return A valid place in the maze in the specified x,y.
     * If specified (x,y) is OutOfBounds or the place is a wall returns null
     */
    private Place getValidNeighbor(int x, int y) {
        if (x >= 0 && x < maze.length && y >= 0 && y < maze.length) {
            return maze[x][y]; // return neighbor or null if neighbor is a wall
        }
        return null;
    }

//    public static void main(String[] args) throws GraphException {
//        Maze m = new Maze(4, 0, 0, 3, 3);
//        m.addWall(1, 1);
//        m.addWall(3, 1);
//        m.addWall(0, 1);
//        m.addWall(2, 3);
//        m.addWall(2, 3);
//        m.addWall(1, 3);
//        System.out.println(m);
//        System.out.println(m.isSolvable());
//
//        ConnectionChecker<Place> cc = new ConnectionChecker<>(m);
//        System.out.println(cc.check(new Place(0,0,4), new Place(3, 3,4)));
//    }
}
