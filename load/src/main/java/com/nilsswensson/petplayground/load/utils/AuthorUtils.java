package com.nilsswensson.petplayground.load.utils;

import com.nilsswensson.petplayground.common.model.book.Author;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorUtils {
    private final List<Author> allAuthors;

    public AuthorUtils() {
        final List<Author> allAuthorsLocal = new ArrayList<>();

        final Author author = Author.builder()
                .firstName("Sergey")
                .lastName("Gorokhov")
                .build();
        allAuthorsLocal.add(author);
        this.allAuthors = allAuthorsLocal;

    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }
}
