package org.czaplinski.library.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate yearOfPublication;
    @OneToMany(
            targetEntity = CopyOfBook.class,
            mappedBy = "book",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<CopyOfBook> listOfBookCopies;
}
