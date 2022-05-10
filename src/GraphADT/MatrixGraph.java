package src.GraphADT;

import src.ListADT.*;
import src.QueueADT.*;
import src.StackADT.*;

/**
 * An implementation of the GraphInterface interface to store graphs using an Adjacency Matrix
 *
 * We have variables for the matrix itself, a list of vertices, and a number of edges calculation.
 *
 * We also have methods to add a vertex, add an edge between two vertices, run a BFS traversal,
 * and a DFS traversal (in addition to some miscellaneous methods like clear and isEmpty)
 *
 * @author George Matta
 * @author Pierlorenzo Perruzo
 * @version 2.0
 */
public class MatrixGraph<T> implements GraphInterface<T> {

    /**
     * The adjacency matrix of the graph.
     */
    private ListInterface<ListInterface<Boolean>> adjMatrix;

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
    public MatrixGraph() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the Graph's matrix and vertex list with a specific capacity.
     *
     * @param initialCapacity the initial capacity for the matrix.
     */
    public MatrixGraph(int initialCapacity) {
        // Initialize the adjacency matrix
        adjMatrix = new ResizeableList<>(initialCapacity);
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
     * To add a vertex, we add the vertex to the vertices list and we create a new entry for it in the adjacency matrix.
     *
     * @param vertex The vertex we are adding to the graph.
     * @return A boolean denoting whether or not we sucessfully added the vertex.
     */
    @Override
    public boolean addVertex(T vertex) {
        // Check the integrity
        checkIntegrity();

        // Check if the vertex is already in the graph
        if (vertices.contains(vertex)) {
            return false;
        }

        // Since it is a unique vertex, we add it to the vertices
        vertices.add(vertex);

        // And add it to the adjMatrix
        adjMatrix.add(new ResizeableList<Boolean>());

        // We sucessfully added the vertex, so return true
        return true;
    }

    /**
     * Adds an edge between two vertices, a begin and an end vertex.
     * If either of the vertices don't exist in the graph, we return false.
     * Otherwise, we set the adjacencyMatrix[begin][end] to be true and increment the number of edges
     *
     * @param begin The starting vertex of the edge.
     * @param end The ending vertex of the edge.
     * @return A boolean denoting whether or not we sucessfully added an edge.
     */
    @Override
    public boolean addEdge(T begin, T end) {
        // Check the integrity
        checkIntegrity();

        // Get the index of the beginning vertex and the ending vertex from the vertices
        int beginIndex = vertices.getIndexOf(begin);
        int endIndex = vertices.getIndexOf(end);

        // If either vertex doesn't exist, return
        if (beginIndex == -1 || endIndex == -1) {
            return false;
        }

        // Otherwise, set the edge between them to be true in the adjMatrix
        adjMatrix.get(beginIndex).add(endIndex, true);

        // Increment the num of edges
        numEdges++;

        // Return true since we sucessfully added the edge
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

        // Get the indices of the beginning and ending vertex in the vertcies list
        int beginIndex = vertices.getIndexOf(begin);
        int endIndex = vertices.getIndexOf(end);

        // If either of the vertices don't exist, there is no edge
        if (beginIndex == -1 || endIndex == -1) {
            return false;
        }

        // Otherwise, get the status of the edge
        Boolean status = adjMatrix.get(beginIndex).get(endIndex);

        // Since it is a Boolean object rather than a boolean primative, it may be null.
        // If it's null, there's no edge.
        if (status == null) {
            status = false;
        }

        // Return the status of the edge's existence
        return status;
    }

    /**
     * Returns whether or not the graph is empty
     *
     * @return A boolean denoting whether or not the graph is empty.
     */
    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return adjMatrix.isEmpty();
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
        adjMatrix.clear();
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
            var row = adjMatrix.get(index);

            // We loop through the row
            for (int i = 1; i <= row.getNumEntries(); i++) {

                // Check if the value of row[i] is 1 (ie: there is a connection to our currentVertex)
                // and if we did not already visited this vertex (i-1 because the array is index 0 while the ListInterface is index 1)
                if ((row.get(i) != null && row.get(i)) && !visited[i-1]) {
                    // If this is the case, we register the vertex
                    queue.enqueue(vertices.get(i));
                    // And we also make sure to mark the vertex as visited
                    visited[i-1] = true;
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
        // The row associated with each vertex in the adjacency matrix
        ListInterface<Boolean> currVertexRow;
        // The bool of whether or not each vertex in the row is a neighbor or not
        Boolean neighborVertexAdj;
        // Loop until we have no more vertices to check
        while(!remainingVertices.isEmpty()){
            // Get the latest index of the top vertex in the stack
            currVertexIndex = vertices.getIndexOf(remainingVertices.peek());
            // Populate the currVertexRow variable from the adjacency matrix
            currVertexRow = adjMatrix.get(currVertexIndex);

            // If we're at a leaf, just pop it
            if(currVertexRow.getNumEntries() == 0){
                remainingVertices.pop();
                continue;
            }

            // Loop through the currVertexRow to check for neighbors
            for(int i = 1; i <= currVertexRow.getNumEntries(); i++){
                // Populate our neighbor variable
                neighborVertexAdj = currVertexRow.get(i);
                // If the vertex is not a neighbor, or the vertex is already visited
                if(neighborVertexAdj == null || !neighborVertexAdj || visited[i - 1]){
                    // If we're at the end of the row, we need to pop the vertex
                    // Since we are sure that there are no more neighbors in this vertex to check
                    if(i == currVertexRow.getNumEntries()){
                        // Remove the current vertex from the stack
                        remainingVertices.pop();
                    }
                    continue;
                }

                // Since we are at a new vertex that hasn't been visited and is a neighbor
                // We add the vertex to our traversal
                traversal.enqueue(vertices.get(i));
                // We also need to look at this vertex's neighbors
                remainingVertices.push(vertices.get(i));
                // We mark it as visited
                visited[i-1] = true;
                // Break, we only want to look at one vertex at a time
                break;
            }

        }

        // Return the final traversal
        return traversal;
    }

    /**
     * Returns a string representation of a ResizeableList matrix
     *
     * @return The string representation of the matrix
     */
    public String previewMatrix() {
        String out = "  ";

        // Initialize the header of the vertex labels
        for (int i = 1; i <= vertices.getNumEntries(); i++) {
            out += vertices.get(i) + " ";
        }
        out += "\n";

        // Loop through the adjacency matrix
        for (int i = 1; i <= adjMatrix.getNumEntries(); i++) {
            // Initialize the sidebar of the vertex labels
            out += vertices.get(i) + " ";
            // Loop through each sublist of the matrix
            for (int j = 1; j <= adjMatrix.get(i).getNumEntries(); j++) {
                // Calculate the status of the matrix
                Boolean status = this.adjMatrix.get(i).get(j);
                if (status == null || !status) {
                    out += " "; // If it's false, it's a space
                } else {
                    out += "1"; // If there's an edge, we put a 1
                }
                out += " "; // Spacer for formatting
            }
            out += "\n"; // New line for formatting. We go to the next sublist
        }

        // Return the final output
        return out;
    }
}
