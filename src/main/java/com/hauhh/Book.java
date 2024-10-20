package com.hauhh;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Version
    private int version;

    private String title;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

}
