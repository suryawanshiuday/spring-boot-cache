# spring-boot-cache
This is Spring Boot application using built in caching mechanism. 
We can integration other caching technologies with spring boot like H2/Eh/HazelCast/Redis but this is not covered in this project.

Upon running the application on localhost:8080, you will find 2 set of REST services

/book

/cache

Example usage:

/book/id/1 - lookup from slow source and populating the data in cache "books", subsequent lookups are faster from cache directly

/book/title/learn - returns all books from cache containing title "learn"

/cache/evict/all - evict all caches

/cache/evict/books - evict cache with name "books"

/cache/evict/books/1 - evict book with key as 1 from cache object "books"
