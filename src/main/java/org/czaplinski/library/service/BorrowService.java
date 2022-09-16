package org.czaplinski.library.service;

import org.czaplinski.library.model.Borrow;
import org.czaplinski.library.repository.BookRepository;
import org.czaplinski.library.repository.BorrowRepository;
import org.czaplinski.library.repository.CopyOfBookRepository;
import org.czaplinski.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final CopyOfBookRepository copyOfBookRepository;

    public BorrowService(BorrowRepository borrowRepository, UserRepository userRepository, CopyOfBookRepository copyOfBookRepository) {
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
        this.copyOfBookRepository = copyOfBookRepository;
    }

    public boolean borrowBook(long userId, long copyOfBookId){
        borrowRepository.save(new Borrow(
                userRepository.findById(userId).orElseThrow(),
                copyOfBookRepository.findById(copyOfBookId).orElseThrow(),
                LocalDate.now(),
                null
        ));
        return true;
    }
    public void returnBook(long borrowId){
        Borrow returnedBook = borrowRepository.findById(borrowId).orElseThrow();
        returnedBook.setReturnedDate(LocalDate.now());
    }
}
