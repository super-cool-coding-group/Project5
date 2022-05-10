package src.StackADT;

/**
 * An interface to implement methods related to Stacks.

 * @author George Matta
 * @version 1.0
 */
public interface StackInterface<T>{

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param entry An object to be added to the stack.
     */
    public void push(T entry);

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     */
    public T pop();

    /**
     * Gets the item at the top of the stack.
     *
     * @return The object at the top of the stack.
     */
    public T peek();

    /**
     * Returns whether or not this stack is empty.
     *
     * @return True if the stack is empty.
     */
    public boolean isEmpty();

    /**
     * Removes all items from this stack.
     */
    public void clear();

}
