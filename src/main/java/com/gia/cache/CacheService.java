package com.gia.cache;

/**
 * Created by Fenglin on 2017/5/13.
 */
public interface CacheService<K, V> {

     V get(K key) throws  Exception;

    void set(K key, V val) throws  Exception;

    void delete(K key) throws  Exception;

    boolean exist(K key) throws  Exception;

    V setIfAbsent(K key, V val) throws  Exception;
}
