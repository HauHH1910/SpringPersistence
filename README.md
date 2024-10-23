# How to map a bidirectional many-to-one association
## Problem:
    My table model contains a many-to-one association.
    How do I model it with Hibenrate so that I can navigate it in both directions?
## Solution:
    You need to model the association on both entites if you want to be able to navigate it in both directions.
    Consider this exampe:
        A book in an online bookstore can have multiple reviews. 
        In your domain model, the Book entity has a one-to-many association with Review entity, and the Review entity has a many-to-one relationship to the Book entity.
![img.png](img.png)

- Let's begin with Review entity, which is the owning side of the association in this example.
- That means that it defines that assocation and the Book entity just references it. 
- The relationship consists of two mandatory and one optional part.
- The attribute of type Book and the @ManyToOne annotation are required.
- The attribute models the association, and the annotation declares the type of relationship.
- The @JoinColumn annotation is optional. It allows you to define the name of the foreign key column.
- I use it in the following code snippet to set the column name to fk_book.
- If you don’t define the name yourself, Hibernate generates a name by combining
- The name of the association mapping attribute and the name of the primary key attribute of the associated entity.

In this example, Hibernate would use book_id as the default column name.

```java
    import jakarta.persistence.*;

    import java.util.ArrayList;
    
    @Entity
    public class Review {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false, updatable = false, name = "id")
        private Long id;
    
        @ManyToOne
        @JoinColumn(name = "fk_book")
        private Book book;
    
        //Other attribute
    }
    
    @Entity
    public class Book {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false, updatable = false, name = "id")
        private Long id;
    
        @OneToMany(mappedBy = "book")
        private List<Review> reviews = new ArrayList<>();
    
        //Other attribute
    }
```
You need to map the one-to-many association on the Book entity to make it bidirectional

You need an attribute that models the association, which is the List<Review> reviews attribute in this example and a @OneToMany annotation. 

Like in the table model, the bidirectional one-to-many association gets defined on the many side. 

The table on the many side stores the foreign key and its entity defines the association.

It’s similar for the entity mapping. You just need to reference the name of the association attribute of the many side as the value of the mappedBy
attribute and Hibernate has all the information it needs.

>That’s all you need to do to define a bidirectional many-to-one association.

Bidirectional associations are easy to use in queries, but they also require an additional step when you persist a new entity.
You need to update the association on both sides when you add or remove an entity.

You can see an example of it in the following code snippet, in which I first create a new Review entity and initialize its association to the Book entity.
And after that, I also need to add the new Review entity to the List of reviews on the Book entity.

```
    Book b = em.find(Book.class, 1L);
    
    Review r = new Review();
    r.setComment("This is a comment");
    r.setBook(b);
    
    b.getReviews().add(r);
    
    em.persist(r);

```

Updating the associations on both entities is an error-prone task.

Therefore, it’s a good practice to provide a helper method that adds another entity to the many side of the association

```java
    import jakarta.persistence.*;

    import java.util.ArrayList;
    
    @Entity
    public class Review {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false, updatable = false, name = "id")
        private Long id;
    
        @ManyToOne
        @JoinColumn(name = "fk_book")
        private Book book;
    
        //Other attribute
    }
    
    @Entity
    public class Book {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false, updatable = false, name = "id")
        private Long id;
    
        @OneToMany(mappedBy = "book")
        private List<Review> reviews = new ArrayList<>();
    
        //Other attribute
        
        //Helper method
        public void addReview(Review review){
            this.reviews.add(review);
            review.setBook(this);
        }
    }
```
