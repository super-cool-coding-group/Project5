package src.QueueADT;

/**
 * An interface to implement methods related to Queues.

 * @author George Matta
 * @author Pierlorenzo Peruzzo
 * @version 1.1
 */
public interface QueueInterface<T>{

    /**
     * Adds a new entry to the back of this queue.
     *
     * @param entry An object to be added.
     */
    public void enqueue(T entry);

    /**
     * Removes and returns the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     */
    public T dequeue();

    /**
     * Gets the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     */
    public T getFront();

    /**
     * Returns whether or not this queue is empty.
     *
     * @return True if the queue is empty, or false otherwise.
     */
    public boolean isEmpty();

    /**
     * Removes all entries from this queue.
     */
    public void clear();

    /**
     * Gets the number of entries in a queue.
     * @return The number of entries currently in this queue.
     */
    public Integer getNumEntries();

}