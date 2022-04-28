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
        MatrixGraph<Integer> graph = new MatrixGraph<>();
        MatrixGraph<String> graph2 = new MatrixGraph<>(1);

        // Tests addVertex
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        // Tests addEdge
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        System.out.println(graph.previewMatrix());

        // Tests hasEdge
        System.out.println("Has Edge(true) = " + graph.hasEdge(1, 2));

        // Test getNumberOfVertices
        System.out.println("Vertices(4) = " + graph.getNumberOfVertices());

        // Test getNumberOfEdges
        System.out.println("Edges(3) = " + graph.getNumberOfEdges());

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
