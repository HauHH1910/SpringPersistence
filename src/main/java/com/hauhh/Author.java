package com.hauhh;

import jakarta.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;

    @Version
    private int version;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private AuthorStatus status;
}
