# How to map an unidirectional many-to-one association

## Problem

    My table model contains a many-to-one association.
    I only want to model it on the many side. How do I do it with Hibernate?

# Solution

    Using an unidirectional many-to-one association is typical approach for associations that contain a lot of entities on the many side of the relationship. 
    It allows you to navigate it in the to-one direction but avoids performance issues that might occur if Hibernate has to load a huge number of entities to initialize the many side of the association
    
![img.png](img.png)

    You model the association only on the entities of the many side. 
    Let's have a look at an example.
        A book in an online bookstore can have multiple reviews.
        In your domain model, you only model the many-to-one association on the Review entity.
    The association consists of two mandatory and one optional part. 
    The entity attribute of type Book and the @ManyToOne annotation are required.
    The attribute models the association, and the annotation declares the lind of relationship
        The @JoinColumn annotation is optional. It allows you to define the name of the foreign key column

```java

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_book")
    private Book book;
}

@Entity
public class Book{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    //Other attribute
}
```

That's all you need to do to model a unidirectional many-to-one association.
Like in the table model, the unidirectional many-to-to association gets defined on the many sode.
The table on the many side stores the foreign key and its entity defines the association.




