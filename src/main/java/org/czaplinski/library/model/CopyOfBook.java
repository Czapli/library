package org.czaplinski.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedNativeQuery(
        name = "CopyOfBook.findNumberOfCopies",
        query = "COUNT (*) " +
                "FROM copy_of_book " +
                "WHERE book_id = :BOOK_ID"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CopyOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Enumerated(EnumType.STRING)
    private StatusOfBook status;
    @OneToOne
//    @JoinColumn(name = "borrow_id")
    private Borrow borrow;

    public CopyOfBook(Book book, StatusOfBook status) {
        this.book = book;
        this.status = status;
    }
}
