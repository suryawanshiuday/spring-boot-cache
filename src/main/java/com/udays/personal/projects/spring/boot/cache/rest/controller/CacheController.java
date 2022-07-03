package com.udays.personal.projects.spring.boot.cache.rest.controller;

import com.udays.personal.projects.spring.boot.cache.rest.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller to provide on demand cache eviction
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    CachingService cachingService;

    /**
     * evict the book from cache based on bookId
     * @param bookId
     */
    @GetMapping("/evict/books/{bookId}")
    public void evictBookFromCacheById(@PathVariable("bookId") long bookId){
        cachingService.evictBookById(bookId);
    }

    /**
     * evict the provided cache, validates if the cacheName is indeed valid cached object
     * we can throw appropriate response in else, just used log for simplicity
     * @param cacheName
     */
    @GetMapping("/evict/{cacheName}")
    public void evictCacheByCacheName(@PathVariable("cacheName") String cacheName){
        if(cachingService.isValidCache(cacheName)){
            cachingService.evictCacheByName(cacheName);
        } else{
            System.out.println("Invalid cache name: "+cacheName);
        }
    }

    /**
     * evict all caches
     */
    @GetMapping("/evict/all")
    public void evictAllCaches(){
        cachingService.evictAllCaches();
    }

}
