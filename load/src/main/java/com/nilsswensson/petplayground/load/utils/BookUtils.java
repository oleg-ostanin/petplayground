package com.nilsswensson.petplayground.load.utils;

import com.nilsswensson.petplayground.common.model.book.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookUtils {
    private final List<Book> allBooks;

    public BookUtils() {
        final List<Book> allBooksLocal = new ArrayList<>();

        final Book book0 = Book.builder().title("Hate1").build();
        allBooksLocal.add(book0);

        this.allBooks = allBooksLocal;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }
}
