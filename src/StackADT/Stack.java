package src.StackADT;

import src.ListADT.*;

public class Stack<T> implements StackInterface<T>{

    private ResizeableList<T> stackList;

    private boolean integrityOk = false;

	private static final int DEFAULT_CAPACITY = 10;

    public Stack(){
      this(DEFAULT_CAPACITY);
    }

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
            throw new SecurityException("ResizeableList object is corrupt or was not initialized properly.");
        }
    }

    @Override
    public void push(T entry){
        // TODO Auto-generated method stub
        checkIntegrity();
        stackList.add(entry);
    }

    @Override
    public T pop(){
        // TODO Auto-generated method stub
        checkIntegrity();
        return stackList.remove();
    }

    @Override
    public T peek(){
        // TODO Auto-generated method stub
        checkIntegrity();
        return stackList.get(stackList.getNumEntries());
    }

    @Override
    public boolean isEmpty(){
        // TODO Auto-generated method stub
        checkIntegrity();
        return stackList.isEmpty();
    }

    @Override
    public void clear(){
        // TODO Auto-generated method stub
        checkIntegrity();
        stackList.clear();
    }

    @Override
    public String toString(){
        return stackList.toString();
    }
}
