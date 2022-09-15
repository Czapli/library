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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String parcelLocker;
    private LocalDate accountCreationDate;
    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<Borrow> listOfBorrowedBook;
}
