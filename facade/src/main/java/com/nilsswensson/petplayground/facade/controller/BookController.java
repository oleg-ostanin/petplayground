package com.nilsswensson.petplayground.facade.controller;

import com.nilsswensson.petplayground.common.model.Book;
import com.nilsswensson.petplayground.facade.service.BookService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rest")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Timed
    @GetMapping("/book")
    public ResponseEntity<Book> findBook() {
        return ResponseEntity.ok(bookService.getByTitle("Hate1"));
    }

    @Timed
    @PostMapping("/add-book")
    public void addBook(@RequestBody final Book book) {
        bookService.addBook(book);
    }
}
