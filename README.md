<h1>How to map basic entity attributes to database columns?</h1>
<hr>
<h2>Problem:</h2>
<p>
    How do I map basic entity attributes to database columns?
</p>
<h2>Solution:</h2>
<p>
    Hibernate doesn't need any additional information to map entity attributes of the following Java types:
</p>
<ul>
    <li>java.lang.String</li>
    <li>char and java.lang.Character</li>
    <li>boolean and java.lang.Boolean</li>
    <li>byte and java.lang.Byte</li>
    <li>short and java.lang.Short</li>
    <li>int and java.lang.Integer</li>
    <li>long and java.lang.Long</li>
    <li>float and java.lang.Float</li>
    <li>double and java.lang.Double</li>
    <li>java.math.BigInteger</li>
    <li>java.math.BigDecimal</li>
    <li>java.sql.Timestamp</li>
    <li>java.sql.Time</li>
    <li>java.sql.Date</li>
    <li>java.util.Calendar</li>
    <li>java.util.Date</li>
    <li>java.util.Locale</li>
    <li>java.util.Timezone</li>
    <li>java.net.URL</li>
    <li>java.sql.Blob</li>
    <li>java.sql.Clob</li>
    <li>byte[] and java.lang.Byte[]</li>
    <li>char[] and java.lang.Character[]</li>
    <li>java.util.UUID</li>
    <li>java.sql.NClob</li>
</ul>
<p>
When you don’t provide any additional mapping information, Hibernate maps
entity attributes of these types to database columns with the same name. It maps
the entity attributes id, version, and firstName in the following code
snippet to the database columns id, version, and firstname.
</p>
<p>
If you want to map an entity attribute to a column with a different name, you can
annotate it with @Column and provide the column name as the name attribute. I
use this annotation in the following code snippet to map the lastName
attribute to the database column lname
</p>
<p>
You can also use the @Column annotation to provide additional mapping
information that Hibernate can use to generate the database schema and to apply
internal optimizations. I don’t recommend using Hibernate’s schema generation
feature to create the final version of your table model, and you shouldn’t add any
additional annotations for it to your entity mappings. But, you should tell
Hibernate if a column is nullable, insertable, and updatable because
it can use this information for internal optimizations. I show that in the following
example for the primary key attribute id. The mapping doesn’t allow null as a
value, and Hibernate doesn’t support updates on primary key attributes.
</p>
<h2>Example:</h2>

```
    @Entity
    public class Author{
        
        @Id
        @GeneratedValue(stategy = GenetationType.AUTO)
        @Column(updatable = false, nullable = false)
        private Long id;
        
        @Version
        private int version;
        
        @Column(name = "fname")
        private String firstName;
        
        @Column(name = "lname")
        private String lastName;
        
        //Getter, Setter, Constructor
    }
```