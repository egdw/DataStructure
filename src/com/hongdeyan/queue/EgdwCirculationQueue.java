package com.hongdeyan.queue;

import java.util.Arrays;

/**
 * 可变长循环队列
 * 出栈性能高 O(1)
 * 代码略微复杂一些
 */
public class EgdwCirculationQueue<E> implements EgdwQueue<E> {

    private Object[] list;

    //队列首位
    private int queueFirst;

    //队列末尾
    private int queueLast;

    //数据长度
    private int size;


    private static final int DEFAULT_SIZE = 10;

    private static final double DEFAULT_GROW_SIZE = 1.5;

    public EgdwCirculationQueue() {
        list = new Object[EgdwCirculationQueue.DEFAULT_SIZE];
    }

    public EgdwCirculationQueue(int size) {
        if (size > 0) {
            list = new Object[size];
        } else {
            list = new Object[EgdwCirculationQueue.DEFAULT_SIZE];
        }
    }

    /**
     * 添加一个元素到队尾
     *
     * @param e 元素
     * @return 是否成功
     */
    @Override
    public boolean add(E e) {
        int curIndex = queueLast % list.length;
        if ((curIndex + 1) == queueFirst && (curIndex != 0 || queueFirst != 0)) {
            //说明队列满了
            grow();
            list[queueLast] = e;
            queueLast++;
        } else {
            //队列未满
            list[curIndex] = e;
            queueLast = ++curIndex;
        }
        size++;
        return true;
    }

    /**
     * 把一个元素从对首删除
     *
     * @return 删除的元素
     */
    @Override
    public E remove() {
        E temp = (E) list[queueFirst];
        int curIndex = queueFirst % list.length;
        if (curIndex == queueLast) {
            //说明已经没有数据可以删了.
            return null;
        } else {
            queueFirst++;
            list[curIndex] = null;
            size--;
        }
        recycle();
        return temp;
    }

    /**
     * 从队列首列中提取出来并移除
     * 如果队列为空返回null
     *
     * @return
     */
    @Override
    public E poll() {
        Object last = list[queueFirst];
        remove();
        return (E) last;
    }

    /**
     * 返回当前队列首位.如果不存在则抛出异常.
     *
     * @return 首位数据
     */
    @Override
    public E element() {
        if (list[queueFirst] == null) {
            throw new NullPointerException("队列顶部不存在数据");
        }
        return (E) list[queueFirst];
    }

    /**
     * 返回当前队列首位.如果不存在则返回null.
     *
     * @return 首位数据
     */
    @Override
    public E peek() {
        return (E) list[queueFirst];
    }

    /**
     * 空间不足增加空间
     */
    private void grow() {
        int growSize = (int) (list.length * EgdwCirculationQueue.DEFAULT_GROW_SIZE);
        Object[] objs = new Object[growSize];
        getNewArray(objs);
    }

    /**
     * 回收空间
     */
    private void recycle() {
        if (size > 0 && list.length / size == 4) {
            //如果超过一半空闲的话.就减少空间
            Object[] objs = new Object[list.length / 2];
            getNewArray(objs);
        }
    }

    /**
     * 获取一个新的数组
     *
     * @param objs
     */
    private void getNewArray(Object[] objs) {
        for (int i = 0; i < list.length; i++) {
            int curIndex = queueFirst % list.length;
            objs[i] = list[(curIndex)];
            list[curIndex] = null;
            queueFirst++;
        }
        queueFirst = 0;
        queueLast = list.length - 1;
        list = objs;
    }


    /**
     * 获得当前循环队列内部元素数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "EgdwCirculationQueue{" +
                "list=" + Arrays.toString(list) +
                ", queueFirst=" + queueFirst +
                ", queueLast=" + queueLast +
                ", size=" + size +
                '}';
    }
}
