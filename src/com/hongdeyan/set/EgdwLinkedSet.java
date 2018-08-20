package com.hongdeyan.set;

import com.hongdeyan.list.EgdwDoubleLinkedList;

/**
 * 基于链表的集合set
 *
 * @param <E>
 * @author egdw
 */
public class EgdwLinkedSet<E extends Comparable> implements EgdwSet<E> {

    private EgdwDoubleLinkedList<E> list;

    public EgdwLinkedSet() {
        list = new EgdwDoubleLinkedList<E>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            list.add(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        return list.remove(e);
    }

    @Override
    public void clear() {
        list.clear();
    }
}
