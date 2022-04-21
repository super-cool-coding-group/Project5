package src.ListADT;

/**
 * The ListInterface interface which we later implement to create a ResizeableList.
 *
 * This interface contains definitions for various methods that are integral for
 * the existence
 * of any list (resizeable or not).
 *
 * These methods allow for functionalities such as adding, removing, swapping,
 * setting/getting elements, counting elements, clearing,
 * and many checkers (such as contains, isEmpty, isFull).
 *
 * Each of these methods is documented separately.
 *
 * @author George Matta
 * @author Pierlorenzo Peruzzo
 * @version 1.1
 */
public interface ListInterface<T> {

    /**
     * Adds the entry to the specified index (such that arr[index] == entry).
     *
     * @param index The index we want to add the entry to
     * @param entry The entry we want to add to the list
     */
    public void add(int index, T entry);

    /**
     * Adds an entry to a specified index.
     *
     * This would delegate to the add(int, T) method, adding the entry at a
     * specified and unchanging index.
     *
     * In most cases, this index would be the end of the list, but it can also be
     * the front of the list (or any index for that matter)
     *
     * @param entry The entry we want to add to the list
     */
    public void add(T entry);

    /**
     * Removes an element from the list given an index.
     *
     * @param index The index we want to remove from the list.
     * @return A generic-type object of the item removed from the list
     */
    public T remove(int index);

    /**
     * Removes an element from the list from a specified index.
     *
     * This delegates to the remove(int, T) method, removing an entry from a
     * specified index.
     *
     * In most cases, this index would be the end of the list, but it can also be
     * the front of the list (or any index for that matter)
     *
     * @return A generic-type object of the item removed from the list
     */
    public T remove();

    /**
     * Sets an element at an index to be the entry.
     *
     * Unlike add, this method does not increase the total number of entries of the
     * list. It only replaces an element.
     *
     * @param index The index of the element we want to replace.
     * @param entry The entry we want to replace the element with.
     * @return A generic-type object of the item removed from the list
     */
    public T set(int index, T entry);

    /**
     * Gets an element from the list given an index.
     *
     * @param index The index of the element we want to get.
     * @return The element at the index.
     */
    public T get(int index);

    /**
     * Swaps two elements at two indices.
     *
     * @param firstIndex  The index of the first element to be swapped.
     * @param secondIndex The index of the second element to be swapped.
     */
    public void swap(int firstIndex, int secondIndex);

    /**
     * Counts the occurences of a specified entry.
     *
     * @param entry The entry we want to count the occurences of
     * @return The frequency of the entry
     */
    public int getFreqOf(T entry);

    /**
     * Checks if an element is in the list.
     *
     * @param entry The entry we want to check the existence of.
     * @return true if the element is in the list, false if it isn't.
     */
    public boolean contains(T entry);

    /**
     * Finds the first occurence of an element in the list and returns its index.
     *
     * @param entry The entry we are looking for in the list
     * @return The index of the specified entry. -1 if it is not in the list.
     */
    public int getIndexOf(T entry);

    /**
     * Gets the capacity of the list. This is how many elements can be added to the
     * list (without any resizing).
     *
     * @return The capacity of the list.
     */
    public int getCapacity();

    /**
     * Gets the number of entries of a list. This is how many elements have been
     * added to the list.
     *
     * @return The number of entries in the list.
     */
    public int getNumEntries();

    /**
     * Checks if there are no entries in the list.
     *
     * @return true if the list is empty, false if it isn't.
     */
    public boolean isEmpty();

    /**
     * Checks if we have added 'capacity' number of elements
     *
     * @return true if the number of entries is the same as the capacity, false if
     *         isn't.
     */
    public boolean isFull();

    /**
     * Clears a list of all its elements.
     */
    public void clear();

    /**
     * Returns the pure Java Array of the ListInterface object
     *
     * @return An array of generic-type objects
     */
    public T[] getArray();

    /**
     * Gets the number of the entries in the List
     *
     * @return The number of entries.
     */
    public int count();

}
