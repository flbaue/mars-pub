/*
 * Florian Bauer
 * flbaue@gmail.com
 * Copyright 2013
 */
package se1p.t1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * OneToMany
 * A basic implementation of the OneToManyInterface
 * 
 * @author Florian Bauer
 */
public class OneToMany<E> implements OneToManyInterface<E>{

    private Set<E> values;
    
    public OneToMany() {
        values = new HashSet<E>();
    }

    @Override
    public boolean add(E e) {
        return values.add(e);
    }

    @Override
    public boolean addAll(OneToManyInterface<? extends E> e) {
        return values.addAll(convertToCollection(e));
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public boolean contains(E o) {
        return values.contains(o);
    }

    @Override
    public boolean containsAll(OneToManyInterface<? extends E> c) {
        return values.containsAll(convertToCollection(c));
    }

    @Override
    public boolean remove(E o) {
        return values.remove(o);
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public Iterator<E> iterator() {
        return values.iterator();
    }

    @Override
    public OneToManyInterface<E> clone() {
       OneToManyInterface<E> clone = new OneToMany();
       clone.addAll(convertToOneToMany(values));
       return clone;
    }           

    private Collection<E> convertToCollection(OneToManyInterface<? extends E> e) {
        
        Collection<E> collection = new HashSet<E>();
        
        for(Iterator it = e.iterator(); it.hasNext();) {
            collection.add((E) it.next());     
        }
        return collection;   
    }
    
     private OneToManyInterface<E> convertToOneToMany(Collection<? extends E> e) {
        
        OneToManyInterface<E> oneToMany = new OneToMany<E>();
        
        for(Iterator it = e.iterator(); it.hasNext();) {
            oneToMany.add((E) it.next());     
        }
        return oneToMany;   
    }
    
}
