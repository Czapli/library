package org.czaplinski.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BorrowerDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String parcelLocker;
}
