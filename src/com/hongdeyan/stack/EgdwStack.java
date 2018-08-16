package com.hongdeyan.stack;

import com.hongdeyan.list.EgdwArrayList;

import java.util.Stack;

/**
 * 栈
 *
 * @author egdw
 */
public class EgdwStack<E> implements EgdwStackImpl<E> {

    private EgdwArrayList<E> list;
    private static final int DEFAULT_SIZE = 10;


    public EgdwStack() {
        list = new EgdwArrayList<>(EgdwStack.DEFAULT_SIZE);
    }

    public EgdwStack(int size) {
        if (size > 0) {
            list = new EgdwArrayList<>(size);
        } else {
            list = new EgdwArrayList<>(EgdwStack.DEFAULT_SIZE);
        }
    }

    @Override
    public E push(E item) {
        list.add(item);
        return item;
    }

    @Override
    public E pop() {
        return list.removeIndex(list.size() - 1);
    }

    @Override
    public E peek() {
        return list.get(list.size() - 1);
    }

    @Override
    public boolean empty() {
        return list.size() == 0;
    }

    @Override
    public int search(E e) {
        return list.indexOf(e);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
