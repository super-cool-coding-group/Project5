package src.GraphADT;

import src.QueueADT.QueueInterface;

public interface GraphInterface<T>{
    /**
     * Adds a vertex to this graph.
     * @param vertex A new vertex to add to the graph. The vertex given must be distinct.
     * @return True if the vertex is added, or false if not.
     */
    public boolean addVertex(T vertex);

    /**
     * Adds an edge between two given vertices that are currently in this graph. The desired edge must not
     * already be in the graph.
     * @param begin The vertex at one end of the edge
     * @param end The vertex at the other end of the edge
     * @return True if the edge is added, or false if not.
     */
    public boolean addEdge(T begin, T end);

    /**
     * Check whether or not an edge exists between two vertices.
     * @param begin The vertex at one end of the edge
     * @param end The vertex at the other end of the edge
     * @return True if an edge exists.
     */
    public boolean hasEdge(T begin, T end);

    /**
     * Check whether or not the graph is empty.
     * @return  True if the graph is empty.
      */
    public boolean isEmpty();

    /**
     *  Gets the number of vertices in this graph.
     * @return The number of vertices in the graph.
     */
    public int getNumberOfVertices();

    /**
     * Gets the number of edges in this graph.
     * @return The number of edges in the graph.
     */
    public int getNumberOfEdges();

    /**
     * Clears this graph, returning it to an empty graph
     */
    public void clear();

    /**
     * Performs a breadth-first traversal of this graph.
     * @param origin The vertex to begin traversing at.
     * @return A queue of the vertices in the traversal.
     */
    public QueueInterface<T> getBreadthFirstTraversal(T origin);

    /**
     * Performs a depth-first traversal of this graph.
     * @param origin  The vertex to begin traversing at
     * @return  A queue of the vertices in the traversal.
     */
    public QueueInterface<T> getDepthFirstTraversal(T origin);

}
