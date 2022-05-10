package src.Drivers;

import src.QueueADT.*;

/**
 * The QueueDriver class that tests basic functionality of the
 * Queue class.
 *
 * The class only has one method: a main method.
 *
 * @author George Matta
 * @author Angelica Arteaga
 * @version 1.0
 */
public class QueueDriver {

    /**
     * We just have a main method to run the various Queue-related methods
     *
     * @param args The general args param for any Java main method
     */
    public static void main(String[] args){
        Queue<Integer> testQueue = new Queue<Integer>(1);

        System.out.println(testQueue);

        // Add first entry
        testQueue.enqueue(1);
        System.out.println(testQueue);

        // Add second entry
        testQueue.enqueue(2);
        System.out.println(testQueue);

        // Retrieve front of queue
        System.out.println("Front of Queue: " + testQueue.getFront());

        // Add third entry
        testQueue.enqueue(3);
        System.out.println(testQueue);

        // Remove entry
        System.out.println("Front of Queue: " + testQueue.getFront());
        System.out.println("Removed Entry: " + testQueue.dequeue());
        System.out.println("Front of Queue: " + testQueue.getFront());
        System.out.println(testQueue);

        // Test clear
        testQueue.clear();
        System.out.println(testQueue);

    }

}
