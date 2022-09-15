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
    @OneToOne
    @JoinColumn(name = "copy_of_book_id")
    private CopyOfBook copyOfBook;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;

}
