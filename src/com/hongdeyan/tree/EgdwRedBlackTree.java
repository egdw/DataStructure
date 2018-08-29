package com.hongdeyan.tree;

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
            //不进行任何操作
        }


        //比较左右节点和父节点颜色是否相同
        if (root.leftNode != null && root.leftNode.type == root.type) {

        } else if (root.rightNode != null && root.rightNode.type == root.type) {

        } else {
            //如果颜色不相同的话
        }


        return root;
    }

    /**
     * 颜色翻转
     * 针对2-3树中四节点变换
     *
     * @param node
     */
    private void flipColors(Node<K, V> node) {
        node.type = RED;
        node.leftNode.type = BLACK;
        node.rightNode.type = BLACK;
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

    public V remove(Object key) {
        return null;
    }

    public void clear() {

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
