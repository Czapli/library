package org.czaplinski.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedNativeQuery(
        name = "CopyOfBook.findNumberOfCopies",
        query = "SELECT COUNT(*) " +
                "FROM copy_of_book c " +
                "WHERE c.book_id= :BOOK_ID"
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
    private Borrow borrow;

    public CopyOfBook(Book book, StatusOfBook status) {
        this.book = book;
        this.status = status;
    }
}
