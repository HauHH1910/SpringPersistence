# How to map a unidirectional many-to-many association

## Problem:

    My table model contains a many-to-many association. I only need to navigate it in one direction. 
    How do I model that with Hibernate?


## Solution:
    
    You only need to model the association on the entity from which you want to navigate the relationship

Let's have a look at example: 
    
    Multiple Authors can write multiple books, and a book can be written by one or more authors. That is typical many-to-many association. 
    If you only want to navigate it from the Book to the Author entities, you only need to model it as a many-to-many association on the Book entity.

![img.png](img.png)

    The relationship definition consists of two mandatory and one optional part. 
    The entity attribute List<Author> authors and the @ManyToMany annotation are required.
    The attribute models the association, and the annotation declares the kind of relationship. 
        The @JoinTable annotation is optional.
        It allows you to define the name of the join table and foreign key columns that store the many-to-many association. 
        I use it in the following code snippet to set the name of the join table to book_author and the names of the foreign key columns to fk_book and fk_author.
    If you don’t define the names yourself, Hibernate generates default table and column names.

```java
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "fk_book"),
            inverseJoinColumns = @JoinColumn(name = "fk_author")
    )
    private List<Author> authors = new ArrayList<>();
}

```






















