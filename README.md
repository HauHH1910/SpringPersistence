<h1> How to define schema and table names?</h1>
<h2>Problem:</h2>
<p>How do I define the name of the database schema and table to which Hibernate maps an entity?</p>
<h2>Solution:</h2>
<ul>
    <li>
    You can define the schema and table name with the <strong>schema</strong> and <strong>name</strong> attributes of the <u>javax.persistence.Table</u> annotation. <br/>
    </li>
    <li>
    You just have to add the @Tableannotation to your entity class and set the name and schema attributes.
    </li>
</ul>
<h2>Example</h2>

```
    @Entity
    @Table(name = "author", schema = "bookstore")
    public class Author {...}
```