package com.hauhh;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "fk_book")
    private Book book;
}
