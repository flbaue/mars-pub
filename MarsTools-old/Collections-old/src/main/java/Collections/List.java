package Collections;

/**
 * Created by Florian Bauer on 17.01.14. flbaue@posteo.de
 */
public interface List<T> extends Iterable<T> {

    void add(T element);

    T get(int position);

    void remove(int position);

    int size();
}
