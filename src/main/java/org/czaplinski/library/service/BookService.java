package org.czaplinski.library.service;

import org.czaplinski.library.model.Book;
import org.czaplinski.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
