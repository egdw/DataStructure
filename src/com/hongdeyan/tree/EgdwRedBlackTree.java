package com.hongdeyan.tree;

import java.util.NoSuchElementException;

/**
 * 红黑树
 *
 * @param <K> key值
 * @param <V> value值
 * @author egdw
 * <p>
 * 每个节点要么是红色，要么是黑色。
 * 根节点必须是黑色
 * 红色节点不能连续（也即是，红色节点的孩子和父亲都不能是红色）。
 * 对于每个节点，从该点至null（树尾端）的任何路径，都含有相同个数的黑色节点。
 * 结构调整过程包含两个基本操作：左旋（Rotate Left），右旋（RotateRight）。
 * </p>
 * 统计性能更优(综合增删改查的所有的操作)
 */
public class EgdwRedBlackTree<K extends Comparable, V> {

    //标记黑球
    private static final int BLACK = 0;
    //标记红球
    private static final int RED = 1;
    //根节点
    private Node<K, V> root;


    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsValue(Object value) {
        return false;
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public V get(Object key) {
        return null;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new UnsupportedOperationException("key is null!");
        }
        root = addElement(root, key, value, EgdwRedBlackTree.BLACK);
        return value;
    }

    private Node<K, V> addElement(Node<K, V> root, K key, V value, int type) {
        if (root == null) {
            size++;
            return new Node<K, V>(type, key, value);
        }
        int compareResult = key.compareTo(root.key);
        if (compareResult > 0) {
            //如果传入的key值大于当前的节点
            root.rightNode = addElement(root.rightNode, key, value, EgdwRedBlackTree.RED);
        } else if (compareResult < 0) {
            //如果传入的key值小于当前的节点
            root.leftNode = addElement(root.leftNode, key, value, EgdwRedBlackTree.RED);
        } else if (compareResult == 0) {
            //如果相等的话
            //修改为当前的值
            root.value = value;
        }

        if (!isRed(root.leftNode) && isRed(root.rightNode)) {
            //左旋转
            root = leftRotate(root);
        }

        if (isRed(root.leftNode) && isRed(root.leftNode.leftNode)) {
            //右旋转
            root = rightRotate(root);
        }

        if (isRed(root.leftNode) && isRed(root.rightNode)) {
            //颜色翻转
            root = flipColors(root);
        }

        return root;
    }


    /**
     * 判断是否是红色节点
     *
     * @param root 传入的节点
     * @return true红色节点 false黑色节点
     */
    private boolean isRed(Node<K, V> root) {
        if (root == null) {
            return false;
        }
        if (root.type == RED) {
            return true;
        }
        return false;
    }

    /**
     * 颜色翻转
     * 针对2-3树中四节点变换
     *
     * @param node
     */
    private Node<K, V> flipColors(Node<K, V> node) {
        node.type = RED;
        node.leftNode.type = BLACK;
        node.rightNode.type = BLACK;
        return node;
    }

    /**
     * 进行右旋转
     *
     * @param node 需要右旋转的节点
     *             node                  x
     *             /  \      右旋转      / \
     *             x   T2   ------->    y  node
     *             / \                      / \
     *             y  T1                   T1 T2
     */
    private Node<K, V> rightRotate(Node<K, V> node) {
        Node<K, V> x = node.leftNode;
        node.leftNode = x.rightNode;
        x.rightNode = node;
        x.type = node.type;
        node.type = RED;
        return x;
    }

    /**
     * 进行左旋转
     *
     * @param node
     * @return 调整完成之后的node节点
     * <p>
     * node                            x
     * /  \        左旋转             /   \
     * T2   x     --------->         node  y
     * ..../ \                        / \
     * ...T1 y                       T2 T1
     */
    private Node<K, V> leftRotate(Node<K, V> node) {
        Node<K, V> x = node.rightNode;
        node.rightNode = x.leftNode;
        x.leftNode = node;
        x.type = node.type;
        node.type = RED;
        return x;
    }

//    public boolean remove(Object key) {
//        return removeElement(root, (K) key) == null ? false : true;
//    }


//    private Node<K, V> removeElement(Node<K, V> root, K element) {
//        if (root == null) {
//            throw new NoSuchElementException("node not found");
//        }
//        int compareResult = element.compareTo(root.key);
//        if (compareResult > 0) {
//            root.rightNode = removeElement(root.rightNode, element);
//        } else if (compareResult < 0) {
//            root.leftNode = removeElement(root.leftNode, element);
//        } else {
//            //如果相同,进行删除操作
//            //需要分三种情况
//            Node leftNode = root.leftNode;
//            Node rightNode = root.rightNode;
//            //如果没有子node的情况
//            if (leftNode == null && rightNode == null) {
//                root = null;
//                size--;
//                return root;
//            } else if (leftNode == null && rightNode != null) {
//                //如果左孩子没有.右孩子有
//                root.key = ((K) rightNode.key);
//                root.rightNode = (rightNode.rightNode);
//                size--;
//            } else if (leftNode != null && rightNode == null) {
//                //如果左孩子有,右孩子没有
//                root.key = ((K) leftNode.key);
//                root.leftNode = (leftNode.leftNode);
//                size--;
//            } else {
//                //如果左右孩子都有,找到左节点最大的数或者右节点最小的数进行替换
//                K e = (K) findBiggestNode(leftNode).key;
//                //返回左节点最大的数并删除左节点最大的数
////                root.setElement((E) removeBiggestNode(leftNode, null));
//                root.key = (e);
//                root.leftNode = (removeElement(leftNode, e));
//            }
//        }
//        //调整红黑树
//        if (!isRed(root.leftNode) && isRed(root.rightNode)) {
//            root = leftRotate(root);
//        }
//
//        if (isRed(root.leftNode) && isRed(root.leftNode.leftNode)) {
//            root = rightRotate(root);
//        }
//
//        if (isRed(root.leftNode) && isRed(root.rightNode)) {
//            root = flipColors(root);
//        }
//
//        return root;
//
//    }


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

    public void clear() {
        root = null;
        size = 0;
    }


    class Node<K, V> {
        //左孩子
        private Node<K, V> leftNode;
        //右孩子
        private Node<K, V> rightNode;
        //表示当前的颜色(默认为红色)
        private int type = RED;
        //保存当前的key
        private K key;
        //保存当前的value
        private V value;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(int type, K key, V value) {
            this.type = type;
            this.key = key;
            this.value = value;
        }
    }

}
