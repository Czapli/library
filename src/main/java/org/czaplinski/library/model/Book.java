package org.czaplinski.library.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private int yearOfPublication;
    @OneToMany(
            targetEntity = CopyOfBook.class,
            mappedBy = "book",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<CopyOfBook> listOfBookCopies;

    public Book(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public Book(long id, String title, String author, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }
}
