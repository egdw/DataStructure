package com.hongdeyan.union;

public interface EgdwUnionFIndImpl<E> {
    boolean isConnected(E p, E q);

    void unionElement(E p,E q);

    int size();
}
