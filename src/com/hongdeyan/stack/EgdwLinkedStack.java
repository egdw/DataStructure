package com.hongdeyan.stack;

import com.hongdeyan.list.EgdwSingleLinkedList;

/**
 * 使用linkedlist实现栈
 */
public class EgdwLinkedStack<E> implements EgdwStackImpl<E> {

    private EgdwSingleLinkedList<E> list;


    public EgdwLinkedStack() {
        list = new EgdwSingleLinkedList<>();
    }

    @Override
    public E push(E item) {
        boolean add = list.add(item);
        return null;
    }

    @Override
    public E pop() {
        return list.poll();
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean empty() {
        return list.size() == 0;
    }

    @Override
    public int search(E e) {
        return 0;
    }

    @Override
    public int size() {
        return list.size();
    }
}
