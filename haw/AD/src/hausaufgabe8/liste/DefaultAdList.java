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
package hausaufgabe8.liste;

/**
 * AdListeImpl implements the {@link AdList} as a simple linked list.
 *
 * @param <T>
 * @author Florian Bauer
 */
public class DefaultAdList<T extends Comparable<T>> implements AdList<T> {

    private ListElement<T> head;
    private int size = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void cons(T elem) {
        size++;
        head = new ListElement<T>(elem, head);
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

        ListElement<T> currentElement = head;
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
        ListElement<T> newElement = new ListElement<T>(elem, null);
        if (head == null) {
            head = newElement;
        } else if (position <= 0) {
            newElement.setNext(head);
            head = newElement;
        } else if (position >= size()) {
            ListElement<T> currentElement = head;
            while (currentElement.hasNext()) {
                currentElement = currentElement.getNext();
            }
            currentElement.setNext(newElement);
        } else {
            ListElement<T> currentElement = head.getNext();
            ListElement<T> previousElement = head;
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
            ListElement<T> newElem = new ListElement<T>(elem, head);
            head = newElem;
        } else {
            ListElement<T> currentElem = head;
            recInsert(currentElem, elem, position);
        }
    }

    private void recInsert(ListElement<T> currentElem, T elem, int steps) {
        if (steps == 1) {
            ListElement<T> prevElem = currentElem;
            ListElement<T> nextElem = currentElem.getNext();
            ListElement<T> newElem = new ListElement<T>(elem, nextElem);
            prevElem.setNext(newElem);
        }
        if (steps > 1) {
            ListElement<T> newCurrentElem = currentElem.getNext();
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
        ListElement<T> currentElem = head;
        int i = 0;
        while (currentElem.hasNext() && i < position) {
            currentElem = currentElem.getNext();
            i++;
        }
        if (i == position) {
            return currentElem.getData();
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
        ListElement<T> newElem = new ListElement<T>(elem, null);

        if (head == null) {
            head = newElem;

        } else {

            ListElement<T> currentElem = head;

            while (currentElem.hasNext()) {
                currentElem = currentElem.getNext();
            }

            currentElem.setNext(newElem);
        }
    }

    public int getSteps() {
        return ListElement.getSteps();
    }

    public void resetCounter() {
        ListElement.resetCounter();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isSorted() {
        if (head == null) {
            return true;
        }

        ListElement<T> current = head;
        ListElement<T> next = current.getNext();

        while (next != null) {
            if (current.compareTo(next) >= 0) {
                return false;
            }
            current = next;
            next = next.getNext();
        }
        return true;
    }

    public void preFillIntList(int n) {

        head = null;

        // TODO
//        while (size() < n) {
//            Integer val = (int) (Math.random() * 1000);
//            if(get(size()-1) <= val) {
//                add((T)val);
//            }
//        }
    }

//    public String toString() {
//        String result = "";
//        ListElement<T> current = head;
//        if (head == null) {
//            return result;
//        }
//
//        result += "Data:" + current.getData() + " -> ";
//
//        while (current.hasNext()) {
//            current = current.getNextWithoutCounting();
//            result += "Data:" + current.getData() + " -> ";
//        }
//
//        return result;
//    }


}
