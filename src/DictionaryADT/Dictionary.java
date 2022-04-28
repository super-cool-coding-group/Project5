package src.DictionaryADT;

import src.ListADT.*;

/**
 *
 */
public class Dictionary<K, V> implements DictionaryInterface<K, V>{

    /**
     * The ResizeableList of the dictionary's entries.
     */
    private ResizeableList<DictionaryEntry<K, V>> dictionary;

    /**
     * A boolean that keeps track of whether or not the constructor was properly called.
     */
    private boolean integrityOk = false;

    /**
     * The default/minimum capacity of any Dictionary. For this implementation, the default capacity is 10.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default Constructor.
     */
    public Dictionary(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize the Dictionary with a specific capacity.
     *
     * @param initialCapacity the initial capacity for the Dictionary.
     */
    public Dictionary(int initialCapacity){
        dictionary = new ResizeableList<DictionaryEntry<K,V>>(initialCapacity);
        integrityOk = true;
    }

    /**
     * Checks the integrity of the dictionary to make sure the constructor was
     * called.
     *
     * @throws SecurityException if the dictionary object was corrupted in some way or the
     *                           constructor wasn't run properly.
     */
    private void checkIntegrity(){
        if (!integrityOk) {
            throw new SecurityException("Dictionary object is corrupt or was not initialized properly.");
        }
    }

    @Override
    public V add(K key, V value){
        // TODO Auto-generated method stub
        // Check the integrity of the dictionary object
        checkIntegrity();

        // Loop through all the entries, making sure the key is not already present.
        // If it is, we replace its value
        for(DictionaryEntry<K, V> entry : dictionary.getArray()){
            if(entry.getKey().equals(key)){
                // Get the old value to return later
                V oldValue = entry.getValue();
                // Reset the value
                entry.setValue(value);
                // Return the old value
                return oldValue;
            }
        }

        // Otherwise, create a new entry
        DictionaryEntry<K, V> newEntry = new DictionaryEntry<>(key, value);
        dictionary.add(newEntry);
        return null;
    }

    @Override
    public V remove(K key){
        // TODO Auto-generated method stub
        // Check the integrity of the dictionary object
        checkIntegrity();

        // Loop through all the entries. If we find the key, remove it.
        for(int i = 0; i < dictionary.getNumEntries(); i++){
            // Initialize a variable for the current entry
            DictionaryEntry<K, V> currEntry = dictionary.get(i);
            // If we find the key
            if(currEntry.getKey().equals(key)){
                // Store the removed value to return, and remove the entry
                V valOfRemoved = currEntry.getValue();
                dictionary.remove(i);
                return valOfRemoved;
            }
        }

        return null;
    }

    @Override
    public V getValue(K key){
        // TODO Auto-generated method stub
        // Check the integrity of the dictionary object
        checkIntegrity();

        // Loop through all the entries. If we find the key, return its value.
        for(int i = 0; i < dictionary.getNumEntries(); i++){
            // Initialize a variable for the current entry
            DictionaryEntry<K, V> currEntry = dictionary.get(i);
            // If we find the key
            if(currEntry.getKey().equals(key)){
                // Store the removed value to return, and remove the entry
                return currEntry.getValue();
            }
        }

        return null;
    }

    @Override
    public boolean contains(K key){
        // TODO Auto-generated method stub
        // Check the integrity of the dictionary object
        checkIntegrity();

        // Loop through all the entries. If we find the key, return true.
        for(int i = 0; i < dictionary.getNumEntries(); i++){
            // If we find the key
            if(dictionary.get(i).getKey().equals(key)){
                // Store the removed value to return, and remove the entry
                return true;
            }
        }

        return false;
    }

    @Override
    public ListInterface<K> getKeys(){
        // TODO Auto-generated method stub
        // Check the integrity of the dictionary object
        checkIntegrity();

        // Initialize a ListInterface of the keys
        ListInterface<K> keys = new ResizeableList<>();

        // Loop through all the entries. Add each key to our keys list
        for(int i = 0; i < dictionary.getNumEntries(); i++){
            keys.add(dictionary.get(i).getKey());
        }

        return keys;
    }

    @Override
    public ListInterface<V> getValues(){
        // TODO Auto-generated method stub
        // Check the integrity of the dictionary object
        checkIntegrity();

        // Initialize a ListInterface of the values
        ListInterface<V> values = new ResizeableList<>();

        // Loop through all the entries. Add each value to our values list
        for(int i = 0; i < dictionary.getNumEntries(); i++){
            values.add(dictionary.get(i).getValue());
        }

        return values;
    }

    @Override
    public boolean isEmpty(){
        // TODO Auto-generated method stub
        checkIntegrity();
        return dictionary.isEmpty();
    }

    @Override
    public int getSize(){
        // TODO Auto-generated method stub
        checkIntegrity();
        return dictionary.getNumEntries();
    }

    @Override
    public void clear(){
        // TODO Auto-generated method stub
        checkIntegrity();
        dictionary.clear();
    }

}
