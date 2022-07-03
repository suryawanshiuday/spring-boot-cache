package com.udays.personal.projects.spring.boot.cache.rest.controller;

import com.udays.personal.projects.spring.boot.cache.rest.model.Book;
import com.udays.personal.projects.spring.boot.cache.rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * service to lookup book by id, first lookup will be slow as data won't be available in cache,
     * subsequent lookups will be from cache and faster
     * @param bookId
     * @return
     */
    @GetMapping("/id/{bookId}")
    public Book getBookByBookId(@PathVariable("bookId") long bookId){
        return bookService.getBookByBookId(bookId);
    }

    /**
     * get list of books with matching title, this always looks up in cache and should be faster all times
     * @param title
     * @return
     */
    @GetMapping("/title/{title}")
    public List<Book> getBookByTitle(@PathVariable("title") String title){
        return bookService.getBooksByTitle(title);
    }

}
