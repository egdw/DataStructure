package com.hongdeyan.queue;

import com.hongdeyan.list.EgdwArrayList;

/**
 * 特点
 * 先入后出的队列
 * 效率比较低,每次出栈会出O(n)
 *
 * @author egdw
 */
public class EgdwArrayQueue<E> implements EgdwQueue<E> {

    private EgdwArrayList<E> list;
    private static final int DEFAULT_SIZE = 10;

    public EgdwArrayQueue() {
        list = new EgdwArrayList<>(EgdwArrayQueue.DEFAULT_SIZE);
    }

    public EgdwArrayQueue(int size) {
        if (size < 0) {
            list = new EgdwArrayList<>(EgdwArrayQueue.DEFAULT_SIZE);
        } else {
            list = new EgdwArrayList<>(size);
        }

    }

    /**
     * 添加数据到队列末尾
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        int add = list.add(e);
        return add > 0 ? true : false;
    }

    /**
     * 单纯删除队列首位数据
     * 如果不存在则抛出异常
     * @return
     */
    @Override
    public E remove() {
        return list.removeIndex(0);
    }

    /**
     * 从队列首列中提取出来并移除
     * 如果队列为空返回null
     * @return
     */
    @Override
    public E poll() {
        E e = list.get(0);
        list.removeIndex(0);
        return e;
    }

    /**
     * 返回当前队列首位.如果不存在则抛出异常.
     *
     * @return 首位数据
     */
    @Override
    public E element() {
        E e = list.get(0);
        if (e == null) {
            throw new NullPointerException("队列顶部不存在数据");
        }
        return e;
    }

    /**
     * 返回当前队列首位.如果不存在则返回null.
     *
     * @return 首位数据
     */
    @Override
    public E peek() {
        return list.get(0);
    }


    @Override
    public String toString() {
        return list.toString();
    }
}
