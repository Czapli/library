package org.czaplinski.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BorrowDto {
    private long idUser;
    private long idCopy;
}
