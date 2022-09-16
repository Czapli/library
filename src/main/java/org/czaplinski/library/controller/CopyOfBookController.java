package org.czaplinski.library.controller;

import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.model.dto.CopyOfBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/library/copyOfBook")
public class CopyOfBookController {
    @PostMapping(value = {"add/{bookId}/{status}"})
    public ResponseEntity<CopyOfBookDto> addCopy(@PathVariable long bookId, @PathVariable StatusOfBook status) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CopyOfBookDto(bookId, status));
    }

    @PutMapping(value = "change/{copyId}/{status}")
    public ResponseEntity<CopyOfBookDto> changeStatus(@PathVariable long copyId, @PathVariable StatusOfBook status) {
        // getById -> updateStatus
        return ResponseEntity.ok(new CopyOfBookDto(
                copyId, status
                )
        );
    }

    @GetMapping(value = {"{copyId}"})
    public ResponseEntity<Integer> getNumberOfCopies(@PathVariable long copyId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(5);
    }
}
