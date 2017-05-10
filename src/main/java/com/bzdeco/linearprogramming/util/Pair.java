package com.bzdeco.linearprogramming.util;

/**
 * Created by bzdeco on 10.05.17.
 */
public class Pair<K, V extends Comparable<? super V>> implements Comparable<Pair<K, V>> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int compareTo(Pair<K, V> other) {
        return this.value.compareTo(other.value);
    }
}
