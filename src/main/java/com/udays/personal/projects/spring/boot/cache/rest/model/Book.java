package com.udays.personal.projects.spring.boot.cache.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Getter @Setter private long id;
    @Getter @Setter private String title;
    @Getter @Setter private String author;

}
