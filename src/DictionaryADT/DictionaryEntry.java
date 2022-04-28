package src.DictionaryADT;

/**
 * The DictionaryEntry class that defines a singular entry in a dictionary.
 *
 * @author George Matta
 * @version 1.0
 */
public class DictionaryEntry<K, V> {

    /**
     * The key of the entry.
     */
    private K key;

    /**
     * The value associated with the key.
     */
    private V value;

    /**
     * Creates a DictionaryEntry given a key-value pair
     *
     * @param key The key of the entry
     * @param value The value associated with the entry
     */
    protected DictionaryEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    /**
     * Retrieves the key of this entry.
     *
     * @return The key of this entry.
     */
    protected K getKey(){
        return key;
    }

    /**
     * Retrieves the value of this entry.
     *
     * @return The value of this entry.
     */
    protected V getValue(){
        return value;
    }

    /**
     * Sets the value of this entry
     */
    protected void setValue(V value){
        this.value = value;
    }

}
