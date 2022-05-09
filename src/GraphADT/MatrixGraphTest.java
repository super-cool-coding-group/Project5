package src.GraphADT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatrixGraphTest{
    MatrixGraph<Character> graph = new MatrixGraph<>();
    MatrixGraph<Character> graph2 = new MatrixGraph<>(1);

    /** 
    @Test
    public void testMatrixGraphConstructor1(){

    }

    @Test
    public void testMatrixGraphConstructor2(){

    }*/

    @Test
    public void testAddVertex(){
        graph.addVertex('A');
        graph.addVertex('B');
    }

    @Test
    public void testAddEdge(){
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addEdge('A', 'B');
        assertTrue(graph.hasEdge('A', 'B'));
    }

    @Test
    public void testHasEdge(){
        assertFalse(graph.hasEdge('A', 'B'));
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addEdge('A', 'B');
        assertTrue(graph.hasEdge('A', 'B'));
    }

    @Test
    public void testIsEmpty(){
        assertTrue(graph.isEmpty());
        graph.addVertex('A');
        assertFalse(graph.isEmpty());
    }

    @Test
    public void testGetNumberOfVertices(){
        assertEquals(0, graph.getNumberOfVertices());
        graph.addVertex('A');
        graph.addVertex('B');
        assertEquals(2, graph.getNumberOfVertices());
    }

    @Test
    public void testGetNumberOfEdges(){
        assertEquals(0, graph.getNumberOfEdges());
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        assertEquals(2, graph.getNumberOfEdges());
    }

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

    @Test
    public void testGetBreadthFirstTraversal(){
        graph.getBreadthFirstTraversal('A');

    }

    @Test
    public void testGetDepthFirstTraversal(){
        graph.getDepthFirstTraversal('A');

    }

    @Test
    public void testPreviewMatrix(){
        graph.previewMatrix();

    }
    
}
