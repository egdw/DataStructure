package com.hongdeyan.tree;

import com.hongdeyan.map.EgdwBinarySearchMap;

/**
 * 基于treeMap的trie(字典树)
 * 达到快速查找的效果
 * 通俗的来说就是一棵字符树
 *
 * @param <E>
 */
public class EgdwTrie<E> {

    private Node<E> root;
    private int size;

    public EgdwTrie() {
        root = new Node<>();
    }

    private class Node<E> {
        //判断当前是否是单词的尾部
        private boolean isWord;
        private EgdwBinarySearchMap<Character, Node<E>> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new EgdwBinarySearchMap<>();
        }

        public Node() {
            next = new EgdwBinarySearchMap<>();
        }
    }


    /**
     * 添加单词
     *
     * @param word 需要添加的单词
     */
    public void addWord(String word) {
        if (word == null || "".equals(word)) {
            throw new IllegalArgumentException("str can't empty or null");
        }
        //这里先使用非递归的添加方式
        addIntoNode(word);
//        char[] charArray = word.toCharArray();
//        addIntoNode(charArray, 0, root);
    }


    public boolean contains(String word) {
        Node<E> temp = root;
        for (int i = 0; i < word.length(); i++) {
            temp = temp.next.get(word.charAt(i));
            if (temp == null) {
                return false;
            }
        }
        return temp.isWord;

    }



    /**
     * 非递归方式的添加单词
     *
     * @param word
     */
    private void addIntoNode(String word) {
        Node<E> temp = root;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            char charAt = word.charAt(i);
            Node<E> eNode = temp.next.get(charAt);
            boolean isWord = false;
            if (i + 1 == length) {
                isWord = true;
            }
            if (eNode == null) {
                eNode = new Node<>(isWord);
                temp.next.add(charAt, eNode);
            } else {
                if (!eNode.isWord && isWord) {
                    size++;
                    eNode.isWord = isWord;
                }
            }
            temp = eNode;
        }
    }

    /**
     * 进行单词添加递归操作函数
     * 效率没有直接循环的快.主要卡在charArray这里速度比较慢..
     * 能力有限...
     *
     * @param charArray 单词的char数组
     * @param index     当前索引
     * @param root      当前指向的node节点
     */
    private void addIntoNode(char[] charArray, int index, Node<E> root) {
        Node<E> eNode = root.next.get(charArray[index]);
        boolean isWord = false;
        //用于在递归中判断是否是重复元素
        boolean formerIsWord = false;
        if (index == charArray.length - 1) {
            //说明已经到底了
            isWord = true;
        }
        //如果节点不存在添加新的节点
        if (eNode == null) {
            //说明不存在.
            eNode = new Node<>(isWord);
            root.next.add(charArray[index], eNode);
        } else {
            formerIsWord = eNode.isWord;
            eNode.isWord = isWord;
        }
        if (!isWord) {
            addIntoNode(charArray, ++index, eNode);
        } else {
            if (!formerIsWord) {
                size++;
            }
            return;
        }
    }

    /**
     * 判断是否有已word开头的单词
     *
     * @param word
     * @return
     */
    public boolean prefix(String word) {
        Node<E> temp = root;
        for (int i = 0; i < word.length(); i++) {
            temp = temp.next.get(word.charAt(i));
            if (temp == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取当前的单词数量
     *
     * @return 当前数量
     */
    public int size() {
        return size;
    }

}
