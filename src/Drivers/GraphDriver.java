package src.Drivers;

import src.GraphADT.*;

/**
 * The GraphDriver class that tests basic functionality of the
 * Graph class.
 *
 * The class only has one method: a main method.
 *
 * @author George Matta
 * @version 1.0
 */
public class GraphDriver {

    public static void main(String[] args){
        // Tests graph constructors
        MatrixGraph<Integer> graph = new MatrixGraph<Integer>();
        MatrixGraph<Integer> graph2 = new MatrixGraph<>(1);

        // Tests addVertex
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        // Tests addEdge
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Tests hasEdge
        System.out.println("Has Edge(true) = " + graph.hasEdge(1, 2));

        // Test getNumberOfVertices
        System.out.println("Vertices(4) = " + graph.getNumberOfVertices());

        // Test getNumberOfEdges
        System.out.println("Edges(3) = " + graph.getNumberOfEdges());

        // Test getBreadthFirstTraversal
        System.out.println(graph.getBreadthFirstTraversal(1));

        // Test getDepthFirstTraversal
        System.out.println(graph.getDepthFirstTraversal(1));

        // Tests clear
        graph.clear();

        // Test isEmpty
        System.out.println("Is Empty(true) = " + graph.isEmpty());

    }

}
