package com.hongdeyan.union;

/**
 * 实现最简单的并查集
 *
 * @author egdw
 */
public class EgdwQuickFind{
    private int[] data;

    public EgdwQuickFind(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0 ");
        }
        data = new int[size];
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


    private int find(int p) {
        return data[p];
    }

    /**
     * 合并元素
     * O(N)
     *
     * @param p 索引1
     * @param q 索引2
     */
    public void unionElement(int p, int q) {
        if (isConnected(p, q)) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] == find(q)) {
                data[i] = find(p);
            }
        }
    }

    public int size() {
        return data.length;
    }
}
