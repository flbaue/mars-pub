package hausaufgabe8.liste;

import counter.StepCounter;

/**
 * AdListeElement is a private inner class to represent an linked element
 * within the {@link hausaufgabe8.liste.DefaultAdList}
 *
 * @author florianbauer
 */
public class ListElement<E extends Comparable<E>> implements Comparable<ListElement<E>> {

    private static StepCounter counter = new StepCounter();
    private E data;
    private ListElement<E> next;

    /**
     * Constructs an list element
     *
     * @param data the containing typed data
     * @param next the link to the following element. Can be null.
     */
    public ListElement(E data, ListElement<E> next) {
        setData(data);
        setNext(next);
    }

    public static int getSteps() {
        return counter.getSteps();
    }

    public static void resetCounter() {
        counter.reset();
    }

    /**
     * Returns the containing data.
     *
     * @return the typed data object
     */
    public E getData() {
        return data;
    }

    /**
     * Sets a new data object.
     *
     * @param data the object
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns the reference to the following linked object. Can be null.
     *
     * @return the following liked object
     */
    public ListElement<E> getNext() {
        counter.step();
        return next;
    }

    /**
     * Sets the reference to the following linked element.
     *
     * @param next the following element
     */
    public void setNext(ListElement<E> next) {
        this.next = next;
    }

    /**
     * Returns the reference to the following linked object. Can be null.
     *
     * @return the following liked object
     */
    public ListElement<E> getNextWithoutCounting() {
        return next;
    }

    /**
     * Asks whether an link to a following element exists.
     *
     * @return true if a following element is linked, false if not
     */
    public boolean hasNext() {
        return getNext() != null;
    }

    @Override
    public int compareTo(ListElement<E> o) {
        return this.getData().compareTo(o.getData());
    }
}
