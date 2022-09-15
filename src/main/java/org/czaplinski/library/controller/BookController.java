package org.czaplinski.library.controller;

import org.czaplinski.library.model.dto.BookDto;
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
    public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(new BookDto(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYearOfPublication()));
    }
}
