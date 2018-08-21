package com.hongdeyan.map;

/**
 * 基于链表的map映射
 * 性能差只是做练习使用.
 * @author egdw
 */
public class EgdwLinkedMap<K, V> implements EgdwMap<K, V> {

    private int size;

    private int modCount;

    private Node<K, V> firstNode;

    private Node<K, V> tailNode;

    class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> preNode;
        Node<K, V> nextNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node<K, V> preNode) {
            this.key = key;
            this.value = value;
            this.preNode = preNode;
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

        public Node<K, V> getPreNode() {
            return preNode;
        }

        public void setPreNode(Node<K, V> preNode) {
            this.preNode = preNode;
        }

        public Node<K, V> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<K, V> nextNode) {
            this.nextNode = nextNode;
        }
    }

    public EgdwLinkedMap() {
        firstNode = new Node<>(null, null);
        tailNode = new Node<>(null, null);
        tailNode.setPreNode(firstNode);
        firstNode.setNextNode(tailNode);
    }

    @Override
    public boolean add(K key, V value) {
        Node<K, V> node = new Node<K, V>(key, value);
        Node<K, V> preNode = tailNode.getPreNode();
        preNode.setNextNode(node);
        tailNode.setPreNode(node);
        node.setNextNode(tailNode);
        node.setPreNode(preNode);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean isExsits(K key) {
        Node<K, V> temp = firstNode.getNextNode();
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                return true;
            }
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override
    public boolean set(K key, V newValue) {
        Node<K, V> temp = firstNode.getNextNode();
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                temp.setValue(newValue);
                modCount++;
                return true;
            }
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override
    public boolean remove(K key) {
        Node<K, V> temp = firstNode.getNextNode();
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                Node<K, V> tempNextNode = temp.getNextNode();
                if (tempNextNode == null) {
                    temp.getPreNode().setNextNode(null);
                } else {
                    temp.getPreNode().setNextNode(tempNextNode);
                    tempNextNode.setPreNode(temp.getPreNode());
                }
                size--;
                modCount++;
                return true;
            }
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override
    public V get(K key) {
        Node<K, V> temp = firstNode.getNextNode();
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                return temp.getValue();
            }
            temp = temp.getNextNode();
        }
        return null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "EgdwLinkedMap{" +
                "size=" + size +
                ", firstNode=" + firstNode +
                ", tailNode=" + tailNode +
                '}';
    }
}
