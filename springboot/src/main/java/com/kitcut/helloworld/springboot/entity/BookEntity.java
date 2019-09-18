package com.kitcut.helloworld.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity(name = "book")
@Getter
@Setter
public class BookEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "release_date")
    private String releaseDate;

}
