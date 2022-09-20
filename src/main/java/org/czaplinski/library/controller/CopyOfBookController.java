package org.czaplinski.library.controller;

import lombok.AllArgsConstructor;
import org.czaplinski.library.controller.exception.BookNotFoundExceptions;
import org.czaplinski.library.controller.exception.CopyOfBookNotFoundExceptions;
import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.model.mapper.CopyOfBookMapper;
import org.czaplinski.library.service.CopyOfBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/library/copyOfBook")
public class CopyOfBookController {
    CopyOfBookService copyOfBookService;
    CopyOfBookMapper copyOfBookMapper;

    @PostMapping(value = {"add/{bookId}/{status}"})
    public ResponseEntity<Void> addCopy(@PathVariable long bookId, @PathVariable StatusOfBook status) throws BookNotFoundExceptions {
        copyOfBookService.addCopyOfBook(copyOfBookMapper.mapToCopyOfBook(bookId, status));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "change/{copyId}/{status}")
    public ResponseEntity<Void> changeStatus(@PathVariable long copyId, @PathVariable StatusOfBook status) throws CopyOfBookNotFoundExceptions {
        copyOfBookService.changeStatus(copyId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = {"{copyId}"})
    public ResponseEntity<Integer> getNumberOfCopies(@PathVariable long copyId) throws CopyOfBookNotFoundExceptions {
        return ResponseEntity.ok().body(copyOfBookService.numberOfCopies(copyId));
    }
}

