package org.czaplinski.library.controller;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.controller.exception.BorrowNotFoundExceptions;
import org.czaplinski.library.controller.exception.CopyOfBookNotFoundExceptions;
import org.czaplinski.library.model.mapper.BorrowMapper;
import org.czaplinski.library.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/library")
public class BorrowController {
    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;
    @PostMapping(value = "{borrowerId}/borrow/{copyOfBookId}")
    public ResponseEntity<Void> borrowBook(@PathVariable long borrowerId, @PathVariable long copyOfBookId) throws CopyOfBookNotFoundExceptions, BookNotFoundExceptions {
        if(borrowService.borrowBook(borrowMapper.mapToNewBorrow(borrowerId, copyOfBookId))){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @PutMapping(value = "return/{borrowId}")
    public ResponseEntity<Void> returnBook(@PathVariable long borrowId) throws BorrowNotFoundExceptions {
        borrowService.returnBook(borrowId);
        return ResponseEntity.ok().build();
    }
}
