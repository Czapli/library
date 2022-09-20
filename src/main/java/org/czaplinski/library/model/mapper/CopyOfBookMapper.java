package org.czaplinski.library.model.mapper;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.model.CopyOfBook;
import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.service.BookService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CopyOfBookMapper {
    BookService bookService;

    public CopyOfBook mapToCopyOfBook(long bookId, StatusOfBook statusOfBook) throws BookNotFoundExceptions {
        return new CopyOfBook(
                bookService.findBookById(bookId),
                statusOfBook
        );
    }
}
