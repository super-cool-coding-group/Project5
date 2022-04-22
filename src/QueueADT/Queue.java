package src.QueueADT;

import src.ListADT.*;

/**
 * The Queue class that implements the QueueInterface interface.
 *
 * This class implements all the methods from the interface and adds toString.
 *
 * Under the hood, the Queue class is represented as a ResizeableList.
 *
 * Each method is documented separately.
 *
 * @author George Matta
 * @author Angelica Arteaga
 * @version 1.0
 */
public class Queue<T> implements QueueInterface<T>{

    /**
     * The ResizeableList of the queue.
     */
    private ResizeableList<T> queue;

    /**
     * A boolean that keeps track of whether or not the constructor was properly called.
     */
    private boolean integrityOk = false;

    /**
     * The default/minimum capacity of any Queue. For this implementation, the default capacity is 10.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default Constructor.
     */
    public Queue(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the Queue with a specific capacity.
     *
     * @param initialCapacity the initial capacity for the Queue.
     */
    public Queue(int initialCapacity){
        queue = new ResizeableList<T>(initialCapacity); 
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
            throw new SecurityException("ResizeableList object is corrupt or was not initialized properly.");
        }
    }

    /**
     * Adds an entry to the back of the Queue.
     *
     * @param entry The entry we want to add to the Queue.
     */
    @Override
    public void enqueue(T entry) {
        checkIntegrity();
        queue.add(entry);
    }

    /**
     * Removes and returns the entry at the front of the Queue.
     *
     * @return entry at the front of the Queue.
     */
    @Override
    public T dequeue() {
        checkIntegrity();
        T front = queue.get(1); 
        queue.remove(1); 
        return front;
    }

    /**
     * Gets the entry at the front of the Queue.
     *
     * @return entry at the front of the Queue.
     */
    @Override
    public T getFront() {
        checkIntegrity();
        return queue.get(1);
    }

    /**
     * Checks if the Queue is empty.
     *
     * @return true if the Queue is empty, false if it isn't.
     */
    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return queue.isEmpty();
    }

    /**
     * Clears the Queue of all its entries.
     */
    @Override
    public void clear() {
        checkIntegrity();
        queue.clear();
    }

    /**
     * Represents the Queue as a string.
     * @return The string representation of the Queue.
     */
    @Override
    public String toString(){
        return queue.toString();
    }

}
