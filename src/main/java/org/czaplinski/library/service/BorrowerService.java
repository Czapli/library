package org.czaplinski.library.service;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.model.Borrower;
import org.czaplinski.library.repository.BorrowerRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public void addBorrower(Borrower borrower){
        borrowerRepository.save(borrower);
    }
    public Borrower findBorrowerById(long borrowerId) throws BookNotFoundExceptions {
        return borrowerRepository.findById(borrowerId).orElseThrow(BookNotFoundExceptions::new);
    }
}
