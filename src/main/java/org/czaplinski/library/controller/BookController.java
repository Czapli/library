package org.czaplinski.library.controller;

import org.czaplinski.library.model.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/library/book")
public class BookController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BookDto(
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getYearOfPublication()));
    }
}
