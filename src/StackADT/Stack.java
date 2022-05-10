package src.StackADT;

import src.ListADT.*;

/**
 * An implementation of the StackInterface interface to create Stacks using a resizeable list
 *
 * We have variable for the list itself which is created using a LIFO (Last In First Out) approach
 *
 * The three methods for a stack are Push, Peek, and Pop, but there are also various methods like clear and isEmpty.
 *
 * @author George Matta
 * @version 1.0
 */
public class Stack<T> implements StackInterface<T>{

    /**
     * The raw list we are using to hold the data of the stack
     */
    private ResizeableList<T> stackList;

    /**
     * Keeps track of whether or not the constructor was called properly
     */
    private boolean integrityOk = false;

    /**
     * The default capacity we use for the default constructor
     */
	private static final int DEFAULT_CAPACITY = 10;

    /**
     * A default constructor.
     *
     * Creates a Stack with the default capacity.
     */
    public Stack(){
      this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a graph given a capacity.
     *
     * @param initialCapacity The initial capacity we want the stack to have.
     */
    public Stack(int initialCapacity){
        stackList = new ResizeableList<T>(initialCapacity);
        integrityOk = true;
    }

    /**
     * Checks the integrity of the Stack to make sure the constructor was
     * called.
     *
     * @throws SecurityException if the stack object was corrupted in some way or the
     *                           constructor wasn't run properly.
     */
    private void checkIntegrity(){
        if (!integrityOk) {
            throw new SecurityException("Stack object is corrupt or was not initialized properly.");
        }
    }

    /**
     * Pushes an entry to the end of the stack.
     */
    @Override
    public void push(T entry){
        checkIntegrity();
        stackList.add(entry);
    }

    /**
     * Removes and returns the last element of the stack.
     *
     * @return The item at the end of the stack.
     */
    @Override
    public T pop(){
        checkIntegrity();
        return stackList.remove();
    }

    /**
     * Returns the last element of the stack without removing it.
     *
     * @return The item at the end of the stack.
     */
    @Override
    public T peek(){
        checkIntegrity();
        return stackList.get(stackList.getNumEntries());
    }

    /**
     * Checks whether or not the stack is empty.
     *
     * @return True if the stack is empty, false if it isn't.
     */
    @Override
    public boolean isEmpty(){
        checkIntegrity();
        return stackList.isEmpty();
    }

    /**
     * Clears the stack such that isEmpty would return true.
     */
    @Override
    public void clear(){
        checkIntegrity();
        stackList.clear();
    }

    /**
     * Returns a String representation of the stack
     * @return A String representing the stack
     */
    @Override
    public String toString(){
        return stackList.toString();
    }
}
