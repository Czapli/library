package org.czaplinski.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String parcelLocker;
}
