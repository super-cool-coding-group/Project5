package src.GraphADT;

import src.ListADT.*;
import src.QueueADT.*;
import src.StackADT.*;
import src.DictionaryADT.*;

public class MatrixGraph<T> implements GraphInterface<T>{

    /**
     * The adjacency matrix of the graph.
     */
    private ListInterface<ListInterface<Boolean>> adjMatrix;

    /**
     * A dictionary of T-int pairs. The int is the 'index' of the vertex, and T is the label
     */
    private DictionaryInterface<T, Integer> vertexLabels;

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
     * Initialize the Graph's matrix with a specific capacity.
     *
     * @param initialCapacity the initial capacity for the matrix.
     */
    public MatrixGraph(int initialCapacity){
        adjMatrix = new ResizeableList<>(initialCapacity);
        for(int i = 0; i < initialCapacity; i++){
            adjMatrix.add(new ResizeableList<>(initialCapacity));
        }
        numEdges = 0;
        integrityOk = true;
    }

    /**
     * Checks the integrity of the Queue to make sure the constructor was
     * called.
     *
     * @throws SecurityException if the Queue object was corrupted in some way or the
     *                           constructor wasn't run properly.
     */
    private void checkIntegrity(){
        if (!integrityOk) {
            throw new SecurityException("Queue object is corrupt or was not initialized properly.");
        }
    }


    @Override
    public boolean addVertex(T vertex) {
        // TODO Auto-generated method stub
        checkIntegrity();

        if(vertexLabels.contains(vertex)){
            return false;
        }

        vertexLabels.add(vertex, vertexLabels.getSize() + 1);
        return true;
    }

    @Override
    public boolean addEdge(T begin, T end) {
        // TODO Auto-generated method stub
        checkIntegrity();

        Integer beginIndex = vertexLabels.getValue(begin);
        Integer endIndex = vertexLabels.getValue(end);

        if(beginIndex == null || endIndex == null){
            return false;
        }

        adjMatrix.get(beginIndex).set(endIndex, true);
        numEdges++;

        return true;
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        // TODO Auto-generated method stub
        checkIntegrity();

        Integer beginIndex = vertexLabels.getValue(begin);
        Integer endIndex = vertexLabels.getValue(end);

        if(beginIndex == null || endIndex == null){
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
        return vertexLabels.getSize();
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
        vertexLabels.clear();
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

}