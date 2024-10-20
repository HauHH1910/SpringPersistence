package com.hauhh;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Version
    private int version;

    @ManyToOne
    @JoinColumn(name = "fk_book")
    private Book book;
}
