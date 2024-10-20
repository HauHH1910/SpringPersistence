package com.hauhh;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Author {

    @Id
    @Column(nullable = false, updatable = false)
    private Long id;

    @Version
    private int version;

    private String firstName;

    private String lastName;
}
