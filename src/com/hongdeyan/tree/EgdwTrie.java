package com.hongdeyan.tree;

import com.hongdeyan.list.EgdwArrayList;
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
        word.trim();
        char[] charArray = word.toCharArray();
        addIntoNode(charArray, 0, root);
    }

    //TODO 待完成
    public void findAll(String word) {
        if (word == null || "".equals(word)) {
            throw new IllegalArgumentException("str can't empty or null");
        }
        word.trim();
//        char[] charArray = word.toCharArray();
//        Node<E> eNode = root;
//        EgdwArrayList<String> arrayList = new EgdwArrayList<>();
//        StringBuilder builder = new StringBuilder(word);
//        for (int i = 0; i < charArray.length; i++) {
//            eNode = eNode.next.get(charArray[i]);
//            if (eNode == null) {
//                return null;
//            }
//        }
////        while(){
////
////        }
    }

    //TODO 待完成
    public String findOne(String word) {
        if (word == null || "".equals(word)) {
            throw new IllegalArgumentException("str can't empty or null");
        }
        word.trim();
        StringBuilder sb = new StringBuilder();
        char[] charArray = word.toCharArray();
        int index = 0;
        Node<E> tempRoot = root;
        while (true) {
            char c = charArray[index];
            tempRoot = tempRoot.next.get(c);
            if (tempRoot == null) {
                return null;
            } else {
                sb.append(c);
            }
            break;
        }
        return sb.toString();
    }


    /**
     * 进行单词添加递归操作函数
     *
     * @param charArray 单词的char数组
     * @param index     当前索引
     * @param root      当前指向的node节点
     */
    private void addIntoNode(char[] charArray, int index, Node<E> root) {
        Node<E> eNode = root.next.get(charArray[index]);
        boolean isWord = false;
        if (index == charArray.length - 1) {
            //说明已经到底了
            isWord = true;
        }
        if (eNode == null) {
            //说明不存在.
            eNode = new Node<>(isWord);
            root.next.add(charArray[index], eNode);
        } else {
            eNode.isWord = isWord;
        }
        if (!isWord) {
            addIntoNode(charArray, ++index, eNode);
        } else {
            return;
        }
    }

}
