<h1>How to use a custom database sequence to generate primary key values</h1>
<hr/>
<h2>Problem:</h2>
<p>
  Hibernate uses its default database sequence to generate primary key values. How to I use my own sequence?
</p> 
<h2>Solution:</h2>
<p>
    The JPA specification supports four options to generate primary key values. One of them is the <strong>GenerationType.SEQUENCE</strong>, which uses a database sequence to generate primary key values
</p> 
<p>
    When you want to use a custom database sequence, you have to annotate the primary key attribute with the @GeneratedValue annotation and set <strong>GenerationType.SEQUENCE</strong> as the value of the strategy attribute.
    This tells Hibernate to use a database sequence to generate the primary key value. 
    If you don’t provide any additional information, Hibernate uses its default sequence, hibernate_sequence.
</p>

<p>
    You can configure the name and schema of a custom database sequence using a
    @SequenceGenerator annotation. The following code snippet shows an
    example of such a mapping. The @GeneratedValue annotation references a
    custom generator with the name author_generator. This generator gets
    defined by the @SequenceGenerator annotation, which tells Hibernate to
    use the author_seq database sequence.
</p>

```
    @Entity
    public class Author {
        
        @Id
        @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_generator"
        )
        @SequenceGenerator(
            name = "author_generator"
        )
        @Column(
            name = "id",
            updatable = false,
            nullable = false
        )
        private Long id;
    }
```
<p>
    When you persist a new Author entity, Hibernate selects a new primary key value from the database sequence <strong>author_seq</strong> before it executes the SQL INSERT statement.
</p>