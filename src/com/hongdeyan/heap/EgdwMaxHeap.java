package com.hongdeyan.heap;

import com.hongdeyan.list.EgdwArrayList;

import java.util.NoSuchElementException;

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

    public EgdwMaxHeap(int size) {
        if (size > 0) {
            list = new EgdwArrayList<>(size);
        } else {
            list = new EgdwArrayList<>();
        }
    }

//    /**
//     * 存在重复插入数据问题
//     * @param element
//     */
//    public void add(E element) {
//        list.add(element);
//        int index = size() - 1;
//        int parentIndex = getParent(index);
//        int compareResult = element.compareTo(list.get(parentIndex));
//        //shiftUp
//        while (compareResult > 0) {
//            if (parentIndex < 0) {
//                break;
//            }
//            swap(index, parentIndex);
//            index = parentIndex;
//            parentIndex = getParent(index);
//            compareResult = element.compareTo(list.get(parentIndex));
//
//        }
//    }

    /**
     * 存在重复插入数据问题
     *
     * @param element
     */
    public void add(E element) {
        list.add(element);
        int index = size() - 1;
        int parentIndex = getParent(index);
        int compareResult = element.compareTo(list.get(parentIndex));
        //shiftUp
        while (compareResult >= 0) {
            System.out.println(compareResult);
            if (parentIndex < 0 || compareResult == 0) {
                System.out.println("close");
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = getParent(index);
            compareResult = element.compareTo(list.get(parentIndex));
        }
    }

    public E get(int index) {
        return list.get(index);
    }


    public int size() {
        return list.size();
    }


    /**
     * 对两个索引之间的元素进行交换
     *
     * @param srcIndex  原索引
     * @param destIndex 需要改变的索引
     */
    private void swap(int srcIndex, int destIndex) {
        E srcElement = list.get(srcIndex);
        E destElement = list.get(destIndex);
        list.set(destElement, srcIndex);
        list.set(srcElement, destIndex);
    }


    /**
     * 获取最大的元素并移除
     *
     * @return
     */
    public E getFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("no element!");
        }
        E e = list.get(0);
        remove();
        return e;
    }

    private void remove() {
        //判断是否有可以删除的元素
        if (list.size() > 2) {
            int tailElementIndex = size() - 1;
            E tailElementValue = list.get(tailElementIndex);
            //设置头为索引最下的值
            list.set(tailElementValue, 0);
            //移除最下的索引
            list.removeIndex(tailElementIndex);
            //从root根开始
            //shiftDown
            int index = 0;
            while (true) {
                //根据上一个index索引获取子节点索引
                int leftChild = getLeftChild(index);
                int rightChild = getRightChild(index);
                //如果左右孩子中有一个的索引超过了list的size.说明已经到底了.没有更底层的叶子了.
                if (leftChild >= size() || rightChild >= size()) {
                    //说明已经到底了
                    break;
                }
                E leftElement = list.get(leftChild);
                E rightElement = list.get(rightChild);
                //如果上一个index节点确实比左右孩子的节点小的话
                if (tailElementValue.compareTo(rightElement) < 0 || tailElementValue.compareTo(leftElement) < 0) {
                    //比较出左右节点哪边的节点大
                    int compare = leftElement.compareTo(rightElement);
                    if (compare > 0) {
                        //左孩子大
                        //交换
                        swap(index, leftChild);
                        //把当前父节点变成左孩子
                        index = leftChild;
                    } else if (compare < 0) {
                        //右孩子大
                        swap(index, rightChild);
                        //把当前父节点变成右孩子
                        index = rightChild;
                    }
                }
            }
        } else {
            //如果只有一个root节点了.直接删除root节点.当前list为空
            list.removeIndex(0);
        }
    }

    /**
     * 获取右孩子的子节点
     *
     * @param index 当前索引
     * @return 右孩子的索引
     */
    private int getRightChild(int index) {
        return (index * 2 + 1);
    }

    /**
     * 获取左孩子的子节点
     *
     * @param index 当前索引
     * @return 左孩子的索引
     */
    private int getLeftChild(int index) {
        return (index * 2 + 2);
    }

    /**
     * 获取子节点的父节点
     *
     * @param index 当前的索引
     * @return 父节点的索引
     */
    private int getParent(int index) {
        return ((index - 1) / 2);
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
