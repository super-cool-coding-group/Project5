package src.GraphADT;

import src.ListADT.*;
import src.QueueADT.*;
import src.StackADT.*;

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

    @Override
    public boolean addVertex(T vertex) {
        // TODO Auto-generated method stub
        checkIntegrity();

        if (vertices.contains(vertex)) {
            return false;
        }

        vertices.add(vertex);

        adjMatrix.add(new ResizeableList<Boolean>());

        return true;
    }

    @Override
    public boolean addEdge(T begin, T end) {
        // TODO Auto-generated method stub
        checkIntegrity();

        int beginIndex = vertices.getIndexOf(begin);
        int endIndex = vertices.getIndexOf(end);

        if (beginIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix.get(beginIndex).add(endIndex, true);
        numEdges++;

        return true;
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        // TODO Auto-generated method stub
        checkIntegrity();

        int beginIndex = vertices.getIndexOf(begin);
        int endIndex = vertices.getIndexOf(end);

        if (beginIndex == -1 || endIndex == -1) {
            return false;
        }

        Boolean status = adjMatrix.get(beginIndex).get(endIndex);
        if (status == null) {
            status = false;
        }

        return status;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        checkIntegrity();
        return adjMatrix.isEmpty();
    }

    @Override
    public int getNumberOfVertices() {
        // TODO Auto-generated method stub
        checkIntegrity();
        return vertices.getNumEntries();
    }

    @Override
    public int getNumberOfEdges() {
        // TODO Auto-generated method stub
        checkIntegrity();
        return numEdges;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        checkIntegrity();
        adjMatrix.clear();
        vertices.clear();
    }

    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        checkIntegrity();

        // Check if origin is in the vertices
        // TODO

        boolean visited[] = new boolean[vertices.getNumEntries()];
        QueueInterface<T> queue = new Queue<>();

        queue.enqueue(origin);
        visited[vertices.getIndexOf(origin) - 1] = true;

        QueueInterface<T> traversalQueue = new Queue<>();

        while (!queue.isEmpty()) {
            traversalQueue.enqueue(queue.getFront());
            T e = queue.dequeue();
            int index = vertices.getIndexOf(e);

            var row = adjMatrix.get(index);
            for (int i = 1; i <= row.getNumEntries(); i++) {

                if ((row.get(i) != null && row.get(i)) && !visited[i-1]) {
                    queue.enqueue(vertices.get(i));
                    visited[i-1] = true;
                }
            }
        }

        return traversalQueue;
    }

    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        // TODO Auto-generated method stub
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
