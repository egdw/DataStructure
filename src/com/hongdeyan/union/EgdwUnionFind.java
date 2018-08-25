package com.hongdeyan.union;

import java.util.Arrays;

/**
 * 基于树的并查集
 *
 * @author egdw
 */
public class EgdwUnionFind {

    private int[] data;
    //基于size的优化方式
    //当二叉树的分支的高度太高会影响效率.
    //size的优化方式就是相当于通过判断当前那边的树枝分叉比较密集那么我不密集的树枝分叉就合并到密集的树枝分叉当中去.
    private int[] size;

    //基于深度的优化方式(效率更高)
    //根据树枝的高度将树枝高度少的树枝合并到树枝高度高的树枝上去
    private int[] rank;

    public EgdwUnionFind(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0 ");
        }
        data = new int[size];
//        this.size = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < data.length; i++) {
            //初始化数据
            data[i] = i;
            //基于size的优化方法
//            this.size[i] = 1;
            //基于rank的优化方法
            this.rank[i] = 1;
        }
    }


    /**
     * 判断是否是想关联的
     * O(1)
     *
     * @param p 索引1
     * @param q 索引2
     * @return
     */
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }


    /**
     * 不同的向上寻找父节点
     * O(h)
     *
     * @param p
     * @return
     */
    private int find(int p) {
        while (true) {
            if (data[p] != p) {
                //说明已经到顶了
                p = data[p];
                //第一种路径压缩..
//                data[p] = data[data[p]];
//                return p;
                //第二种数据压缩.极端
                data[p] = find(data[p]);
            }
            return data[p];
        }
    }

    /**
     * 合并元素
     * O(h)
     *
     * @param p 索引1
     * @param q 索引2
     */
    public void unionElement(int p, int q) {
        int qIndex = find(q);
        int pIndex = find(p);
        if (qIndex == pIndex) {
            return;
        }


        //rank的优化方式
        //通过计算出当前的深度进行合并.也可以极大的提高效率
        if (rank[qIndex] < rank[pIndex]) {
            data[qIndex] = data[pIndex];
        } else if (rank[qIndex] > rank[pIndex]) {
            data[pIndex] = data[qIndex];
        } else {
            data[pIndex] = data[qIndex];
            rank[qIndex] += 1;
        }


        //size的优化方式
        //通过判断当前索引下的size大小得出树枝的大小.可以比不处理极大的提高效率
//        if (size[qIndex] < size[pIndex]) {
//            data[qIndex] = data[pIndex];
//            size[pIndex] += size[qIndex];
//        } else {
//            data[pIndex] = data[qIndex];
//            size[qIndex] += size[pIndex];
//        }
    }

    public int size() {
        return data.length;
    }

    @Override
    public String toString() {
        return "EgdwUnionFind{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
