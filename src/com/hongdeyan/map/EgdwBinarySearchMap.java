package com.hongdeyan.map;


import java.util.NoSuchElementException;

/**
 * 基于二分搜索树的Map映射
 *
 * @param <K> 主键
 * @param <V> 值
 */
public class EgdwBinarySearchMap<K extends Comparable, V> implements EgdwMap<K, V> {

    private int size;
    private Node<K, V> root;

    private class Node<K extends Comparable, V> {
        private Node<K, V> leftNode;
        private Node<K, V> rightNode;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }


        public Node<K, V> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node<K, V> leftNode) {
            this.leftNode = leftNode;
        }

        public Node<K, V> getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node<K, V> rightNode) {
            this.rightNode = rightNode;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    @Override
    public boolean add(K key, V value) {
        root = addElement(root, key, value);
        return true;
    }


    private Node<K, V> addElement(Node<K, V> root, K key, V value) {
        if (root == null) {
            size++;
            return new Node<>(key, value);
        }
        int compareResult = key.compareTo(root.key);
        if (compareResult > 0) {
            root.rightNode = addElement(root.rightNode, key, value);
            return root;
        } else if (compareResult < 0) {
            root.leftNode = addElement(root.leftNode, key, value);
            return root;
        }
        return root;
    }


    @Override
    public boolean isExsits(K key) {

        return exsitsELement(root, key);
    }


    private boolean exsitsELement(Node<K, V> root, K key) {
        if (root == null) {
            return false;
        }
        int compareResult = key.compareTo(root.key);
        if (compareResult > 0) {
            return exsitsELement(root.rightNode, key);
        } else if (compareResult < 0) {
            return exsitsELement(root.leftNode, key);
        } else {
            //说明找到了.
            return true;
        }
    }


    @Override
    public boolean set(K key, V newValue) {
        return setElement(root, key, newValue);
    }


    private boolean setElement(Node<K, V> root, K key, V value) {
        if (root == null) {
            return false;
        }
        int compareResult = key.compareTo(root.key);
        if (compareResult > 0) {
            return setElement(root.rightNode, key, value);
        } else if (compareResult < 0) {
            return setElement(root.leftNode, key, value);
        } else {
            //说明找到了.
            root.value = value;
            return true;
        }
    }


    @Override
    public boolean remove(K key) {
        return removeElement(root, key) == null ? false : true;
    }


    private Node<K, V> removeElement(Node<K, V> root, K element) {
        if (root == null) {
            throw new NoSuchElementException("node not found");
        }
        int compareResult = element.compareTo(root.getKey());
        if (compareResult > 0) {
            root.rightNode = removeElement(root.getRightNode(), element);
            return root;
        } else if (compareResult < 0) {
            root.leftNode = removeElement(root.getLeftNode(), element);
            return root;
        } else {
            //如果相同,进行删除操作
            //需要分三种情况
            Node leftNode = root.getLeftNode();
            Node rightNode = root.getRightNode();
            //如果没有子node的情况
            if (leftNode == null && rightNode == null) {
                root = null;
                size--;
            } else if (leftNode == null && rightNode != null) {
                //如果左孩子没有.右孩子有
                root.setKey((K) rightNode.getKey());
                root.setRightNode(rightNode.getRightNode());
                size--;
            } else if (leftNode != null && rightNode == null) {
                //如果左孩子有,右孩子没有
                root.setKey((K) leftNode.getKey());
                root.setLeftNode(leftNode.getLeftNode());
                size--;
            } else {
                //如果左右孩子都有,找到左节点最大的数或者右节点最小的数进行替换
                K e = (K) findBiggestNode(leftNode).getKey();
                //返回左节点最大的数并删除左节点最大的数
//                root.setElement((E) removeBiggestNode(leftNode, null));
                root.setKey(e);
                root.setLeftNode(removeElement(leftNode, e));
            }
            return root;
        }
    }

    /**
     * 查找某个节点下最大的值
     *
     * @param root 节点
     * @return 返回最大的值
     */
    private Node<K, V> findBiggestNode(Node<K, V> root) {
        if (root == null) {
            throw new UnsatisfiedLinkError("not found node");
        }
        if (root.rightNode != null) {
            return findBiggestNode(root.rightNode);
        } else {
            return root;
        }
    }


    /**
     * 删除某个节点下最大的值
     *
     * @param root    需要删除的某个节点
     * @param preNode 保存上一个子节点.
     * @return
     */
    private V removeBiggestNode(Node<K, V> root, Node<K, V> preNode) {
        if (root == null) {
            throw new UnsatisfiedLinkError("not found node");
        }
        if (root.rightNode != null) {
            return removeBiggestNode(root.rightNode, root);
        } else {
            //说明root.rightNode == null 了
            //删除
            preNode.setRightNode(null);
            size--;
            return root.getValue();
        }
    }


    /**
     * 找到某个Node左孩子最下的那个分支.
     *
     * @param root
     * @return
     */
    private Node<K, V> getLeftLastChildNode(Node<K, V> root) {
        if (root != null) {
            if (root.getLeftNode() == null) {
                return root;
            }
            return getLeftLastChildNode(root.getLeftNode());
        }
        return null;
    }


    @Override
    public V get(K key) {
        return getElement(root, key);
    }

    private V getElement(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        int compareResult = key.compareTo(root.key);
        if (compareResult > 0) {
            return getElement(root.rightNode, key);
        } else if (compareResult < 0) {
            return getElement(root.leftNode, key);
        } else {
            return root.value;
        }
    }

    @Override
    public int size() {
        return size;
    }
}
