package com.hongdeyan.list;

/**
 * 自己实现ArrayList
 */
public class EgdwArrayList {

    private Object[] elements;
    private int size;

    /**
     * 构造函数传入初始elment大小
     *
     * @param size 初始大小
     */
    public EgdwArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size can't small 0");
        }
        this.size = size;
        elements = new Object[size];
    }

    public EgdwArrayList() {
        //如果不设置的话就默认为10
        this(10);
    }

    /**
     * 获取当前的size大小
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 返回当前的容量大小
     *
     * @return 当前容量大小
     */
    public long getOpcaity() {
        return this.size;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据index值获取相应的值
     *
     * @param index
     * @return 返回的对象
     */
    public Object get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index can not small 0");
        }
        if (index > size) {
            throw new IllegalArgumentException("out of EgdwArraylist size!");
        }
        return elements[index];
    }

    /**
     * 添加元素
     *
     * @param element 元素
     * @return 添加进入时候的索引
     */
    public int add(Object element) {
        return addLast(element);
    }

    /**
     * 添加元素到数组最后
     *
     * @param element 添加的元素
     * @return 返回添加进入时候的索引值
     */
    public int addLast(Object element) {
        if (size == elements.length) {
            //如果数组已经放满了,则抛出异常
            throw new ArrayIndexOutOfBoundsException("array is full");
        }
        elements[size] = element;
        size++;
        return size - 1;
    }


    public void add(Object element, int index) {

    }


}
