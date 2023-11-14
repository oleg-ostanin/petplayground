package com.nilsswensson.petplayground.common.model.book;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class Author {
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
}
