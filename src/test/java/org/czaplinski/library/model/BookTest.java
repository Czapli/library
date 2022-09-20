package org.czaplinski.library.model;

import org.czaplinski.library.model.dto.BookDto;
import org.czaplinski.library.model.mapper.BookMapper;
import org.czaplinski.library.repository.BookRepository;
import org.czaplinski.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;

    @Test
    void addBookToRepositoryTest() {
        //Given
        Book book = new Book("title 1 test", "author 1 test", 1999);
        bookRepository.save(book);

        //When
        Book shouldFindBook = bookRepository.findById(book.getId()).orElseThrow();

        //Then
        assertEquals(book.getId(), shouldFindBook.getId());
    }

    @Test
    void addBookToRepositoryByServiceTest() {
        //Given
        Book book = new Book("title 1 test", "author 1 test", 1999);
        bookService.addBook(book);

        //When
        Book shouldFindBook = bookRepository.findById(book.getId()).orElseThrow();

        //Then
        assertEquals(book.getId(), shouldFindBook.getId());
    }

    @Test
    void mapBookDtoToBookTest(){
        //Given
        BookDto bookDto = new BookDto("title 1 test", "author 1 test", 1999);

        //When
        Book book = bookMapper.mapToBook(bookDto);

        //Then
        assertEquals(bookDto.getTitle(), book.getTitle());
        assertEquals(bookDto.getAuthor(), book.getAuthor());
        assertEquals(bookDto.getYearOfPublication(), book.getYearOfPublication());
    }

}