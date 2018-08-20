package com.hongdeyan.map;

public interface EgdwMap<K, V> {
    public boolean add(K key, V value);

    public boolean isExsits(K key);

    public boolean set(K key, V newValue);

    public boolean remove(K key);

    public V get(K key);
}
