package com.udays.personal.projects.spring.boot.cache.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.management.timer.Timer;

@Service
/**
 * instead of using @CacheEvict annotations, we can use CacheManager instance to evict cache with more flexibility/readability
 */
public class CachingService {

    @Autowired
    CacheManager cacheManager;

    /**
     * validate if cacheName passed is infact a valid cache
     * @param cacheName
     * @return
     */
    public boolean isValidCache(String cacheName){
        return cacheManager.getCacheNames().contains(cacheName);
    }

    /**
     * Evict entry in books cache by bookId
     * @param bookId
     */
    public void evictBookById(long bookId){
        cacheManager.getCache("books").evict(bookId);
    }

    /**
     * scheduled eviction of books cache, clearing all entries
     * NOTE: for this to work, @EnableScheduling is important in Application class
     * we can either use @CacheEvict or cacheManager.getCache.clear
     */
    @Scheduled(fixedRate = 5*Timer.ONE_SECOND)
    //@CacheEvict(value = "books", allEntries = true)
    public void ttlEvictBooksCache(){
        System.out.println("Evicting books cache");
        cacheManager.getCache("books").clear();
    }

    /**
     * evict the cache by passed cacheName
     * @param cacheName
     */
    public void evictCacheByName(String cacheName){
        cacheManager.getCache(cacheName).clear();
    }

    /**
     * evict all caches
     */
    public void evictAllCaches(){
        cacheManager.getCacheNames().stream().forEach(each-> cacheManager.getCache(each).clear());
    }

}
