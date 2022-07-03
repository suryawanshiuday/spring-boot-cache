package com.udays.personal.projects.spring.boot.cache.rest.service;

import com.udays.personal.projects.spring.boot.cache.rest.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    ConcurrentMapCacheManager concurrentMapCacheManager;

    /**
     * If bookId does not exist in "books" cache, it executes fetchFromSlowSource method and the returned value is inserted in "books" cache
     * Note: if there are multiple methods which lookup book by different ways and we want to put them in same "books" cache,
     *       we can either add @Cacheable annotation to all methods or we can add that at the class level
     *       Adding to class level, will add all returned objects from all methods. we might not want to do that for some methods like getBooksByTitle below
     *       (getBooksByTitle just queries cache for matching titles and does not update cache)
     * @param bookId
     * @return
     */
    @Cacheable("books")
    public Book getBookByBookId(long bookId){
        return fetchFromSlowSource(bookId);
    }

    /**
     * lookup from slow source where sleep timer is added to simulate slow response
     * @param bookId
     * @return
     */
    private Book fetchFromSlowSource(long bookId){
        System.out.println("Looking up id in slow source");
        try{
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException){
            System.out.println("Interrupted sleep");
        }
        return new Book(bookId,"Learn Book"+bookId,"Author"+bookId);
    }

    /**
     * Lookup in "books" cache for matching titles
     * This method does not update cache and hence no cacheable annotation
     * If class level cacheable annotation is used, this method will insert data in cache with key as title and value as List<Book>
     * @param title
     * @return
     */
    public List<Book> getBooksByTitle(String title){
        Cache booksCache = concurrentMapCacheManager.getCache("books");
        ConcurrentMap<Long, Book> booksMap = (ConcurrentMap<Long, Book>) booksCache.getNativeCache();
        List<Book> matched = booksMap.values().stream().filter(each->each.getTitle().contains(title)).collect(Collectors.toList());
        return matched;
    }


}
