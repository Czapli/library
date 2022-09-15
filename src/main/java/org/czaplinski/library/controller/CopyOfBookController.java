package org.czaplinski.library.controller;

import org.czaplinski.library.model.StatusOfBook;
import org.czaplinski.library.model.dto.CopyOfBookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/library/copyOfBook")
public class CopyOfBookController {
    @PostMapping(value = {"add/{idBook}/{status}"})
    public ResponseEntity<CopyOfBookDto> addNewCopy(@PathVariable long idBook, @PathVariable StatusOfBook status) {
        return ResponseEntity.ok(new CopyOfBookDto(idBook, status));
    }

    @PutMapping(value = "change/{idCopy}/{status}")
    public ResponseEntity<CopyOfBookDto> changeStatus(@PathVariable long idCopy, @PathVariable StatusOfBook status) {
        // getById -> updateStatus
        return ResponseEntity.ok(new CopyOfBookDto(
                    idCopy, status
                )
        );
    }
    @GetMapping(value = {"{idCopy}"})
    public ResponseEntity<Integer> getNumberOfCopies(@PathVariable long idCopy) {
        return ResponseEntity.ok(5);
    }
}
