package src.UnitTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import src.GraphADT.ListGraph;

/**
 * This is the testing class we use to test the methods we implemented for List Graph.
 *
 * The methods we are testing are breadth first traversal, depth first traversal, is empty, and clear.
 *
 * We use the JUnit testing framework to run these tests.
 *
 * This is basically a copy of the MatrixGraphTest file but with the ListGraph rather than the MatrixGraph.
 * Because of OOP, the actual calling of the methods is abstracted away and everything works as it should.
 *
 * @author Angelica Arteaga
 * @author George Matta
 * @version 1.0
 */
public class ListGraphTest{

    /**
     * A graph created using the default constructor.
     */
    ListGraph<Character> graph = new ListGraph<>();

    /**
     * A graph created using the regular constructor.
     */
    ListGraph<Character> graph2 = new ListGraph<>(1);

    /**
     * Tests adding a vertex into a graph
     */
    @Test
    public void testAddVertex(){
        graph.addVertex('A');
        graph.getBreadthFirstTraversal('A');
        String expected = "[A], Capacity: 10, NumEntries: 1";
        String actual = graph.getBreadthFirstTraversal('A').toString();
        assertEquals(expected, actual);
    }

    /**
     * Tests adding edges between vertices in a graph
     */
    @Test
    public void testAddEdge(){
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addEdge('A', 'B');
        assertTrue(graph.hasEdge('A', 'B'));
    }

    /**
     * Tests if hasEdge returns the appropariate boolean of whether or not an edge exists
     * between two vertices
     */
    @Test
    public void testHasEdge(){
        assertFalse(graph.hasEdge('A', 'B'));
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addEdge('A', 'B');
        assertTrue(graph.hasEdge('A', 'B'));
        assertFalse(graph.hasEdge('A', 'C'));
    }

    /**
     * Tests whether or not isEmpty returns true for empty graphs and false for non-empty ones.
     */
    @Test
    public void testIsEmpty(){
        assertTrue(graph.isEmpty());
        graph.addVertex('A');
        assertFalse(graph.isEmpty());
    }

    /**
     * Tests if we are keeping track of the number of vertices properly.
     */
    @Test
    public void testGetNumberOfVertices(){
        assertEquals(0, graph.getNumberOfVertices());
        graph.addVertex('A');
        graph.addVertex('B');
        assertEquals(2, graph.getNumberOfVertices());
    }

    /**
     * Tests if we're keeping track of the number of edges properly.
     */
    @Test
    public void testGetNumberOfEdges(){
        assertEquals(0, graph.getNumberOfEdges());
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'A');
        assertEquals(2, graph.getNumberOfEdges());
    }

    /**
     * Tests if we can clear graphs properly.
     */
    @Test
    public void testClear(){
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'A');
        assertEquals(2, graph.getNumberOfVertices());
        assertEquals(2, graph.getNumberOfEdges());
        graph.clear();
        assertEquals(0, graph.getNumberOfVertices());
        assertEquals(0, graph.getNumberOfEdges());
    }

    /**
     * Tests the BFS traversal of graphs.
     */
    @Test
    public void testGetBreadthFirstTraversal(){
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');
        graph.addVertex('I');

        graph.addEdge('A', 'B');
        graph.addEdge('A', 'D');
        graph.addEdge('A', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('D', 'G');
        graph.addEdge('E', 'F');
        graph.addEdge('E', 'H');
        graph.addEdge('G', 'H');
        graph.addEdge('F', 'C');
        graph.addEdge('F', 'H');
        graph.addEdge('H', 'I');
        graph.addEdge('C', 'B');
        graph.addEdge('I', 'F');

        graph.getBreadthFirstTraversal('A');
        String expected = "[A, B, D, E, G, F, H, C, I], Capacity: 10, NumEntries: 9";
        String actual = graph.getBreadthFirstTraversal('A').toString();
        assertEquals(expected, actual);
    }

    /**
     * Tests the DFS traversal of graphs
     */
    @Test
    public void testGetDepthFirstTraversal(){
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');
        graph.addVertex('H');
        graph.addVertex('I');

        graph.addEdge('A', 'B');
        graph.addEdge('A', 'D');
        graph.addEdge('A', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('D', 'G');
        graph.addEdge('E', 'F');
        graph.addEdge('E', 'H');
        graph.addEdge('G', 'H');
        graph.addEdge('F', 'C');
        graph.addEdge('F', 'H');
        graph.addEdge('H', 'I');
        graph.addEdge('C', 'B');
        graph.addEdge('I', 'F');

        graph.getDepthFirstTraversal('A');
        String expected = "[A, B, E, F, C, H, I, D, G], Capacity: 10, NumEntries: 9";
        String actual = graph.getDepthFirstTraversal('A').toString();
        assertEquals(expected, actual);
    }

}
