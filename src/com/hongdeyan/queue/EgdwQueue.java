package com.hongdeyan.queue;

public interface EgdwQueue<E> {
    boolean add(E e);

    E remove();

    E poll();

    E element();

    E peek();
}
