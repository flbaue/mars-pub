/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * Florian Bauer
 * fbaue@posteo.de
 * 03.10.2013
 */
package hausaufgabe8.liste;

/**
 * AdListeInterface is an typed interface to represent a custom list.
 *
 * @author Florian Bauer
 */
public interface AdList<T> {

    /**
     * Adds an element to the beginning of the list.
     *
     * @param elem the element to be added
     */
    void cons(T elem);

    /**
     * Removes the head element of the list and returns it.
     *
     * @return the head element
     */
    T head();

    /**
     * Returns the number of elements within the list.
     *
     * @return number of elements
     */
    int length();

    /**
     * Asks if the list is empty.
     *
     * @return true if empty, false if not empty
     */
    boolean isEmpty();

    /**
     * Inserts an element to a specified position with the list. If the list is
     * empty, the element will be add as first element. If the position is
     * higher then the list is long, the element will be added as last element.
     *
     * @param elem     the element to be added
     * @param position the position on which the element shall be added
     */
    void insert(T elem, int position);

    void recInsert(T elem, int position);

    /**
     * @param position
     * @return
     */
    T get(int position);

    /**
     * @param elem
     */
    void add(T elem);

    /**
     * @return
     */
    int size();

    /**
     *
     * @return
     */
    boolean isSorted();

}
