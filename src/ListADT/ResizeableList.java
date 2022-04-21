package src.ListADT;

/**
 * The ResizeableList class that implements the ListInterface interface.
 *
 * This class implements all the methods from the interface while also adding
 * functionality for doubling
 * the capacity of the list whenever we fill the list up.
 *
 * The index of a ResizeableList object starts at 1.
 *
 * Each method is documented separately.
 *
 * @author George Matta
 * @author Pierlorenzo Peruzzo
 * @version 1.3
 */
public class ResizeableList<T> implements ListInterface<T> {

    /**
     * The pure Java Array that we will be populating
     */
    private T[] array;

    /**
     * A field denoting the number of entries in our array.
     *
     * This number can increase (or decrease) by more than 1 if we add an element to
     * an index that is larger than numOfEntries + 1
     * More info. on this can be found in the documentations for the add and remove
     * methods.
     */
    private int numOfEntries;

    /**
     * A boolean denoting whether or not the constructor was called properly.
     */
    private boolean integrityOk = false;

    /**
     * A static final field denoting the maximum capacity of any list.
     */
    private static final int MAX_CAPACITY = 10_000;

    /**
     * A static final field denoting the default capacity of a list.
     *
     * We use this capacity if one is not provided in the constructor.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The default constructor.
     *
     * Creates a ResizeableList with DEFAULT_CAPACITY capacity by delegating to the
     * ResizeableList(int) constructor
     */
    public ResizeableList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * The main constructor of our ResizeableList.
     *
     * Creates a ResizeableList given a capacity.
     *
     * @param capacity The capacity we want the list to have.
     */
    public ResizeableList(int capacity) {
        // Check that the capacity is within bounds
        checkCapacity(capacity);
        // Create a pure generic-type array
        this.array = createArray(capacity);
        // Default the number of entries to 0 (the list has no entries)
        this.numOfEntries = 0;
        // The integrity of the list is now okay
        this.integrityOk = true;
    }

    /**
     * Creates a generic-type array
     *
     * @param capacity The capacity we want the list to have
     * @return The array object of T generics
     */
    @SuppressWarnings("unchecked")
    private T[] createArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    /**
     * Throws an error if the capacity of the list is too large or too small.
     *
     * @throws IllegalArgumentException if the capacity is greater than the maximum
     *                                  or less than 1.
     * @param capacity The capacity we are checking.
     */
    private void checkCapacity(int capacity) {
        // Default the error message and then append either "large" or "small"
        String errorMessage = "Attempted to create a list with a capacity (" + capacity + ") which is too ";
        // If it is too large
        if (capacity > MAX_CAPACITY) {
            errorMessage += "large";
            throw new IllegalArgumentException(errorMessage);
        }
        // If it is too small
        if (capacity < 1) {
            errorMessage += "small";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Continuously doubles the capacity while a provided index is larger than
     * capacity.
     *
     * @param index The index we are checking if it is too large.
     */
    private void ensureCapacity(int index) {
        // While the index is too large, double the capacity.
        while (index >= array.length) {
            doubleCapacity();
        }
    }

    /**
     * Doubles the capacity of the array.
     *
     * This method also checks the new capacity with the checkCapacity(int) method,
     * creates
     * an array using createArray(int), and then copies the entries of the
     * old array to the new one using copyEntries(T[]).
     */
    private void doubleCapacity() {
        int newCapacity = array.length * 2;
        checkCapacity(newCapacity);
        T[] newArray = createArray(newCapacity);
        array = copyEntries(newArray);
    }

    /**
     * Copies all the entries from this array into a new array
     *
     * @param newArray The array to copy the entries into
     * @return The array with the copied entries
     */
    private T[] copyEntries(T[] newArray) {
        // Loop through each item in the old array
        for (int i = 0; i < numOfEntries; i++) {
            // Set the element in the new array
            newArray[i] = array[i];
        }
        // Return the new array
        return newArray;
    }

    /**
     * Checks the integrity of the ResizeableList to make sure the constructor was
     * called.
     *
     * @throws SecurityException if the list object was corrupted in some way or the
     *                           constructor wasn't run properly.
     */
    private void checkIntegrity() {
        if (!integrityOk) {
            throw new SecurityException("ResizeableList object is corrupt or was not initialized properly.");
        }
    }

    /**
     * Checks if a provided index is within the range of the list [1-numOfEntries]
     *
     * @throws IndexOutOfBoundsException if the index is not in the range
     *                                   [1-numOfEntries]
     * @param index The index we are checking.
     */
    private void checkIndexInRange(int index) {
        if (index < 1 || index > numOfEntries) {
            throw new IndexOutOfBoundsException("The index provided (" +
                    index + ") does not fall within " +
                    "the range of the array [1-" +
                    numOfEntries + "].");
        }
    }

    /**
     * Adds an entry to the list given an index such that arr[index] == entry.
     *
     * Since we are in a list, we want to maintain the order of the elements, so we
     * employ the moveEntriesForward(int) method.
     * We call this method to create a space for us to fit the entry into.
     *
     * We also need to maintain the numOfEntries field, so we increase it (by at
     * least 1).
     * In special cases where the index is larger than the numOfEntries by a factor
     * of more than 1, we increase
     * the number of entries more.
     * The formula to calculate how much we are increasing the numOfEntries field by
     * is index - numOfEntries.
     * So whenever we add an element, we increase the numOfEntries by a factor of
     * Math.max(1, index-numOfEntries).
     *
     * @param index The index we want to add the entry to.
     * @param entry The element we want to add.
     */
    @Override
    public void add(int index, T entry) {
        // Make all our checks and ensure that we have the required capacity.
        checkIntegrity();
        ensureCapacity(index);

        // If we are adding to anywhere other than the end of the list, we need to make
        // room for the element
        if (index <= numOfEntries) {
            moveEntriesForward(index);
        }

        // Set the entry to the empty spot we made
        array[index - 1] = entry;

        // Calculate the new number of entries
        int factor = Math.max(1, index - numOfEntries);
        numOfEntries += factor;
    }

    /**
     * Adds an entry to the end of the list.
     *
     * @param entry The entry we want to add to the end of the list.
     */
    @Override
    public void add(T entry) {
        // Add the element to the end of the list
        add(numOfEntries + 1, entry);
    }

    /**
     * Removes an element from the list given an index.
     *
     * Similarly to the add method, we use the moveEntriesBack(int) method to
     * maintain the order of the list.
     *
     * Further, we decrease the number of entries by 1, and then continue to
     * decrement the number of entries for
     * each trailing "null" entry in our list.
     *
     * For example:
     * We have a list: [1, 2, null, null, 3]
     * We remove the element "3": [1, 2, null, null]
     * We remove the trailing "null"s: [1, 2]
     *
     * @param index The index of the element we want to remove.
     * @return The removed element.
     */
    @Override
    public T remove(int index) {
        // Sanitize input
        checkIntegrity();
        checkIndexInRange(index);

        // Store the element we remove
        T removed = moveEntriesBack(index);

        // We are definitely removing one entry
        numOfEntries--;
        // We also remove an entry for every trailing null
        for (; numOfEntries != 0 && array[numOfEntries - 1] == null; numOfEntries--)
            ;

        // Return the element we removed
        return removed;
    }

    /**
     * Removes the last element from the list.
     *
     * @return The last element from the list (the removed element).
     */
    @Override
    public T remove() {
        // Check the integrity of the object
        checkIntegrity();
        // Remove the item at the last index
        return remove(numOfEntries);
    }

    /**
     * Removes an entry from a list by moving all proceeding elements back an index.
     *
     * This method serves to actually remove the element from the list, and it
     * maintains the order of the elements.
     *
     * @param index The index of the element we want to remove
     * @return The removed element
     */
    private T moveEntriesBack(int index) {
        // Store the removed element
        T removed = array[index - 1];
        // Start at the index and continuously move the next element back once.
        for (int i = index; i <= numOfEntries; i++) {
            array[i - 1] = array[i];
        }
        // Return the removed element
        return removed;
    }

    /**
     * Moves all entries from the index forward once to create a null entry at index
     *
     * This is basically the opposite of moveEntriesBack(int)
     *
     * @param index The index of the element we want to make null
     */
    private void moveEntriesForward(int index) {
        // Make sure we have enough room
        ensureCapacity(numOfEntries + 1);
        // Start at 1 less than the end of the list and continuously set the next
        // element to the current element
        for (int i = numOfEntries; i >= index - 1; i--) {
            array[i + 1] = array[i];
        }
    }

    /**
     * Sets an element at a specified index to the entry.
     *
     * This method does not change the number of entries, it simply replaces an
     * element with another element.
     *
     * @param index The index of the element we want to replace.
     * @param entry The entry we want to replace the element with.
     * @return The element we 'removed' from the list
     */
    @Override
    public T set(int index, T entry) {
        // Sanitize input
        checkIntegrity();
        checkIndexInRange(index);

        // Store the element we're removing
        T removed = array[index - 1];
        // Set the element to the entry we want
        array[index - 1] = entry;

        // Return the removed element
        return removed;
    }

    /**
     * Retrieves an element from the list given its index.
     *
     * @param index The index of the element we want to get.
     * @return The element we got from the list.
     */
    @Override
    public T get(int index) {
        // Sanitize input
        checkIntegrity();
        checkIndexInRange(index);

        // Return the element
        return array[index - 1];
    }

    /**
     * Swaps two elements in the list given two indicies.
     *
     * @param firstIndex  The index of the first element we're swapping
     * @param secondIndex The index of the second element we're swapping
     */
    @Override
    public void swap(int firstIndex, int secondIndex) {
        // Sanitize input
        checkIntegrity();
        checkIndexInRange(firstIndex);
        checkIndexInRange(secondIndex);

        // Swap the two elements
        T tempEntry = array[firstIndex - 1];
        array[firstIndex - 1] = array[secondIndex - 1];
        array[secondIndex - 1] = tempEntry;
    }

    /**
     * Counts how many times a specified entry occurs in the list.
     *
     * @param entry The entry we are scanning for.
     * @return The number of times the element is present.
     */
    @Override
    public int getFreqOf(T entry) {
        // Check the integrity of the object
        checkIntegrity();

        // Initialize the counter variable
        int count = 0;
        // Loop through the array
        for (int i = 0; i < numOfEntries; i++) {
            // If we are at a null entry, skip it
            if (array[i] == null) {
                continue;
            }
            // If we found the element
            if (array[i].equals(entry)) {
                // Increase the count
                count++;
            }
        }

        // Return the final count
        return count;
    }

    /**
     * Checks if an element is present in the list.
     *
     * @param entry The entry we are scanning for.
     * @return true if the element is in the list, false if it isn't.
     */
    @Override
    public boolean contains(T entry) {
        // Check the integrity of the object
        checkIntegrity();
        // If the entry is in the array, return true
        return getIndexOf(entry) > -1;
    }

    /**
     * Gets the index of a specified entry.
     *
     * @param entry The entry we are scanning for.
     * @return The index of the entry.
     */
    @Override
    public int getIndexOf(T entry) {

        // Loop through the array
        for (int i = 0; i < numOfEntries; i++) {
            // If we find the element
            if (array[i].equals(entry)) {
                // Return the indes
                return i;
            }
        }

        // Otherwise, return -1
        return -1;
    }

    /**
     * Gets the capacity of this list.
     * This is just this.array.length.
     *
     * @return The capacity of the list.
     */
    @Override
    public int getCapacity() {
        // Check the integrity of the object
        checkIntegrity();
        // Return the capacity
        return array.length;
    }

    /**
     * Gets the number of entries of the list.
     * This is just a simple getter which returns the numOfEntries field.
     *
     * @return The number of entries of the list.
     */
    @Override
    public int getNumEntries() {
        // Check the integrity of the object
        checkIntegrity();
        // Return the number of entries
        return numOfEntries;
    }

    /**
     * Checks if the list is empty.
     * We do this by checking if the number of entries is 0.
     *
     * @return true if the list is empty, false if it isn't.
     */
    @Override
    public boolean isEmpty() {
        // Check the integrity of the object
        checkIntegrity();
        // Return whether or not the list is empty
        return numOfEntries == 0;
    }

    /**
     * Checks if the list is full
     * We do this by checking if the number of entries is greater than or equal to
     * one less than the capacity.
     * (In Java, we can't add an element to index array.length using the native
     * arr[ind])
     *
     * @return true if the list is full, false if it isn't
     */
    @Override
    public boolean isFull() {
        // Check the integrity of the object
        checkIntegrity();
        // Return whether or not the list is full
        return numOfEntries >= array.length - 1;
    }

    /**
     * Clears a list of all its entries.
     * We do this by continuously removing the last entry (by calling remove())
     * until the list is empty (isEmpty()).
     */
    @Override
    public void clear() {
        // Check the integrity of the object
        checkIntegrity();
        // While the list isn't empty
        while (!isEmpty()) {
            // Remove the last element
            remove();
        }
    }

    /**
     * Retrieves the basic generic-type array of the list.
     * This is a simple getter method that returns this.array
     *
     * @return A generic-type array of the elements in the list (this.array)
     */
    @Override
    public T[] getArray() {
        checkIntegrity();
        return array;
    }

    /**
     * Returns a readable String representation of a ResizeableList object.
     *
     * The String follows the following format:
     * [a, b, c, ..., d] Capacity: capacity NumEntries: numOfEntries
     *
     * @return The String representation of the ResizeableList object
     */
    @Override
    public String toString() {
        checkIntegrity();

        String output = "[";
        for (int i = 0; i < numOfEntries - 1; i++) {
            output += array[i] + ", ";
        }

        if (numOfEntries != 0) {
            output += array[numOfEntries - 1];
        }
        output += "]";

        output += ", Capacity: " + array.length;
        output += ", NumEntries: " + numOfEntries;

        return output;
    }

    /**
     * Gets the total number of entries in this ResizeableList
     * @return The numOfEntries instance field.
     */
    @Override
    public int count() {
        return numOfEntries;
    }

    /**
     * Returns a preview of the first 10 items of the list
     * @return The string preview of the list
     */
    public String preview(){
        checkIntegrity();

        String output = "[";
        for (int i = 0; i < numOfEntries - 1 && i < 10; i++) {
            output += array[i] + ", ";
        }

        output += "...]";

        return output;
    }
}
