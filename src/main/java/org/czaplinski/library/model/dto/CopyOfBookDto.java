package org.czaplinski.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.czaplinski.library.model.StatusOfBook;
@Getter
@Setter
@AllArgsConstructor
public class CopyOfBookDto {
    private long bookId;
    private StatusOfBook status;
}
