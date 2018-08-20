package com.hongdeyan.set;

import com.hongdeyan.tree.EgdwBinarySearchTree;

/**
 * 基于二分查找树的集合
 *
 * @param <E> 必须继承Comparable接口
 * @author egdw
 */
public class EgdwBinarySearchSet<E extends Comparable> implements EgdwSet<E> {

    private EgdwBinarySearchTree<E> tree;


    public EgdwBinarySearchSet() {
        tree = new EgdwBinarySearchTree<E>();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.size() == 0;
    }

    @Override
    public boolean contains(E e) {
        return tree.contains(e);
    }

    @Override
    public boolean add(E e) {
        tree.add(e);
        return true;
    }

    @Override
    public boolean remove(E e) {
        tree.remove(e);
        return true;
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public String toString() {
        return "EgdwBinarySearchSet{" +
                "tree=" + tree +
                '}';
    }
}
