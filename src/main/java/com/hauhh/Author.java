package com.hauhh;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Version
    private int version;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
}
