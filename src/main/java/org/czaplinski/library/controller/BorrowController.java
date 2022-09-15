package org.czaplinski.library.controller;

import org.czaplinski.library.model.dto.BorrowDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/library")
public class BorrowController {
    @PostMapping(value = "{idUser}/borrow/{idBook}")
    public ResponseEntity<BorrowDto> borrowBook (@PathVariable long idUser, @PathVariable long idBook) {
        //mapper added borrowedDate
        return ResponseEntity.ok( new BorrowDto(
                idUser,
                idBook
        ));
    }
    @PutMapping(value = "{idUser}/return/{idBook}")
    public ResponseEntity<BorrowDto> returnBook (@PathVariable long idUser, @PathVariable long idBook) {
        //mapper added returnedDate
        return ResponseEntity.ok( new BorrowDto(
                idUser,
                idBook
        ));
    }
}
