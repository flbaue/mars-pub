package Collections;

import java.util.Iterator;

/**
 * Created by Florian Bauer on 17.01.14. flbaue@posteo.de
 */
public class ArrayList<T> implements List<T> {

    private Object[] content;
    private int elements = 0;
    private int initialSize = 25;

    public ArrayList(){
        content = new Object[initialSize];
    }

    public ArrayList(int initialSize) {
        this.initialSize = initialSize;
        content = new Object[this.initialSize];
    }

    @Override
    public void add(T element) {
        if(elements == initialSize) {
            expandContent();
        }

        content[elements] = element;
        elements += 1;
    }

    private void expandContent() {
        Object[] newContent = new Object[initialSize + size()];
        for(int position = 0; position < size(); position++) {
            newContent[position] = content[position];
        }
        content = newContent;
    }

    @Override
    public T get(int position) {
        return (T) content[position];
    }

    @Override
    public void remove(int position) {
        content[position] = null;
        moveTailForward(position + 1, 1);
    }

    private void moveTailForward(int startPosition, int steps) {
        for(int position = startPosition; position < size(); position++) {
            content[position - steps] = content[position];
        }
    }

    @Override
    public int size() {
        return elements;
    }


    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }
}
