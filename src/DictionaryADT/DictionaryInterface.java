package src.DictionaryADT;

import src.ListADT.ListInterface;

/**
 * An interface to implement methods related to Dictionaries.

 * @author George Matta
 * @version 1.0
 */
public interface DictionaryInterface<K, V>{

    /**
     * Adds a new entry to this dictionary.
     * If the given key already exists in the dictionary, we replace its value.
     *
     * @param key An object search key of the new entry.
     * @param value An object associated with the search key.
     * @return The value associated with the key before it was replaced, or null if the key is distinct.
     */
    public V add(K key, V value);

    /**
     * Removes a specific entry from this dictionary given a key.
     *
     * @param key The key of the value to remove
     * @return The value associated with the key, or null if the key is not present.
     */
    public V remove(K key);

    /**
     * Gets the value associated with the key in this dictionary.
     *
     * @param key The key of the value to return.
     * @return The value associated with the key, or null if the key is not present.
     */
    public V getValue(K key);

    /**
     * Checks whether a key is in the dictionary
     *
     * @param key The key we are finding in this dictionary
     * @return True if the key is found in the dictionary.
     */
    public boolean contains(K key);

    /**
     * Returns a ListInterface object of all the keys in the dictionary.
     *
     * @return A ListInterface object of all the keys in the dictionary.
     */
    public ListInterface<K> getKeys();

    /**
     * Returns a ListInterface object of all the values in the dictionary.
     *
     * @return A ListInterface object of all the values in the dictionary.
     */
    public ListInterface<V> getValues();

    /**
     * Checks whether or not this dictionary is empty.
     *
     * @return True if the dictionary is empty.
     */
    public boolean isEmpty();

    /**
     * Gets the size of this dictionary.
     * @return The number of entries (key-value pairs) currently in the dictionary.
     */
    public int getSize();

    /**
     * Removes all entries from this dictionary.
     */
    public void clear();
}