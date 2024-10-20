package com.hauhh;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
}
