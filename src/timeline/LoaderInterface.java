package timeline;

/**
 * LoaderInterface is used to check if entries is available, load newest entry, load a spesific entry, load previous
 * or next entry of the current loaded entry.
 * It is meant to be used on a set of values where an identifier like a index og a date can identify when an entry was
 * put
 * Any class that implements LoderInterface should handle some sort of list.
 */
public interface LoaderInterface {
    /**
     * Checks if specific field has value
     * Usually uses a field in the domain specific class that will indicate availability
     * @return isAvailable
     */
    boolean hasSpecific();

    /**
     * Checks if next field has value
     * Usually uses a field in the domain specific class that will indicate availability
     * @return isAvailable
     */
    boolean hasNext();

    /**
     * Checks if previous field has value
     * Usually uses a field in the domain specific class that will indicate availability
     * @return isAvailable
     */
    boolean hasPrevious();

    /**
     * Loads the newest entry
     */
    void loadNewestEntry();

    /**
     * Loads a specific entry
     */
    void loadSpecificEntry();

    /**
     * Loads the entry that lies before the current loaded entry
     */
    void loadPreviousEntry();

    /**
     * Loads the entry that lies next to the current loaded entry
     */
    void loadNextEntry();
}
