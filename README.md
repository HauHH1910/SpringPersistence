<h1>How to map an enum to database column</h1>
<hr/>
<h2>Problem:</h2>
<p>
    How do I map an enum attribute to a database column? Which option should I
choose?
</p>
<hr/>
<h2>Solution:</h2>
<p>
    JPA and Hibernate provide two standard options to map an enum to a database column. You can either use the String representation or the ordinal value.
</p>
<p>
    Both approaches have their drawbacks:
</p>
<ul>
    <li>
        The <strong>String</strong> representation is verbose, and renaming an enum value requires that you also update your database.
    </li>
    <li>
    The <strong>Ordinal</strong> of an enum value is its position in the enum declaration. This
    value changes and requires you to update your database when you remove an
    existing value or do not add new values to the end of the enum declaration    
    </li>
</ul>
<p>
    You have to decide which drawback is the lesser evil for your specific
application or use an <a href="https://thorben-janssen.com/hibernate-enum-mappings/#:~:text=But%20before%20you,this%20entity%20attribute." target="_blank">AttributeConverter</a> to define a custom mapping to
avoid these issues.
</p>
<p>
    When you use the JPA and Hibernate standard mapping, you can either rely on
the default mapping of its ordinal value or specify the mapping approach. You
can do that with an @Enumerated annotation.
</p>
<p>
    The following examples use the AuthorStatus enum. This enum indicates if
an author published a book with a publisher, self-published, or is still writing a
book
</p>

```
    public enum AuthorStatus {
        PUBLISHED, SELF_PUBLISHED, NOT_PUBLISHED;
    }
```

<p>
    The following example mapping uses the @Enumerated annotation to
explicitly tell Hibernate to use the ordinal value. If you don’t provide an
@Enumerated annotation or don’t set an EnumType as its value, Hibernate
also uses the ordinal value as the default mapping.
</p>

```
    @Entity
    public class Author {
        
        @Enumerated(EnumType.ORDINAL)
        private AuthorStatus status;
        
        ...
    }
```

<p>
    When you use this mapping to persist an <strong>Author</strong> entity with status <strong>PUBLISHED</strong>, Hibernate stores the value 0 in the database.
</p>

<p>
    If you want to store the <strong>String</strong> representation of the enum value in the database, you need to annotate the entity attributes with <strong>@Enumerated</strong> and set <strong>EnumType.String</strong> as its value.
</p>

```
    @Entity
    public class Author {
        
        @Enumerated(EnumType.STRING)
        private AuthorStatus status;
        
        ...
    }
```

<p>
    When you now persist the same entity in the database, Hibernate writes the value
PUBLISHED into the database column status.
</p>


<h2>References:</h2>
<ul>
    <li>
        <a href="https://thorben-janssen.com/hibernate-enum-mappings/" target="_blank">Enum Mappings with Hibernate</a>
    </li>
</ul>

