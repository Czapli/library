package org.czaplinski.library.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    private String title;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfPublication;
}
