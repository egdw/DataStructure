package com.hongdeyan.queue;

import com.hongdeyan.list.EgdwDoubleLinkedList;

import java.util.NoSuchElementException;

/**
 * 基于双向连别的队列
 * @author egdw
 */
public class EgdwLinkedQueue<E> implements EgdwQueue<E> {

    private EgdwDoubleLinkedList<E> list;

    public EgdwLinkedQueue() {
        list = new EgdwDoubleLinkedList<>();
    }

    @Override
    public boolean add(E e) {
        list.add(e);
        return true;
    }

    @Override
    public E remove() {
        return list.pull();
    }

    @Override
    public E poll() {
        return list.pull();
    }

    @Override
    public E element() {
        E first = list.getFirst();
        if(first == null){
            throw new NoSuchElementException("not fount element");
        }
        return first;
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public int size() {
        return list.size();
    }
}
