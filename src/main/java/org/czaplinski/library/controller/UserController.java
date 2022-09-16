package org.czaplinski.library.controller;

import org.czaplinski.library.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/library/user")
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserDto(
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getEmailAddress(),
                        userDto.getParcelLocker()));

    }

}
