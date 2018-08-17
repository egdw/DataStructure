package com.hongdeyan.list;

/**
 * 双线链表
 *
 * @author egdw
 */
public class EgdwDoubleLinkedList<E> {

    private Node<E> firstNode;

    private Node<E> tailNode;

    private int size;

    public EgdwDoubleLinkedList() {
        //初始化
        firstNode = new Node<E>(null, null, null);
        tailNode = new Node<E>(firstNode, null, null);
        firstNode.setNext(tailNode);
    }


    public void add(E element) {
        Node<E> eNode = new Node<>(null, null, element);
        tailNode.pre.next = eNode;
        eNode.next = tailNode;
        eNode.pre = tailNode.pre;
        tailNode.pre = eNode;
        size++;
    }


    public E pull() {
        E e = firstNode.next.element;
        firstNode.next = firstNode.next.next;
        firstNode.next.pre = firstNode;
        size--;
        return e;
    }


    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean insert(E element, int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        Node nextNode = firstNode;
        for (int i = 0; i <= index; i++) {
            nextNode = nextNode.next;
        }
        Node<E> newNode = new Node<>(null, null, element);
        nextNode.pre.next = newNode;
        newNode.pre = nextNode.pre;
        newNode.next = nextNode;
        return true;
    }


    public boolean set(E element, int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        Node nextNode = firstNode;
        for (int i = 0; i <= index; i++) {
            nextNode = nextNode.next;
        }
        nextNode.element = element;
        return true;
    }


    public E get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        Node nextNode = firstNode;
        for (int i = 0; i <= index; i++) {
            nextNode = nextNode.next;
        }
        return (E) nextNode.element;
    }

    public boolean delete(E element) {
        Node nextNode = firstNode;
        for (int i = 0; i <= size; i++) {
            nextNode = nextNode.next;
            if (nextNode.element.equals(element)) {
                //如果找到了
                nextNode.pre.next = nextNode.next;
                return true;
            }
        }
        return false;
    }


    public boolean removeFirst(){
        return deleteByIndex(0);
    }

    private  boolean deleteByIndex(int index) {
        Node nextNode = firstNode;
        for (int i = 0; i <= index; i++) {
            nextNode = nextNode.next;
        }
        nextNode.pre.next = nextNode.next;
        return true;
    }


    public int size() {
        return size;
    }

    private class Node<E> {
        private Node<E> pre;
        private Node<E> next;
        private E element;

        public Node(E element) {
            this.element = element;
        }

        public Node(Node<E> pre, Node<E> next, E element) {
            this.pre = pre;
            this.next = next;
            this.element = element;
        }

        public Node<E> getPre() {
            return pre;
        }

        public void setPre(Node<E> pre) {
            this.pre = pre;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node next = firstNode.next;
        for (int i = 0; i < size; i++) {
            Object element = next.getElement();
            if (size - i == 1) {
                sb.append(element);
            } else {
                sb.append(element).append(" => ");
            }
            next = next.next;
        }
        return "EgdwDoubleLinkedList{" +
                sb.toString() +
                ", size=" + size +
                '}';
    }
}
