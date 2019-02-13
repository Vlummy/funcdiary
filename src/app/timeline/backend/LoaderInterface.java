package app.timeline.backend;

/**
 * LoaderInterface is used to check if entries is available, load newest entry, load a spesific entry, load previous
 * or next entry of the current loaded entry.
 * It is meant to be used on a set of values where an identifier like a index og a date can identify when an entry was
 * put
 * Any class that implements LoderInterface should handle some sort of list.
 *
 * T is usually the type of object that is being handled in the list
 * E is usually a type of identifier to identify the objects when loading a specific entry
 *
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public interface LoaderInterface<T, E> {
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
    void loadSpecificEntry(E entry);

    /**
     * Loads the entry that lies before the current loaded entry
     */
    void loadPreviousEntry();

    /**
     * Loads the entry that lies next to the current loaded entry
     */
    void loadNextEntry();

    /**
     * Returns the actual entry that is currently loaded
     * @return
     */
    T getEntry();
}
