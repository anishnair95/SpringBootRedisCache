package com.javatechie.crud.example.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * Custom cache Store class
 * @param <T> - type of Object
 */
public class CacheStore<T> {

    private Cache<String,T> cache;

    public CacheStore(int expiryDuration, TimeUnit timeUnit, long size) {

        cache = Caffeine.newBuilder()
                .expireAfterWrite(expiryDuration,timeUnit)
                .build();
    }


    //Method to fetch previously stored record using record key
    public T get(String key) {
        return cache.getIfPresent(key);
    }

    //Method to put a new record in Cache Store with record key
    public void add(String key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
            System.out.println("Record stored in "
                    + value.getClass().getSimpleName()
                    + " Cache with Key = " + key);
        }

    }

}
