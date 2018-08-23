package com.hongdeyan.heap;

import com.hongdeyan.list.EgdwArrayList;

/**
 * 基于动态数组的堆(最大堆)
 * 二分完全树
 *
 * @author egdw
 */
public class EgdwMaxHeap<E extends Comparable> {


    private EgdwArrayList<E> list;


    public EgdwMaxHeap() {
        list = new EgdwArrayList<>();
    }

    public EgdwMaxHeap(E[] data) {
        //数组转换成二叉完全树
        arrayToList(data);
    }

    /**
     * 传入的数组转换成二叉完全树
     *
     * @param data
     */
    private void arrayToList(E[] data) {
        list = new EgdwArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                list.add(data[i]);
            }
            for (int i = getParent(list.size() - 1); i >= 0; i--) {
                System.out.println(i);
                shiftDown(i);
            }
        }

    }

    /**
     * 获取root节点
     * @return 根节点
     */
    public E getTop(){
        if(size() == 0){
            throw new IndexOutOfBoundsException("size equals zero");
        }
        return list.get(0);
    }

    /**
     * 添加新元素
     *
     * @param element 元素
     */
    public void add(E element) {
        if (list.contains(element)) {
            return;
        }
        list.add(element);
        shiftUp(size() - 1);
    }


    /**
     * 上扬
     *
     * @param index 当前的node节点
     */
    private void shiftUp(int index) {
        int parentIndex = getParent(index);
        while (parentIndex > -1) {
            E src = list.get(index);
            E parentElement = list.get(parentIndex);
            if (src.compareTo(parentElement) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
                parentIndex = getParent(index);
            } else {
                break;
            }
        }
    }


    /**
     * 获取排名第一的节点并移除
     *
     * @return 排名第一的节点
     */
    public E getFirst() {
        if (list.size() >= 2) {
            E rootElement = list.get(0);
            list.set(list.get(size() - 1), 0);
            list.removeLast();
            shiftDown(0);
            return rootElement;
        } else if (list.size() == 1) {
            E rootElement = list.get(0);
            list.removeLast();
            return rootElement;
        }
        return null;
    }


    /**
     * 获取排名第一的节点并插入新的节点
     *
     * @param element 新节点
     * @return 排名第一的节点
     */
    public E replace(E element) {
        if (size() > 0) {
            E rootElement = list.get(0);
            list.set(element, 0);
            shiftDown(0);
            return rootElement;
        }
        return element;
    }


    /**
     * 下洋
     * @param index 当前的node索引
     */
    private void shiftDown(int index) {
        while (true) {
            E rootElement = list.get(index);
            int leftChildIndex = getLeftChild(index);
            int rightChildIndex = getRightChild(index);
            if (leftChildIndex < size() || rightChildIndex < size()) {
                if (leftChildIndex < size() && rightChildIndex >= size()) {
                    //如果左节点存在右节点不存在的情况
                    if (rootElement.compareTo(list.get(leftChildIndex)) < 0) {
                        swap(index, leftChildIndex);
                        index = leftChildIndex;
                        continue;
                    }
                    break;
                } else if (rightChildIndex < size() && leftChildIndex >= size()) {
                    //如果右节点存在左节点不存在的情况
                    if (rootElement.compareTo(list.get(rightChildIndex)) < 0) {
                        swap(index, rightChildIndex);
                        index = rightChildIndex;
                        continue;
                    }
                    break;
                } else if (rightChildIndex >= size() && leftChildIndex >= size()) {
                    //如果左右节点都不存在的情况
                    break;
                } else if (rightChildIndex < size() && leftChildIndex < size()) {
                    //如果左右节点都存在的情况
                    if (list.get(leftChildIndex).compareTo(list.get(rightChildIndex)) > 0) {
                        //左孩子大于右孩子
                        if (rootElement.compareTo(list.get(leftChildIndex)) < 0) {
                            swap(index, leftChildIndex);
                            index = leftChildIndex;
                            continue;
                        }
                        break;
                    } else {
                        if (rootElement.compareTo(list.get(rightChildIndex)) < 0) {
                            swap(index, rightChildIndex);
                            index = rightChildIndex;
                            continue;
                        }
                        break;
                    }
                }
                break;

            } else {
                //说明下面已经完全没有子节点了.
                break;
            }
        }
    }


    public int size() {
        return list.size();
    }

    /**
     * 交互list两个索引的值
     * @param src 第一个索引
     * @param dest 第二个索引
     */
    private void swap(int src, int dest) {
        E e = list.get(dest);
        list.set(list.get(src), dest);
        list.set(e, src);
    }

    /**
     * 得到父亲索引
     * @param index 子索引
     * @return 父索引
     */
    private int getParent(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    /**
     * 得到左孩子的索引
     * @param index 父索引
     * @return 左孩子索引
     */
    private int getLeftChild(int index) {
        return (index * 2 + 1);
    }

    /**
     * 得到右孩子的索引
     * @param index 父索引
     * @return 右孩子索引
     */
    private int getRightChild(int index) {
        return (index * 2 + 2);
    }
}
