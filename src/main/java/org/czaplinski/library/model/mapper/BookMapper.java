package org.czaplinski.library.model.mapper;

import org.czaplinski.library.model.Book;
import org.czaplinski.library.model.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book mapToBook(BookDto bookDto){
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYearOfPublication()
        );
    }
}
