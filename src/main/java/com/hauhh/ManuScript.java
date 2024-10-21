package com.hauhh;

import jakarta.persistence.*;

@Entity
public class ManuScript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "fk_book")
    private Book book;
}
