package com.hauhh;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Version
    private int version;

    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public void addBook(Book newBook) {
        this.books.add(newBook);
        newBook.getAuthors()
                .add(this);
    }

    public void removeBook(Book oldBook) {
        this.books.remove(oldBook);
        oldBook.getAuthors()
                .remove(this);
    }
}
