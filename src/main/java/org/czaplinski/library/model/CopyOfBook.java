package org.czaplinski.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
