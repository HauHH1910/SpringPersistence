# How to map a view with Hibernate

## Problem

      I have a read-only view that I want to use in associations and JPQL queries
      How do I map it with Hibernate?

## Solution

    Database views, in general, are mapped in the same way as database tables.
    You just have to define an entity that maps the view with the specific name and one or more of its columns.

    But the normal table mapping is not read-only, and you can use the entity to change  its content.

    Depending on the database you use and the definition of the view, you're not allowed to perform an update on the view content.
    You should therefore also prevent Hibernate from updating it.

> You can easily achieve that by annotating your entity with Hibernate's @Immutable annotation.

```java

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
public class BookView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    @Column(name = "title")
    private String title;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date publishingDate;
}
```

> As a result, Hibernate performs as SQL SELECT statement to read the entity, but it does not perform any UPDATE statements when you change an attribute.