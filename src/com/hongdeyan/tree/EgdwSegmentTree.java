package com.hongdeyan.tree;

/**
 * 线段树(平衡二叉树)
 *
 * 主要用于任意选取一部分查询出相应的结果
 * 效率高,速度快
 * O(logN)
 *
 * @param <E>
 * @author egdw
 */
public class EgdwSegmentTree<E> {

    //用户传入的数组引用
    private E[] data;
    //真实的树数组
    private E[] tree;
    private EgdwSegmentTreeMerge<E> merge;

    public EgdwSegmentTree(E[] data, EgdwSegmentTreeMerge<E> merge) {
        this.merge = merge;
        this.data = (E[]) new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i];
        }
        this.tree = (E[]) new Object[data.length * 4];
        buildSegmentTree(0, 0, size() - 1);
    }

    /**
     * 创建一个线段树
     *
     * @param treeIndex 当前的索引
     * @param l         当前区间的左索引
     * @param r         当前区间的右索引
     */
    //treeindex 的位置创建表示区间[l...r]的线段数
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            //如果左边和右边相同代表到达了叶子节点
            tree[treeIndex] = data[l];
            return;
        }
        int leftTree = getLeftChild(treeIndex);
        int rightTree = getRightChild(treeIndex);

        //获取两者的中间
        int mid = l + (r - l) / 2;
        //递归
        buildSegmentTree(leftTree, l, mid);
        buildSegmentTree(rightTree, mid + 1, r);

        //合并父节点的数据
        tree[treeIndex] = merge.merge(tree[leftTree], tree[rightTree]);
    }

    /**
     * 修改某个值
     * @param index
     * @param newValue
     */
    public void update(int index, E newValue) {
        if (index > data.length || index < 0) {
            throw new IllegalArgumentException("index is illegal");
        }

        //查找置顶索引位置的元素在树的数组索引在哪里
        int rootIndex = 0;
        int left = 0;
        int right = size() - 1;
        while (true) {
            int mid = left + (right - left) / 2;
            if (right == left) {
                //说明找到底了
                updateElement(rootIndex, newValue);
                break;
            }
            if (index < mid + 1) {
                rootIndex = getLeftChild(rootIndex);
                right = mid;
            } else if (index >= mid + 1) {
                rootIndex = getRightChild(rootIndex);
                left = mid + 1;
            }
        }


    }

    private void updateElement(int index, E newValue) {
        tree[index] = newValue;
        int parentIndex = getParent(index);
        //获取叶子节点的父节点
        if (parentIndex >= 0) {
            //递归调用
            updateElement(parentIndex, merge.merge(tree[getLeftChild(parentIndex)], tree[getRightChild(parentIndex)]));
        }
    }


    public int size() {
        return data.length;
    }

    /**
     * 获取某个指定索引下数组的值
     *
     * @param index 索引
     * @return 值
     */
    public E get(int index) {
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    /**
     * 查询指定索引区间的数据
     *
     * @param l 左索引
     * @param r 右索引
     * @return 左索引和右索引的合并
     */
    public E query(int l, int r) {
        return query(0, l, r, 0, size() - 1);
    }


    /**
     * 递归查询指定索引区间内的数据
     *
     * @param treeIndex 当前的开始节点
     * @param l         左索引
     * @param r         右索引
     * @param queryL    当前节点左范围
     * @param queryR    当前节点右范围
     * @return 指定或部分索引的值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //如果左索引和右索引都与当前节点的范围一样的话就返回当前的数据
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int leftChildIndex = getLeftChild(treeIndex);
        int rightChildIndex = getRightChild(treeIndex);
        // 查询当前节点的中间值是多少
        int queryMid = queryL + (queryR - queryL) / 2;
        if (r < queryMid + 1) {
            //如果当前的节点的索引都在左孩子那边的话
            return query(leftChildIndex, l, r, queryL, queryMid);
        } else if (l >= queryMid + 1) {
            //如果当前的节点的索引都在右孩子那边的话
            return query(rightChildIndex, l, r, queryMid + 1, queryR);
        }

        //如果是分布到两边的话
        E leftResult = query(leftChildIndex, l, queryMid, queryL, queryMid);
        E rightResult = query(rightChildIndex, queryMid + 1, r, queryMid + 1, queryR);
        return merge.merge(leftResult, rightResult);
    }


    private int getLeftChild(int index) {
        return (index * 2) + 1;
    }

    private int getRightChild(int index) {
        return (index * 2) + 2;
    }


    private int getParent(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }
}
