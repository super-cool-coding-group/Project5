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
        MatrixGraph<Character> graph = new MatrixGraph<>();
        MatrixGraph<String> graph2 = new MatrixGraph<>(1);

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


        System.out.println(graph.previewMatrix());

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


        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        graph2.addVertex("F");
        graph2.addVertex("G");
        graph2.addVertex("H");
        graph2.addVertex("I");

        graph2.addEdge("A", "B");
        graph2.addEdge("A", "D");
        graph2.addEdge("A", "E");
        graph2.addEdge("B", "E");
        graph2.addEdge("D", "G");
        graph2.addEdge("E", "F");
        graph2.addEdge("E", "H");
        graph2.addEdge("G", "H");
        graph2.addEdge("F", "C");
        graph2.addEdge("F", "H");
        graph2.addEdge("H", "I");
        graph2.addEdge("C", "B");
        graph2.addEdge("I", "F");

        System.out.println(graph2.previewMatrix());
        // Test getBreadthFirstTraversal
        System.out.println(graph2.getBreadthFirstTraversal("A"));

        // Test getDepthFirstTraversal
        System.out.println(graph2.getDepthFirstTraversal("A"));


    }

}
