package com.hongdeyan.list;

import java.util.NoSuchElementException;

/**
 * 单链表
 *
 */
public class EgdwSingleLinkedList<E> implements EgdwListImpl<E> {

    private Node node;
    private int size;

    @Override
    public boolean add(E e) {
        if (node == null) {
            node = new Node(e);
        } else {
            node = new Node(e, this.node);
        }
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (node == null) {
            throw new NoSuchElementException("not found any element!");
        }
        E element = node.getElement();
        this.node = node.preNode;
        size--;
        return element;
    }

    @Override
    public boolean remove() {
        this.node = node.preNode;
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getFirst() {
        return node.getElement();
    }

    @Override
    public E getLast() {
        Node tempNode = node.preNode;
        E element = null;
        while (tempNode != null) {
            element = tempNode.element;
            tempNode = tempNode.preNode;
        }
        return element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 单链表不需要
     *
     * @param e 餐厨
     * @return 是否完成
     */
    @Override
    public boolean delete(E e) {
        Node preNode = this.node;
        Node tempNode = node.preNode;
        while (tempNode != null) {
            if (tempNode.element.equals(e)) {
                preNode.preNode = tempNode.preNode;
                size--;
                return true;
            }
            preNode = tempNode;
            tempNode = tempNode.preNode;
        }
        return false;
    }


    class Node {
        private E element;

        private Node preNode;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node preNode) {
            this.element = element;
            this.preNode = preNode;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node getPreNode() {
            return preNode;
        }

        public void setPreNode(Node preNode) {
            this.preNode = preNode;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(node.element + " => ");
        Node temp = node.preNode;
        while (temp != null) {
            sb.append(temp.element + "=> ");
            temp = temp.preNode;
        }
        return "Node{" +
                sb.toString() +
                '}';
    }
}
