<h1>How to use a database table to generate primary key values</h1>
<hr/>
<h2>Problem:</h2>
<p>
  How do I generate primary key values if my database doesnt support sequences or auto-incremented columns?
</p>
<h2>Solution:</h2>
<p>
    JPA and Hibernate support different strategies to generate primary key values.<br/>
    One of them is the table strategy, which uses a database table to simulate a sequence.<br/>
    This strategy provides a good solution if your database doesn't support sequences and auto-incremented database columns.
</p>
<p>
    If you want to use this strategy, you have to annotate the primary key attribute
with an @Id and a @GeneratedValue annotation with
GenerationType.TABLE as the value of the strategy attribute.
</p>

```java
    import jakarta.persistence.*;

    @Entity
    public class Author {
    
        @Id
        @GeneratedValue(
                strategy = GenerationType.TABLE
        )
        @Column(
                name = "id",
                nullable = false,
                updatable = false
        )
        private Long id;
        
        //Other attributes
    }
```
<p>
    When you persist a new Author entity, Hibernate selects the next primary key value from the <strong>hibernate_sequences</strong> table and updates it afterwards.<br/>
    These two statements create an overhead and lock the <strong>hibernate_sequences</strong> table until you commit the transaction. That makes the <strong>TABLE</strong> strategy slower then the <strong>SEQUENCE</strong> or <strong>IDENTITY</strong> strategy.<br/>
    Hibernate then uses the retrieved primary key value to insert the new Author entity into the Author table.
</p>

