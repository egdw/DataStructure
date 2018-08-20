package com.hongdeyan.tree;

import com.hongdeyan.queue.EgdwLinkedQueue;

import java.util.NoSuchElementException;

/**
 * 二分搜索树
 * 二分搜索树不会包含相同的元素
 * 元素必须是可以比较大小的且实现Comparable接口
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


    /**
     * 查找某个节点下的某个元素
     *
     * @param root
     * @param element
     * @return
     */
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
    public E remove(E element) {
        Node<E> eNode = nodeRemove(root, element);
        E e = eNode.getElement();
        return e;
    }

    private Node<E> nodeRemove(Node<E> root, E element) {
        if (root == null) {
            throw new NoSuchElementException("node not found");
        }
        int compareResult = element.compareTo(root.getElement());
        if (compareResult > 0) {
            root.rightNode = nodeRemove(root.getRightNode(), element);
            return root;
        } else if (compareResult < 0) {
            root.leftNode = nodeRemove(root.getLeftNode(), element);
            return root;
        } else {
            //如果相同,进行删除操作
            //需要分三种情况
            Node leftNode = root.getLeftNode();
            Node rightNode = root.getRightNode();
            //如果没有子node的情况
            if (leftNode == null && rightNode == null) {
                root = null;
                System.out.println("删除");

                size--;
            } else if (leftNode == null && rightNode != null) {
                //如果左孩子没有.右孩子有
                root.setElement((E) rightNode.getElement());
                root.setRightNode(rightNode.getRightNode());
                System.out.println("删除");

                size--;
            } else if (leftNode != null && rightNode == null) {
                //如果左孩子有,右孩子没有
                root.setElement((E) leftNode.getElement());
                root.setLeftNode(leftNode.getLeftNode());
                System.out.println("删除");

                size--;
            } else {
                //如果左右孩子都有,找到左节点最大的数或者右节点最小的数进行替换
                E e = (E) findBiggestNode(leftNode).getElement();
                System.out.println("biggest:" + e);
                System.out.println("currentRoot:" + root.getElement());
                //返回左节点最大的数并删除左节点最大的数
//                root.setElement((E) removeBiggestNode(leftNode, null));
                root.setElement(e);
                root.setLeftNode(nodeRemove(leftNode, e));
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
    private Node<E> findBiggestNode(Node<E> root) {
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
    private E removeBiggestNode(Node<E> root, Node<E> preNode) {
        if (root == null) {
            throw new UnsatisfiedLinkError("not found node");
        }
        if (root.rightNode != null) {
            return (E) removeBiggestNode(root.rightNode, root);
        } else {
            //说明root.rightNode == null 了
            //删除
            preNode.setRightNode(null);
            size--;
            return root.getElement();
        }
    }


    /**
     * 找到某个Node左孩子最下的那个分支.
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

    /**
     * 返回当前的size
     *
     * @return
     */
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


    public void foreachPre() {
        preForeach(root);
    }

    public void foreachCenter() {
        centerForeach(root);
    }

    public void foreachBehind() {
        behindForeach(root);
    }

    public void foreachLevel() {
        levelForeach(root);
    }

    /**
     * 前序遍历(深度优先遍历)
     */
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

    /**
     * 中序遍历(深度优先遍历)
     *
     * @param root
     */
    private void centerForeach(Node<E> root) {
        if (root == null) {
            return;
        }
        centerForeach(root.getLeftNode());
        System.out.println(root);
        centerForeach(root.getRightNode());
    }


    /**
     * 后续遍历(深度优先遍历)
     *
     * @param root
     */
    private void behindForeach(Node<E> root) {
        if (root == null) {
            return;
        }
        behindForeach(root.getLeftNode());
        behindForeach(root.getRightNode());
        System.out.println(root);
    }

    /**
     * 层序遍历(广度优先遍历)
     * 通过使用队列的形式实现
     *
     * @param root
     */
    private void levelForeach(Node<E> root) {
        //需要使用队列完成操作.这里使用我自己定义的基于链表的队列
        //当然这里也可以使用java自带的队列完成操作
        EgdwLinkedQueue<Node> queue = new EgdwLinkedQueue<>();
        queue.add(root);
        while (queue.size() != 0) {
            Node node = queue.poll();
            System.out.println(node.getElement());
            if (node.leftNode != null) {
                queue.add(node.leftNode);
            }
            if (node.rightNode != null) {
                queue.add(node.rightNode);
            }
        }
    }

    /**
     * 清空二叉树
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }
}
