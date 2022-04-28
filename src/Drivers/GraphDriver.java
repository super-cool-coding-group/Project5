package src.Drivers;

import src.GraphADT.*;

/**
 * The GraphDriver class that tests basic functionality of the
 * Graph class.
 *
 * The class only has one method: a main method.
 *
 * @author George Matta
 * @author Angelica Arteaga
 * @version 1.0
 */
public class GraphDriver {

    public static void main(String[] args){
        // Tests graph constructors
        MatrixGraph<Character> graph = new MatrixGraph<Character>();
        MatrixGraph<Character> graph2 = new MatrixGraph<>(1);

        // Tests addVertex
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');

        // Tests addEdge
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('D', 'A');


        // Tests hasEdge
        System.out.println("Has Edge(true) = " + graph.hasEdge('A', 'B'));
        System.out.println("Has Edge(true) = " + graph.hasEdge('B', 'C'));

        // Test getNumberOfVertices
        System.out.println("Vertices(4) = " + graph.getNumberOfVertices());

        // Test getNumberOfEdges
        System.out.println("Edges(4) = " + graph.getNumberOfEdges());

        // Test getBreadthFirstTraversal
        System.out.println(graph.getBreadthFirstTraversal('A'));

        // Test getDepthFirstTraversal
        System.out.println(graph.getDepthFirstTraversal('A'));

        // Tests clear
        graph.clear();

        // Test isEmpty
        System.out.println("Is Empty(true) = " + graph.isEmpty());

    }

}
