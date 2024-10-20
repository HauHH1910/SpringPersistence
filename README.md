<h1 style="color: #4169e1">How to map a util Date or Calendar to a database column</h1>
<h2 style="color: #ddaa3b">Problem:</h2>
<p >I use a java.util.Date to model a date as an entity attribute. But Hibernate
maps it to a timestamp with nanoseconds. How do I change the mapping so that
Hibernate only stores the years, months, and days?</p>
<h2 style="color: #ddaa3b">Solution:</h2>
<p>
    The SQL standard supports three different data types to store date and time
information. Hibernate can map all of them to a java.util.Date or a
java.util.Calendar. You need to decide which of the following SQL
types Hibernate will use
</p>
<ul>
    <li>
    <strong style="color: #2e8b57">TIMESTAMP</strong>: Persists the date and time with nanoseconds. Hibernate uses this type by default
    </li>
    <li>
    <strong style="color: #2e8b57">TIME</strong>: Stores only the time of day without nanoseconds.
    </li>
    <li>
    <strong style="color: #2e8b57">DATE</strong>: Persists only the date with years, months, and days.
    </li>
</ul>
<p>
    You can define the preferred mapping with the @Temporal annotation. As you can see in the following code snippet, the annotation takes a TemporalType
    enum as a value. The num allows you to select the SQL type (DATE, TIME, or TIMESTAMP) that you want to use.
</p>
<h2 style="color: #ddaa3b">Example</h2>

```
  @Entity
  @Table(name = "author")
  public class Author {
        
        //This will persists only the date with years, months, and days
        @Temporal(TemporalType.DATE)
        private Date dateOfBirth;

        //Other attributes
  }

```