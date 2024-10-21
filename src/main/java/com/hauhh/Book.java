package com.hauhh;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String title;

    @OneToOne(mappedBy = "book")
    private ManuScript manuScript;
}
