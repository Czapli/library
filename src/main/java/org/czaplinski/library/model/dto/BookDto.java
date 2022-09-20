package org.czaplinski.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private int yearOfPublication;
}
