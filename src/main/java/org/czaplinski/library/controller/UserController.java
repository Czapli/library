package org.czaplinski.library.controller;

import org.czaplinski.library.model.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/library/user")
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(new UserDto(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmailAddress(),
                userDto.getParcelLocker()));
    }

}
