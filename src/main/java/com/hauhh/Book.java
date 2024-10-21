package com.hauhh;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "fk_book"),
            inverseJoinColumns = @JoinColumn(name = "fk_author")
    )
    private List<Author> authors = new ArrayList<>();
}
