<h1>How to map a simple primary key</h1>
<hr/>
<h2>Problem:</h2>
<p>
    How do I map a simple primary key of a database with an entity?
</p> 
<h2>Solution:</h2>
<p>
    You can model a simple primary key with an entity attribute that you annotate with an @Id annotation. 
    <br>
    JPA and Hibernate support the following data types as primary keys:
</p>
<ul>
    <li>
        Primitive Java types and their wrapper types
    </li>
    <li>
        java.lang.String
    </li>
    <li>
        java.util.Date and java.sql.Date
    </li>
    <li>
        java.math.BigDecimal
    </li>
    <li>
        java.math.BigInteger
    </li>
</ul>

<p>
    The following code snippet shows an example of a primary key attribute. It is of type java.lang.Long.
    I annotated it with an @Id and a @Column annotation.
    You don’t need the @Column annotation to define a primary key,but it allows you to provide additional mapping information. 
    In this example, I tell Hibernate that the id attribute can’t be null and can’t be updated.
    You could also provide the database column name, if you don’t want to rely on Hibernate’s default mapping.
</p>

```
    @Entity
    public class Author {
        
        @Id
        @Column(nullable = false, updatable = false)
        private Long id;
    }
```