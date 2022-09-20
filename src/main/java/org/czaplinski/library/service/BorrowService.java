package org.czaplinski.library.service;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BorrowNotFoundExceptions;
import org.czaplinski.library.model.Borrow;
import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;

    public boolean borrowBook(Borrow borrow) {
        if (borrow.getCopyOfBook().getStatus().equals(StatusOfBook.IN_USE)) {
            borrow.getCopyOfBook().setStatus(StatusOfBook.BORROWED);
            borrowRepository.save(borrow);
            return true;
        }
        return false;
    }

    public void returnBook(long borrowId) throws BorrowNotFoundExceptions {
        Borrow returnedBook = borrowRepository.findById(borrowId).orElseThrow(BorrowNotFoundExceptions::new);
        returnedBook.getCopyOfBook().setStatus(StatusOfBook.IN_USE);
        returnedBook.setReturnedDate(LocalDate.now());
        borrowRepository.save(returnedBook);
    }
}
