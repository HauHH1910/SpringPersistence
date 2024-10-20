<h1>How to use an auto-incremented column to generate primary key values</h1>
<hr/>
<h2>Problem</h2>
<p>
  How do I use an auto-incremented database column to generate primary key values?
</p> 
<h2>Solution</h2>
<p>
  JPA and Hibernate support different strategies to generate primary key values.
One of them is the identity strategy that uses an auto-incremented database
column.
</p> 
<p>
    If you want to use this strategy, you have to annotate the primary key attribute
with an @Id and a @GeneratedValue annotation with
GenerationType.IDENTITY as the value of the strategy attribute
</p>
<p>
    The following code snippet shows an example of this annotation.
</p>

```
    @Entity
    public class Author {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        private Long id;
        ...
    }
```
<p>
    If you persist a new Author entity, Hibernate performs the SQL INSERT
statement immediately. It uses the auto-incremented database column id to
generate the primary key value and retrieves the value from the database.
</p>

