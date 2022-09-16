package org.czaplinski.library.model;

import org.czaplinski.library.model.Book;
import org.czaplinski.library.repository.BookRepository;
import org.czaplinski.library.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void addBook() {
        //Given
        Book book = new Book("title 1 test", "author 1 test", 1999);
        bookRepository.save(book);

        //When
        Book shouldFindBook = bookRepository.findById(book.getId()).orElseThrow();

        //Then
        Assertions.assertEquals(book.getId(), shouldFindBook.getId());
    }
}