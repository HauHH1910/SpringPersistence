# How to map a unidirectional one-to-one association

## Problem:

    My table model contains an one-to-one association. I only need to navigate it one direction. 
    How do I model that with Hibernate?

## Solution:

    You only need to model the association on the entity from which you want to navigate the relationship

### Let’s have a look at an example:

    A book gets created from a manuscript. You could model this with a Book and a Manuscript entity and a one-to-one association between them. 
    If you just want to navigate it from the Manuscript to the Book entity, you only need to model it as a one-to-one association on the Manuscript entity.

![img.png](img.png)

    The relationship definition consists of two mandatory and one optional part. 
    The entity attribute Book book and the @OneToOne annotation are required.
    The  attribute models the association, and the annotation declares the kind of relationship.
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
```































