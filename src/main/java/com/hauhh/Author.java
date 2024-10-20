package com.hauhh;

import jakarta.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_generator"
    )
    @SequenceGenerator(
            name = "author_generator",
            sequenceName = "author_seq"
    )
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Version
    private int version;

    private String firstName;

    private String lastName;
}
