package com.hongdeyan.stack;

public interface EgdwStackImpl<E> {
    public E push(E item) ;

    public E pop();

    public E peek();

    public boolean empty();

    public int search(E e);

    public int size();

}
