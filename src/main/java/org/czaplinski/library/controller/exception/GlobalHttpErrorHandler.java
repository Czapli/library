package org.czaplinski.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundExceptions.class)
    public ResponseEntity<Object> handlerBookNotFoundExceptions(BookNotFoundExceptions e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with given id doesn't exist");
    }

    @ExceptionHandler(BorrowerNotFoundExceptions.class)
    public ResponseEntity<Object> handlerBorrowerNotFoundExceptions(BookNotFoundExceptions e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrower with given id doesn't exist");
    }
        @ExceptionHandler(BorrowNotFoundExceptions.class)
    public ResponseEntity<Object> handlerBorrowNotFoundExceptions(BookNotFoundExceptions e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrow with given id doesn't exist");
    }
        @ExceptionHandler(CopyOfBookNotFoundExceptions.class)
    public ResponseEntity<Object> handlerCopyOfBookNotFoundExceptions(BookNotFoundExceptions e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Copy Of Book with given id doesn't exist");
    }

}
