package org.czaplinski.library.model;

import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.controller.exception.BorrowNotFoundExceptions;
import org.czaplinski.library.controller.exception.CopyOfBookNotFoundExceptions;
import org.czaplinski.library.model.mapper.BorrowMapper;
import org.czaplinski.library.repository.BookRepository;
import org.czaplinski.library.repository.BorrowRepository;
import org.czaplinski.library.repository.BorrowerRepository;
import org.czaplinski.library.repository.CopyOfBookRepository;
import org.czaplinski.library.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BorrowTest {

    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    BorrowService borrowService;
    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    BorrowerRepository borrowerRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CopyOfBookRepository copyOfBookRepository;

    @Test
    void addBorrowToRepositoryTest() {
        //Given
        Borrower borrower = new Borrower("firstName test", "lastName test", "test@czaplinski.org", "1234", LocalDate.now());
        borrowerRepository.save(borrower);
        Book book = new Book("title test", "author test", 1999);
        bookRepository.save(book);
        CopyOfBook copyOfBook = new CopyOfBook(book, StatusOfBook.IN_USE);
        copyOfBookRepository.save(copyOfBook);
        Borrow borrow = new Borrow(borrower, copyOfBook, null, null);
        borrowRepository.save(borrow);

        //When
        Borrow shouldFindBorrow = borrowRepository.findById(borrow.getId()).orElseThrow();

        //Then
        assertEquals(borrow.getId(), shouldFindBorrow.getId());
    }

    @Test
    void mapToBorrowTest() throws BookNotFoundExceptions, CopyOfBookNotFoundExceptions {

        //Given
        Borrower borrower = new Borrower("firstName test", "lastName test", "test@czaplinski.org", "1234", LocalDate.now());
        borrowerRepository.save(borrower);
        Book book = new Book("title test", "author test", 1999);
        bookRepository.save(book);
        CopyOfBook copyOfBook = new CopyOfBook(book, StatusOfBook.IN_USE);
        copyOfBookRepository.save(copyOfBook);

        //When
        Borrow borrow = borrowMapper.mapToNewBorrow(borrower.getId(), copyOfBook.getId());

        //Then
        assertNotNull(borrow.getBorrowedDate());
        assertEquals(borrower.getId(),borrow.getBorrower().getId());
        assertEquals(copyOfBook.getId(),borrow.getCopyOfBook().getId());
        assertEquals(book.getId(),borrow.getCopyOfBook().getBook().getId());
        assertNull(borrow.getReturnedDate());
    }

    @Test
    void borrowBookTest() throws BookNotFoundExceptions, CopyOfBookNotFoundExceptions {
        //Given
        Borrower borrower = new Borrower("firstName test", "lastName test", "test@czaplinski.org", "1234", LocalDate.now());
        borrowerRepository.save(borrower);
        Book book = new Book("title test", "author test", 1999);
        bookRepository.save(book);
        CopyOfBook copyOfBookInUse = new CopyOfBook(book, StatusOfBook.IN_USE);
        copyOfBookRepository.save(copyOfBookInUse);
        CopyOfBook copyOfBookLost = new CopyOfBook(book, StatusOfBook.LOST);
        copyOfBookRepository.save(copyOfBookLost);


        //When
        Borrow borrowInUse = borrowMapper.mapToNewBorrow(borrower.getId(), copyOfBookInUse.getId());
        Borrow borrowLost = borrowMapper.mapToNewBorrow(borrower.getId(), copyOfBookLost.getId());

        //Then
        assertTrue(borrowService.borrowBook(borrowInUse));
        assertFalse(borrowService.borrowBook(borrowLost));
    }

    @Test
    void returnBookTest() throws BookNotFoundExceptions, CopyOfBookNotFoundExceptions, BorrowNotFoundExceptions {

        //Given
        Borrower borrower = new Borrower("firstName test", "lastName test", "test@czaplinski.org", "1234", LocalDate.now());
        borrowerRepository.save(borrower);
        Book book = new Book("title test", "author test", 1999);
        bookRepository.save(book);
        CopyOfBook copyOfBook = new CopyOfBook(book, StatusOfBook.IN_USE);
        copyOfBookRepository.save(copyOfBook);
        Borrow borrow = borrowMapper.mapToNewBorrow(borrower.getId(), copyOfBook.getId());
        borrowService.borrowBook(borrow);

        //When
        borrowService.returnBook(borrow.getId());

        //Then
        assertNotNull(borrowRepository.findById(borrow.getId()).get().getReturnedDate());
    }
}

//Given
//When
//Then