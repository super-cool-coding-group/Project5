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
        MatrixGraph<String> graph3 = new MatrixGraph<>();

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

        // // Test getBreadthFirstTraversal
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


        graph3.addVertex("A");
        graph3.addVertex("B");
        graph3.addVertex("C");
        graph3.addVertex("D");
        graph3.addVertex("E");
        graph3.addVertex("F");
        graph3.addVertex("G");
        graph3.addVertex("H");
        graph3.addVertex("I");

        graph3.addEdge("A", "E");
        graph3.addEdge("B", "A");
        graph3.addEdge("B", "C");
        graph3.addEdge("B", "E");
        graph3.addEdge("E", "H");
        graph3.addEdge("F", "B");
        graph3.addEdge("F", "C");
        graph3.addEdge("G", "H");
        graph3.addEdge("H", "D");
        graph3.addEdge("H", "F");
        graph3.addEdge("H", "I");
        graph3.addEdge("I", "F");

        System.out.println(graph3.getBreadthFirstTraversal("G"));
        System.out.println(graph3.getDepthFirstTraversal("G"));

        ListGraph<String> graph4 = new ListGraph<>();
        graph4.addVertex("A");
        graph4.addVertex("B");
        graph4.addVertex("C");
        graph4.addVertex("D");
        graph4.addVertex("E");
        graph4.addVertex("F");
        graph4.addVertex("G");
        graph4.addVertex("H");
        graph4.addVertex("I");

        graph4.addEdge("A", "B");
        graph4.addEdge("A", "D");
        graph4.addEdge("A", "E");
        graph4.addEdge("B", "E");
        graph4.addEdge("D", "G");
        graph4.addEdge("E", "F");
        graph4.addEdge("E", "H");
        graph4.addEdge("G", "H");
        graph4.addEdge("F", "C");
        graph4.addEdge("F", "H");
        graph4.addEdge("H", "I");
        graph4.addEdge("C", "B");
        graph4.addEdge("I", "F");
        
        System.out.println(graph4.previewList());

        // Test getBreadthFirstTraversal
        System.out.println(graph4.getBreadthFirstTraversal("A"));

        // Test getDepthFirstTraversal
        System.out.println(graph4.getDepthFirstTraversal("A"));
    }

}
