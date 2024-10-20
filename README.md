# How to use a generated UUID as a primary key?
## Problem:
    I want to use UUID as a primary key. How do I map them with Hibernate? Can Hibernate generate UUID values for new entities?
## Solution:

```java
    import jakarta.persistence.*;

    import java.util.UUID;

    @Entity
    public class Author {
    
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id", nullable = false, updatable = false)
        private UUID id;
}
```