package com.javatechie.crud.example.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for getting redis cache details
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheManager cacheManager;

    private final ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CacheController(final RedisCacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.objectMapper = new ObjectMapper();
//        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     *
     * @return This API returns the all cache available in the app
     */

    @GetMapping("/name")
    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }


    /**
     *
     * @param cacheName Name of the cache
     * @return This API returns the all cached data in JSON for the specific cache
     */

    @GetMapping("/rediscache/{cacheName}")
    public List<Map<String, Object>> getCache(@PathVariable String cacheName) {

        return redisTemplate.keys(cacheName+"*")
                .parallelStream()
                .map(key -> {

                    Map<String, Object> cacheEntries= new HashMap<>();
                    cacheEntries.put(key, getValue(cacheName, key));
                    return cacheEntries;
                }).collect(Collectors.toList());
    }

    public Object getValue(String cacheName, String key) {

        String keyWithoutPrefix = key;
        if(key.contains(cacheName+"::")) {
            keyWithoutPrefix = StringUtils.delete(key,cacheName+"::" );
        }
        else if(key.contains(cacheName)) {
            keyWithoutPrefix = StringUtils.delete(key,cacheName );
        }

        Object object = cacheManager.getCache(cacheName).get(keyWithoutPrefix).get();
        return object;
//        return cacheManager.getCache(cacheName).get(keyWithoutPrefix).toString();

    }
}
