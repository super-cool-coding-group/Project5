package src.UnitTests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import src.QueueADT.Queue;

/**
 * This is the testing class we use to test the methods we implemented for Queue.
 *
 * We use the JUnit testing framework to run these tests.
 *
 * @author Angelica Arteaga
 */
public class QueueTest {

    Queue<Integer> queue = new Queue<Integer>(1);
    Queue<Integer> queue2 = new Queue<Integer>();
    Queue<Integer> queue3 = new Queue<Integer>(22);

    @Test
    public void testQueue(){
        String expected = "[], Capacity: 10, NumEntries: 0";
        assertEquals(expected, queue2.toString());
    }

    @Test
    public void testQueue2(){
        String expected = "[], Capacity: 22, NumEntries: 0";
        assertEquals(expected, queue3.toString());
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(1);
        assertEquals(1, queue.getNumEntries());
        queue.enqueue(2);
        assertEquals(2, queue.getNumEntries());
        queue.enqueue(2);
        assertEquals(3, queue.getNumEntries());
        assertEquals(1, queue.getFront());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());  
    }

    @Test
    public void testGetFront() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.getFront()); 
        queue.dequeue();
        assertEquals(2, queue.getFront()); 
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testClear() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.getNumEntries());
        queue.clear();
        assertEquals(0, queue.getNumEntries());
    }

    @Test
    public void testToString(){
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        String expected = "[1, 2, 3], Capacity: 4, NumEntries: 3";
        assertEquals(expected, queue.toString());
    }

    @Test
    public void testGetNumEntries(){
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.getNumEntries());
        queue.enqueue(3);
        assertEquals(3, queue.getNumEntries());
    }
    
}
