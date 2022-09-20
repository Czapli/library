package org.czaplinski.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Borrower borrower;
    @OneToOne
    @JoinColumn(name = "copy_of_book_id")
    private CopyOfBook copyOfBook;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;

    public Borrow(Borrower borrower, CopyOfBook copyOfBook, LocalDate borrowedDate, LocalDate returnedDate) {
        this.borrower = borrower;
        this.copyOfBook = copyOfBook;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

}
