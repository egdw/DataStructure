package com.hongdeyan.queue;

import com.hongdeyan.heap.EgdwMaxHeap;

/**
 * 基于最大堆的优先队列
 *
 * @param <E>
 *
 * @author egdw
 *
 */
public class EgdwPriorityQueue<E extends Comparable> {


    private EgdwMaxHeap<E> heap;

    public EgdwPriorityQueue() {
        heap = new EgdwMaxHeap<>();
    }

    public boolean add(E o) {
        heap.add(o);
        return true;
    }

    public E getTop() {
        return heap.getTop();
    }

    public E poll() {
        return heap.getFirst();
    }

    public int size() {
        return heap.size();
    }
}
