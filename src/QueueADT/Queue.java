package src.QueueADT;

import src.ListADT.*;

public class Queue<T> implements QueueInterface<T>{

    private ResizeableList<T> queue;
    private boolean integrityOk = false;
    private int frontIndex; // Index of front entry
    private int backIndex; // Index of back entry
    private static final int DEFAULT_CAPACITY = 3;

    public Queue(){
        this(DEFAULT_CAPACITY);
    }

    public Queue(int initialCapacity){
        ResizeableList<T> tempQueue = new ResizeableList<T>(initialCapacity + 1); 
        queue = tempQueue;
        frontIndex = 1;
        backIndex = initialCapacity;
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
        T front = queue.get(frontIndex); 
        queue.set(frontIndex, null); 
        frontIndex = (frontIndex + 1) % queue.getCapacity();
        return front;
    }

    @Override
    public T getFront() {
        // TODO Auto-generated method stub
        checkIntegrity();
        return queue.get(frontIndex);
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
