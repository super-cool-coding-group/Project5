package src.GraphADT;

import src.ListADT.*;
import src.QueueADT.*;
import src.StackADT.*;

/**
 * An implementation of the GraphInterface interface to store graphs using an Adjacency List
 *
 * We have variables for the list itself, a list of vertices, and a number of edges calculation.
 *
 * We also have methods to add a vertex, add an edge between two vertices, run a BFS traversal,
 * and a DFS traversal (in addition to some miscellaneous methods like clear and isEmpty)
 *
 * @author George Matta
 * @author Pierlorenzo Perruzo
 * @version 1.0
 */
public class ListGraph<T> implements GraphInterface<T> {

    /**
     * The adjacency List of the graph.
     */
    private ListInterface<ListInterface<T>> adjList;

    /**
     * A list of vertices
     */
    private ListInterface<T> vertices;

    /**
     * An int denoting the current number of edges in the graph.
     */
    private int numEdges;

    /**
     * A boolean that keeps track of whether or not the constructor was properly
     * called.
     */
    private boolean integrityOk = false;

    /**
     * The default/minimum capacity of any Adjacency Matrix. For this
     * implementation, the default capacity is 10.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default Constructor.
     */
    public ListGraph() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the Graph's adjList and vertex list with a specific capacity.
     *
     * @param initialCapacity the initial capacity for the adjList.
     */
    public ListGraph(int initialCapacity) {
        // Initialize the adjacency matrix
        adjList = new ResizeableList<>(initialCapacity);
        // Initialize the vertices
        vertices = new ResizeableList<>(initialCapacity);
        // Initialize the number of edges to 0
        numEdges = 0;
        // Make the integrity okay
        integrityOk = true;
    }

    /**
     * Checks the integrity of the graph to make sure the constructor was
     * called.
     *
     * @throws SecurityException if the graph object was corrupted in some way or
     *                           the
     *                           constructor wasn't run properly.
     */
    private void checkIntegrity() {
        if (!integrityOk) {
            throw new SecurityException("Graph object is corrupt or was not initialized properly.");
        }
    }

     /**
     * Adds a vertex to the graph.
     * If the graph already contains the vertex, we don't add it again.
     * To add a vertex, we add the vertex to the vertices list and we create a new entry for it in the adjacency list.
     *
     * @param vertex The vertex we are adding to the graph.
     * @return A boolean denoting whether or not we sucessfully added the vertex.
     */
    @Override
    public boolean addVertex(T vertex) {
        // Check the integrity
        checkIntegrity();

        // If the vertex is already in the graph, don't add it again
        if (vertices.contains(vertex)) {
            return false;
        }

        // Add the vertex to the vertices list
        vertices.add(vertex);

        // Add a new entry to the adjacency list
        adjList.add(new ResizeableList<T>());

        // We sucessfully added the vertex, so return true
        return true;
    }

    /**
     * Adds an edge between two vertices, a begin and an end vertex.
     * If either of the vertices don't exist in the graph, we return false.
     * Otherwise, we add the end vertex to the adjList's begin vertex's index's list
     *
     * @param begin The starting vertex of the edge.
     * @param end The ending vertex of the edge.
     * @return A boolean denoting whether or not we sucessfully added an edge.
     */
    @Override
    public boolean addEdge(T begin, T end) {
        // Check the integrity
        checkIntegrity();

        // Check if the beginning index exists in the vertices
        int beginIndex = vertices.getIndexOf(begin);

        // If the beginning vertex doesn't exist, return false
        if (beginIndex == -1) {
            return false;
        }

        // If the end vertex doesn't exist, return false
        if(!vertices.contains(end)){
            return false;
        }

        // Get the list of adjacent vertices of the beginning vertex
        ListInterface<T> adjacentVertices = adjList.get(beginIndex);

        // If the end vertex is already in that list, return false
        if (adjacentVertices.contains(end)){
            return false;
        }

        // Otherwise, add the edge
        adjacentVertices.add(end);

        // Increment the number of edges
        numEdges++;

        // Return true since we added the edge
        return true;
    }

    /**
     * Checks whether or not an edge exists between two vertices.
     *
     * @param begin The starting vertex of the edge.
     * @param end The ending vertex of the edge.
     * @return A boolean denoting whether or not an edge exists between two vertices.
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        // Check the integrity
        checkIntegrity();

        // Get the beginning vertex's index
        int beginIndex = vertices.getIndexOf(begin);

        // If the beginning vertex doesn't exist, return false
        if (beginIndex == -1) {
            return false;
        }

        // If the ending vertex doesn't exist, return false
        if(!vertices.contains(end)){
            return false;
        }

        // Return whether or not the ending vertex is adjacent to the beginning
        return adjList.get(beginIndex).contains(end);
    }

    /**
     * Returns whether or not the graph is empty
     *
     * @return A boolean denoting whether or not the graph is empty.
     */
    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return adjList.isEmpty();
    }

    /**
     * Gets the number of vertices in the graph
     *
     * @return The number of entries in the vertices list.
     */
    @Override
    public int getNumberOfVertices() {
        checkIntegrity();
        return vertices.getNumEntries();
    }

    /**
     * Gets the number of edges in the graph.
     *
     * @return The numEdges field.
     */
    @Override
    public int getNumberOfEdges() {
        checkIntegrity();
        return numEdges;
    }

     /**
     * Clears a graph of all its vertices and edges.
     */
    @Override
    public void clear() {
        checkIntegrity();
        adjList.clear();
        vertices.clear();
        numEdges = 0;
    }

     /**
     * Gets the Breadth-First traversal of a graph given an origin
     *
     * @param origin The origin vertex we are starting traversal from
     * @return A QueueInterface object of the traversal.
     */
    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        checkIntegrity();

        // The visited bool array holds bools such that visited[i-1] = whether or not vertices.get(i) is visited
        boolean visited[] = new boolean[vertices.getNumEntries()];
        QueueInterface<T> queue = new Queue<>();

        // Add to the visited vertex the passed origin
        queue.enqueue(origin);
        visited[vertices.getIndexOf(origin) - 1] = true;

        // The queue to hold the final result of the visited vertex
        QueueInterface<T> resQueue = new Queue<>();

        // Loop through the queue until is empty
        while (!queue.isEmpty()) {
            // Add the visited vertex the result queue
            resQueue.enqueue(queue.getFront());
            // Now we dequeue the first vertex
            T currentVertex = queue.dequeue();
            // And we use the element to find its index in the matrix
            int index = vertices.getIndexOf(currentVertex);
            // So finally we can get the row of the matrix containing the adjacent vertices
            var row = adjList.get(index);

            // We loop through the row
            for (int i = 1; i <= row.getNumEntries(); i++) {

                T adjVertx =row.get(i);
                if (adjVertx == null)
                    continue;

                int adjVertexIndex = vertices.getIndexOf(adjVertx);
                if (!visited[adjVertexIndex-1]){
                    // If this is the case, we register the vertex
                    queue.enqueue(adjVertx);
                    // And we also make sure to mark the vertex as visited
                    visited[adjVertexIndex-1] = true;
                }
            }
        }

        return resQueue;
    }

     /**
     * Gets the Depth-First traversal of a graph given an origin
     *
     * @param origin The origin vertex we are starting traversal from
     * @return A QueueInterface object of the traversal.
     */
    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        // Check the integrity of the graph
        checkIntegrity();

        // Initialize variables to calculate the traversal
        // The traversal queue holds the final traversal we will return
        QueueInterface<T> traversal = new Queue<>();
        // The remainingVertices stack holds the vertices that we are yet to fully complete
        StackInterface<T> remainingVertices = new Stack<>();
        // The visited bool array holds bools such that visited[i-1] = whether or not vertices.get(i) is visited
        boolean[] visited = new boolean[vertices.getNumEntries()];

        // Begin traversing from the origin
        // We find the index of the origin
        int currVertexIndex = vertices.getIndexOf(origin);
        // The origin is the first item in the traversal
        traversal.enqueue(origin);
        // We now need to look through the neighbors of the origin, so we add the origin to the remaining vertices
        remainingVertices.push(origin);
        // We also set the origin to be visited in the visited array
        visited[currVertexIndex-1] = true;

        // Initialize variables to loop
        // The row associated with each vertex in the adjacency list
        ListInterface<T> currVertexRow;
        // Loop until we have no more vertices to check
        while(!remainingVertices.isEmpty()){
            // Get the latest index of the top vertex in the stack
            currVertexIndex = vertices.getIndexOf(remainingVertices.peek());
            // Populate the currVertexRow variable from the adjacency list
            currVertexRow = adjList.get(currVertexIndex);

            // If we're at a leaf, just pop it
            if(currVertexRow.getNumEntries() == 0){
                remainingVertices.pop();
                continue;
            }

            for(int i = 1; i <= currVertexRow.getNumEntries(); i++){
                int vertIndex = vertices.getIndexOf(currVertexRow.get(i));
                if(visited[vertIndex - 1]){
                    if(i == currVertexRow.getNumEntries()){
                        remainingVertices.pop();
                    }
                    continue;
                }
                // Since we are at a new vertex that hasn't been visited and is a neighbor
                // We add the vertex to our traversal
                traversal.enqueue(vertices.get(vertIndex));
                // We also need to look at this vertex's neighbors
                remainingVertices.push(vertices.get(vertIndex));
                // We mark it as visited
                visited[vertIndex-1] = true;
                // Break, we only want to look at one vertex at a time
                break;
            }
        }

        // Return the final traversal
        return traversal;
    }

    /**
     * Returns a string representation of a ResizeableList adjList
     *
     * @return The string representation of the adjList
     */
    public String previewList() {
        String out = "";

        // Loop through the adjacency list
        for (int i = 1; i <= adjList.getNumEntries(); i++) {
            // Initialize the sidebar of the vertex labels
            out += vertices.get(i) + " -> ";
            // Loop through each sublist of the matrix
            for (int j = 1; j <= adjList.get(i).getNumEntries(); j++) {
                // Calculate the status of the matrix
                T status = this.adjList.get(i).get(j);
                if (status == null) {
                    out += ""; // If it's false, it's nothing
                } else {
                    out += status; // If there's an edge, we put a 1
                }
                out += " -> "; // Spacer for formatting
            }
            out += "|\n"; // New line for formatting. We go to the next sublist
        }

        // Return the final output
        return out;
    }
}
