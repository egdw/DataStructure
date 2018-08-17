package com.hongdeyan.list;

public interface EgdwListImpl<E> {
    boolean add(E e);

    E poll();

    boolean remove();

    int size();

    E getFirst();

    E getLast();

    boolean isEmpty();

    boolean delete(E e);
}
