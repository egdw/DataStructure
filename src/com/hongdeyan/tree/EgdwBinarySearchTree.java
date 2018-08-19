package com.hongdeyan.tree;

/**
 * 二分搜索树
 * 二分搜索树不会包含相同的元素
 * 元素必须是可以比较大小的
 *
 * @author egdw
 */
public class EgdwBinarySearchTree<E extends Comparable> {


    private class Node<E extends Comparable> {
        E element;
        Node leftNode;
        Node rightNode;

        public Node(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    //root
    private Node<E> root;
    //root size
    private int size;

    public EgdwBinarySearchTree() {
    }


    /**
     * 第一种添加方法
     *
     * @param element
     */
//    public void add(E element) {
//        if (root == null) {
//            //初始化
//            root = new Node<E>(element);
//        } else {
//            nodeAdd(root, element);
//        }
//    }
//
//
//    private void nodeAdd(Node<E> root, E element) {
//        if (root.element.equals(element)) {
//            //如果元素相等的话基于不重复的原则不进行添加
//            return;
//        }
//        int rootCompareResult = element.compareTo(root.element);
//        if (rootCompareResult > 0 && root.getRightNode() == null) {
//            //说明比他大
//            root.setRightNode(new Node(element));
//            size++;
//            return;
//        } else if (rootCompareResult < 0 && root.getLeftNode() == null) {
//            root.setLeftNode(new Node(element));
//            size++;
//            return;
//        }
//        //进行递归操作
//        if (rootCompareResult > 0) {
//            //大于
//            nodeAdd(root.getRightNode(), element);
//        } else if (rootCompareResult < 0) {
//            //小于
//            nodeAdd(root.getLeftNode(), element);
//        }
//
//    }

    /**
     * 第二种添加方法
     *
     * @param element
     */
    public void add(E element) {
        root = nodeAdd(root, element);
    }


    private Node<E> nodeAdd(Node<E> root, E element) {

        if (root == null) {
            size++;
            return new Node<E>(element);
        }

        int rootCompareResult = root.getElement().compareTo(element);
        if (rootCompareResult > 0) {
            root.setLeftNode(nodeAdd(root.getLeftNode(), element));
        } else if (rootCompareResult < 0) {
            root.setRightNode(nodeAdd(root.getRightNode(), element));
        }
        return root;
    }


    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        Node<E> node = nodeFind(root, element);
        return node == null ? false : true;
    }


    private Node<E> nodeFind(Node<E> root, E element) {
        if (root == null || root.getElement() == null) {
            return null;
        }
        int compareResult = element.compareTo(root.getElement());
        if (compareResult > 0) {
            return nodeFind(root.getRightNode(), element);
        } else if (compareResult < 0) {
            return nodeFind(root.getLeftNode(), element);
        } else {
            //如果相同
            return root;
        }
    }


    /**
     * 移除某个元素
     *
     * @param element
     * @return 是否完成
     * 删除算法可能有问题.不能保证可靠性
     */
    public boolean remove(E element) {
        return nodeRemove(root, element);
    }

    private boolean nodeRemove(Node<E> root, E element) {
        if (root == null) {
            return false;
        }
        int compareResult = element.compareTo(root.getElement());
        if (compareResult > 0) {
            return nodeRemove(root.getRightNode(), element);
        } else if (compareResult < 0) {
            return nodeRemove(root.getLeftNode(), element);
        } else {
            //如果相同,进行删除操作
            if (root.getRightNode() != null) {
                //如果左孩子不等于空,那么替换当前自己的位置
                Node rightNode = root.getRightNode();
                root.setElement((E) rightNode.getElement());
                root.setRightNode(rightNode.getRightNode());
                Node leftLastChildNode = getLeftLastChildNode(rightNode);
                if (leftLastChildNode != null) {
                    leftLastChildNode.setLeftNode(root.getLeftNode());
                    root.setLeftNode(leftLastChildNode);
                }
            } else if (root.getLeftNode() != null) {
                Node leftNode = root.getLeftNode();
                root.setElement((E) leftNode.getElement());
                root.setLeftNode(leftNode.getLeftNode());
                root.setRightNode(leftNode.getRightNode());
            } else {
                //如果两者都为null的话
                root.element = null;
                root.rightNode = null;
                root.leftNode = null;
            }
            return true;
        }
    }

    /**
     * 找到某个Node左海子最下的那个分支.
     *
     * @param root
     * @return
     */
    private Node<E> getLeftLastChildNode(Node<E> root) {
        if (root != null) {
            if (root.getLeftNode() == null) {
                return root;
            }
            return getLeftLastChildNode(root.getLeftNode());
        }
        return null;
    }

    public int size() {
        return size;
    }

    /**
     * 获取根节点
     *
     * @return 根节点
     */
    public E getRoot() {
        if (root == null) {
            return null;
        }
        return root.getElement();
    }


    /**
     * 前序遍历
     */
    public void foreach() {
        preForeach(root);
    }


    private void preForeach(Node<E> root) {
        //首先先判断当前的根节点是否为null
        if (root == null) {
            return;
        }

        System.out.println(root);

        //递归打印左右节点
        preForeach(root.getLeftNode());
        preForeach(root.getRightNode());
    }
}
