# Hibernate

## 1. Basic / Embedded Type

- 嵌套的类型，使用 `@Embeddable`

  > - `@Embeddable` is used to describe the mapping type itself (e.g. `Publisher`).
  > - `@Embedded` is for referencing a given embeddable type (e.g. `book.publisher`).

- 注意使用 `@Column(name = "publisher_name")`

```java
@Entity(name = "Book")
public static class Book {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String author;

	private Publisher publisher;

	//Getters and setters are omitted for brevity
}

@Embeddable
public static class Publisher {

	@Column(name = "publisher_name")
	private String name;

	@Column(name = "publisher_country")
	private String country;

	//Getters and setters, equals and hashCode methods omitted for brevity

}
```

```sql
create table Book (
    id bigint not null,
    author varchar(255),
    publisher_country varchar(255),
    publisher_name varchar(255),
    title varchar(255),
    primary key (id)
)
```

## 2. 一个Entity中有两个 Embedable

```java
@Entity(name = "Book")
@AttributeOverrides({
	@AttributeOverride(
		name = "ebookPublisher.name",
		column = @Column(name = "ebook_publisher_name")
	),
	@AttributeOverride(
		name = "paperBackPublisher.name",
		column = @Column(name = "paper_back_publisher_name")
	)
})
@AssociationOverrides({
	@AssociationOverride(
		name = "ebookPublisher.country",
		joinColumns = @JoinColumn(name = "ebook_publisher_country_id")
	),
	@AssociationOverride(
		name = "paperBackPublisher.country",
		joinColumns = @JoinColumn(name = "paper_back_publisher_country_id")
	)
})
public static class Book {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String author;

	private Publisher ebookPublisher;

	private Publisher paperBackPublisher;

	//Getters and setters are omitted for brevity
}
```

