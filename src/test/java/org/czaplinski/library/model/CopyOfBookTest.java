package org.czaplinski.library.model;

import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.controller.exception.CopyOfBookNotFoundExceptions;
import org.czaplinski.library.model.mapper.CopyOfBookMapper;
import org.czaplinski.library.repository.BookRepository;
import org.czaplinski.library.repository.CopyOfBookRepository;
import org.czaplinski.library.service.CopyOfBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CopyOfBookTest {
    @Autowired
    private CopyOfBookRepository copyOfBookRepository;
    @Autowired
    private CopyOfBookMapper copyOfBookMapper;
    @Autowired
    private CopyOfBookService copyOfBookService;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void addCopyOfBookToRepositoryTest() throws BookNotFoundExceptions {
        //Given
        Book book = new Book("title test", "author test", 1999);
        bookRepository.save(book);
        CopyOfBook copyOfBook = new CopyOfBook(book, StatusOfBook.IN_USE);
        copyOfBookRepository.save(copyOfBook);

        //When
        CopyOfBook shouldFindCopyOfBook = copyOfBookRepository
                .findById(copyOfBook.getId())
                .orElseThrow(BookNotFoundExceptions::new);

        //Then
        assertEquals(copyOfBook.getId(), shouldFindCopyOfBook.getId());
    }

    @Test
    void mapToCopyOfBookTest() throws BookNotFoundExceptions {

        //Given
        Book book = new Book("title test", "author test", 1999);
        bookRepository.save(book);

        //When
        CopyOfBook copyOfBook = copyOfBookMapper.mapToCopyOfBook(book.getId(), StatusOfBook.IN_USE);

        //Then
        assertEquals(copyOfBook.getBook().getId(), book.getId());
        assertEquals(copyOfBook.getStatus(), StatusOfBook.IN_USE);
    }

    @Test
    void changeStatusTest() throws CopyOfBookNotFoundExceptions {

        //Given
        Book book = new Book("title test", "author test", 1999);
        bookRepository.save(book);
        CopyOfBook copyOfBook = new CopyOfBook(book, StatusOfBook.IN_USE);
        copyOfBookRepository.save(copyOfBook);

        //When
        copyOfBookService.changeStatus(copyOfBook.getId(), StatusOfBook.LOST);
        CopyOfBook shouldFindCopyOfBook = copyOfBookRepository
                .findById(copyOfBook.getId())
                .orElseThrow(CopyOfBookNotFoundExceptions::new);
        //Then
        assertEquals(StatusOfBook.LOST, shouldFindCopyOfBook.getStatus());
    }

    @Test
    void numberOfCopiesTest() throws CopyOfBookNotFoundExceptions {

        //Given
        Book book1 = new Book("title 1 test", "author 1 test", 2001);
        bookRepository.save(book1);
        Book book2 = new Book("title 2 test", "author 2 test", 2002);
        bookRepository.save(book2);


        CopyOfBook copyOfBook1 = new CopyOfBook(book1, StatusOfBook.IN_USE);
        copyOfBookService.addCopyOfBook(copyOfBook1);

        CopyOfBook copyOfBook2 = new CopyOfBook(book2, StatusOfBook.IN_USE);
        copyOfBookService.addCopyOfBook(copyOfBook2);


        CopyOfBook copyOfBook3 = new CopyOfBook(book1, StatusOfBook.IN_USE);
        copyOfBookService.addCopyOfBook(copyOfBook3);

        CopyOfBook copyOfBook4 = new CopyOfBook(book1, StatusOfBook.IN_USE);
        copyOfBookService.addCopyOfBook(copyOfBook4);

        CopyOfBook copyOfBook5 = new CopyOfBook(book2, StatusOfBook.IN_USE);
        copyOfBookService.addCopyOfBook(copyOfBook5);


        //When
        int numberOfBook1 = copyOfBookService.numberOfCopies(copyOfBook1.getId());
        int numberOfBook2 = copyOfBookService.numberOfCopies(copyOfBook2.getId());

        //Then
        assertEquals(3, numberOfBook1);
        assertEquals(2, numberOfBook2);
    }
}
