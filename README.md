# How to map a bidirectional one-to-one association

## Problem:

    My table model contains a one-to-one association. 
    How do I model it with Hibernate so that I can navigate it in both directions?

## Solution:

    You need to model the association on both entities if you want to be able to navigate it in both directions

### Let’s have a look at an example:

    A book gets created from a manuscript. 
    You could model this with a Book and a Manuscript entity and a one-to-one association between them. 
    You need to model that with a one-to-one association on the Book entity and the Manuscript entity.

![img.png](img.png)

    Let's begin with the ManuScript entity, which of the owning side of the association in this example.
    That means that it defines the relationship and the Book entity just references it.

    The relationship definition consists of two mandatory and one optional part. 
    The entity attribute Book book and the @OneToOne annotation are required. 
    The attribute models the association, and the annotation declares the kind of relationship. 
        The @JoinColumn annotation is optional. 
        It allows you to define the name of the foreign key column that links the book to the manuscript.
    I use it in the following code snippet to set the name of the foreign key column to 'fk_book'.

    If you don’t define the name yourself, Hibernate generates a name by combining the name of the association mapping attribute and the name of the primary key attribute of the associated entity.

```java

import jakarta.persistence.*;

@Entity
public class ManuScript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fk_book")
    private Book book;

}

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(mappedBy = "book")
    private ManuScript manuscript;

}
```

You also need to map the one-to-one association on the Book entity to make it bidirectional


































