package org.czaplinski.library.controller;

import org.czaplinski.library.model.dto.BorrowDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/library")
public class BorrowController {
    @PostMapping(value = "{userId}/borrow/{copyOfBookId}")
    public ResponseEntity<BorrowDto> borrowBook(@PathVariable long userId, @PathVariable long copyOfBookId) {
        //mapper added borrowedDate
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BorrowDto(
                        userId,
                        copyOfBookId
                ));
    }

    @PutMapping(value = "return/{borrowId}")
    public ResponseEntity<BorrowDto> returnBook(@PathVariable long borrowId) {
        //mapper added returnedDate
        return ResponseEntity.ok().build();
    }
}
