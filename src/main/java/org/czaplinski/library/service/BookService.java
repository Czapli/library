package org.czaplinski.library.service;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.model.Book;
import org.czaplinski.library.repository.BookRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }
    public Book findBookById(long bookId) throws BookNotFoundExceptions {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundExceptions::new);
    }
}
