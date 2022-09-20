package org.czaplinski.library.controller;

import lombok.AllArgsConstructor;
import org.czaplinski.library.model.dto.BookDto;
import org.czaplinski.library.model.mapper.BookMapper;
import org.czaplinski.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/library/book")
public class BookController {

    BookService bookService;
    BookMapper bookMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookMapper.mapToBook(bookDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
