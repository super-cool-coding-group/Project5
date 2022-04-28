package src.GraphADT;

import src.ListADT.*;
import src.QueueADT.*;
import src.StackADT.*;

public class MatrixGraph<T> implements GraphInterface<T>{

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
     * A boolean that keeps track of whether or not the constructor was properly called.
     */
    private boolean integrityOk = false;

    /**
     * The default/minimum capacity of any Adjacency Matrix. For this implementation, the default capacity is 10.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default Constructor.
     */
    public MatrixGraph(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the Graph's matrix and vertex list with a specific capacity.
     *
     * @param initialCapacity the initial capacity for the matrix.
     */
    public MatrixGraph(int initialCapacity){
        // Initialize the adjacency matrix
        adjMatrix = new ResizeableList<>(initialCapacity);
<<<<<<< HEAD
        // Initialize the vertices
=======
        vertices = new ResizeableList<>(initialCapacity);
        for(int i = 0; i < initialCapacity; i++){
            ListInterface<Boolean> subList = new ResizeableList<>(initialCapacity);
            for(int ii = 0; ii < initialCapacity; ii++){
                subList.add(false);
            }
            adjMatrix.add(subList);
        }
>>>>>>> a84c8013a5d37b4d38666954a3872de0c0b20030
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
     * @throws SecurityException if the graph object was corrupted in some way or the
     *                           constructor wasn't run properly.
     */
    private void checkIntegrity(){
        if (!integrityOk) {
            throw new SecurityException("Graph object is corrupt or was not initialized properly.");
        }
    }


    @Override
    public boolean addVertex(T vertex) {
        // TODO Auto-generated method stub
        checkIntegrity();

        if(vertices.contains(vertex)){
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

        if(beginIndex == -1 || endIndex == -1){
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

        if(beginIndex == -1 || endIndex == -1){
            return false;
        }

        Boolean status = adjMatrix.get(beginIndex).get(endIndex);
        if(status == null){
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
        // TODO Auto-generated method stub
        checkIntegrity();
        return null;
    }

    @Override
    public StackInterface<T> getDepthFirstTraversal(T origin) {
        // TODO Auto-generated method stub
        checkIntegrity();
        return null;
    }

     /**
     * Returns a string representation of a ResizeableList matrix
     *
     * @return The string representation of the matrix
     */
    public String previewMatrix(){
        String out = "  ";

        // Initialize the header of the vertex labels
        for(int i = 1; i <= vertices.getNumEntries(); i++){
            out += vertices.get(i) + " ";
        }
        out += "\n";

        // Loop through the adjacency matrix
        for(int i = 1; i <= adjMatrix.getNumEntries(); i++){
            // Initialize the sidebar of the vertex labels
            out += vertices.get(i) + " ";
            // Loop through each sublist of the matrix
            for(int j = 1; j <= adjMatrix.get(i).getNumEntries(); j++){
                // Calculate the status of the matrix
                Boolean status = this.adjMatrix.get(i).get(j);
                if(status == null || !status){
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
