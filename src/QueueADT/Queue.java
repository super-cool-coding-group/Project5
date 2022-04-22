package src.QueueADT;

import src.ListADT.*;

public class Queue<T> implements QueueInterface<T>{

    private ResizeableList<T> queue;
    private boolean integrityOk = false;
    private static final int DEFAULT_CAPACITY = 3;

    public Queue(){
        this(DEFAULT_CAPACITY);
    }

    public Queue(int initialCapacity){
        queue = new ResizeableList<T>(initialCapacity); 
        integrityOk = true;
    }

    private void checkIntegrity(){
        if (!integrityOk) {
            throw new SecurityException("ResizeableList object is corrupt or was not initialized properly.");
        }
    }

    @Override
    public void enqueue(T entry) {
        // TODO Auto-generated method stub
        checkIntegrity();
        queue.add(entry);
    }

    @Override
    public T dequeue() {
        // TODO Auto-generated method stub
        checkIntegrity();
        T front = queue.get(1); 
        queue.remove(1); 
        return front;
    }

    @Override
    public T getFront() {
        // TODO Auto-generated method stub
        checkIntegrity();
        return queue.get(1);
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        checkIntegrity();
        return queue.isEmpty();
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        checkIntegrity();
        queue.clear();
    }

    @Override
    public String toString(){
        return queue.toString();
    }

}
