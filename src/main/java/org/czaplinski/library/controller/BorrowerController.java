package org.czaplinski.library.controller;

import lombok.AllArgsConstructor;
import org.czaplinski.library.model.dto.BorrowerDto;
import org.czaplinski.library.model.mapper.BorrowerMapper;
import org.czaplinski.library.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/library/user")
public class BorrowerController {
    BorrowerService borrowerService;
    BorrowerMapper borrowerMapper;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BorrowerDto> addUser(@RequestBody BorrowerDto borrowerDto) {
        borrowerService.addBorrower(borrowerMapper.mapToBorrower(borrowerDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
