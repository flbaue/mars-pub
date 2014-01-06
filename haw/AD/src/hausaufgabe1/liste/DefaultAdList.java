/*
 * Florian Bauer
 * flbaue@posteo.de
 * 2013
 */

/**
 * FLorian Bauer
 * fbaue@posteo.de
 * 03.10.2013
 */
package hausaufgabe1.liste;

import counter.StepCounter;

/**
 * AdListeImpl implements the {@link AdList} as a simple linked list.
 *
 * @param <T>
 * @author Florian Bauer
 */
public class DefaultAdList<T> implements AdList<T> {

    private AdListElement<T> head;
    private StepCounter counter;
    private int size = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void cons(T elem) {
        size++;
        head = new AdListElement<T>(elem, head);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T head() {
        size--;
        if (head == null) {
            return null;
        }
        T headData = head.getData();
        head = head.getNext();
        return headData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int length() {

        if (head == null) {
            return 0;
        }

        AdListElement<T> currentElement = head;
        int numberOfElements = 1;
        while (currentElement.hasNext()) {
            numberOfElements++;
            currentElement = currentElement.getNext();
        }
        return numberOfElements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(T elem, int position) {

        size++;
        AdListElement<T> newElement = new AdListElement<T>(elem, null);
        if (head == null) {
            head = newElement;
        } else if (position <= 0) {
            newElement.setNext(head);
            head = newElement;
        } else if (position >= size()) {
            AdListElement<T> currentElement = head;
            while (currentElement.hasNext()) {
                currentElement = currentElement.getNext();
            }
            currentElement.setNext(newElement);
        } else {
            AdListElement<T> currentElement = head.getNext();
            AdListElement<T> previousElement = head;
            int currentPosition = 1;
            while (currentPosition < position) {
                previousElement = currentElement;
                currentElement = currentElement.getNext();
                currentPosition++;
            }
            previousElement.setNext(newElement);
            newElement.setNext(currentElement);
        }
    }

    @Override
    public void recInsert(T elem, int position) {
        size++;
        if (position == 0) {
            AdListElement<T> newElem = new AdListElement<T>(elem, head);
            head = newElem;
        } else {
            AdListElement<T> currentElem = head;
            recInsert(currentElem, elem, position);
        }
    }

    private void recInsert(AdListElement<T> currentElem, T elem, int steps) {
        if (steps == 1) {
            AdListElement<T> prevElem = currentElem;
            AdListElement<T> nextElem = currentElem.getNext();
            AdListElement<T> newElem = new AdListElement<T>(elem, nextElem);
            prevElem.setNext(newElem);
        }
        if (steps > 1) {
            AdListElement<T> newCurrentElem = currentElem.getNext();
            if (newCurrentElem == null) {
                //Wenn die einfügeposition nicht mehr in der liste liegt
                recInsert(currentElem, elem, 1);
            } else {
                recInsert(newCurrentElem, elem, steps - 1);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T get(int position) {
        AdListElement<T> currentElem = head;
        int i = 0;
        while (currentElem.hasNext() && i < position) {
            currentElem = currentElem.next;
            i++;
        }
        if (i == position) {
            return currentElem.data;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void add(T elem) {

        size++;
        AdListElement<T> newElem = new AdListElement<T>(elem, null);

        if (head == null) {
            head = newElem;

        } else {

            AdListElement<T> currentElem = head;

            while (currentElem.hasNext()) {
                currentElem = currentElem.next;
            }

            currentElem.setNext(newElem);
        }
    }

    public StepCounter refCounter() {
        if (counter == null) {
            counter = new StepCounter();
        }
        return counter;
    }

    private void count() {
        if (counter != null) {
            counter.step();
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size() {
        return size;
    }

//    public String toString() {
//        String result = "";
//        AdListElement<T> current = head;
//        if (head == null) {
//            return result;
//        }
//
//        result += "Data:" + current.getData() + " -> ";
//
//        while (current.hasNext()) {
//            current = current.getNext();
//            result += "Data:" + current.getData() + " -> ";
//        }
//
//        return result;
//    }

    /**
     * AdListeElement is a private inner class to represent an linked element
     * within the {@link DefaultAdList}
     *
     * @author florianbauer
     */
    private class AdListElement<E> {
        private E data;
        private AdListElement<E> next;

        /**
         * Constructs an list element
         *
         * @param data the containing typed data
         * @param next the link to the following element. Can be null.
         */
        private AdListElement(E data, AdListElement<E> next) {
            setData(data);
            setNext(next);
        }

        /**
         * Returns the containing data.
         *
         * @return the typed data object
         */
        private E getData() {
            return data;
        }

        /**
         * Sets a new data object.
         *
         * @param data the object
         */
        private void setData(E data) {
            this.data = data;
        }

        /**
         * Returns the reference to the following linked object. Can be null.
         *
         * @return the following liked object
         */
        private AdListElement<E> getNext() {
            count();
            return next;
        }

        /**
         * Sets the reference to the following linked element.
         *
         * @param next the following element
         */
        private void setNext(AdListElement<E> next) {
            //count();
            this.next = next;
        }

        /**
         * Asks whether an link to a following element exists.
         *
         * @return true if a following element is linked, false if not
         */
        private boolean hasNext() {
            return getNext() != null;
        }

    }

}
